# CUBA and Chameleon integration

This is a sample application that shows how to make [Chameleon tours creating tool](https://www.trychameleon.com/) 
working fine with Java applications based on [CUBA Platform](https://www.cuba-platform.com/).

Although CUBA-based applications works just fine with Chameleon, there are couple questions that need to be addressed:

1. How to include custom [Chameleon JS-snippet](https://www.trychameleon.com/setup/go-live) that is used by Chameleon 
tool on production to **every** page of the CUBA app.
1. How to [pass custom fields to Chameleon](https://www.youtube.com/watch?v=2GZueVdBPGw&list=PLu0g_GBxlYcmVU1fRJ7HPmDzBKDJAUFep&index=5)
like user ID, user role and some others to help Chameleon to 
[target users](https://www.youtube.com/watch?v=poYJ2Rq57dI&index=4&list=PLu0g_GBxlYcmVU1fRJ7HPmDzBKDJAUFep).
1. How to make Chameleon 
[selecting the right element](https://www.youtube.com/watch?v=KI26PLlEsr4&list=PLu0g_GBxlYcmVU1fRJ7HPmDzBKDJAUFep&index=3) 
on the page if we have a lot of similar elements on the screen, for example if we need to select one of two "Save" buttons,  
or one specific table row out of many quite identical ones.

This app demonstrates solutions for this topics.
The demo is based on our [classical Bicycle Workshop example](https://github.com/cuba-platform/sample-workshop).

## Adding Chameleon Javascript

According to chameleon manual we have to add the next script to the `<head>' section of our HTMLs to get Chameleon tool enabled:

```javascript
<script type="text/javascript">/* Chameleon - better user onboarding */!function(t,n,o){var a="chmln",e="adminPreview",c="setup identify alias track clear set show on off custom help _data".split(" ");if(n[a]||(n[a]={}),n[a][e]&&(n[a][e]=!1),!n[a].root){n[a].accountToken=o,n[a].location=n.location.href.toString(),n[a].now=new Date;for(var s=0;s<c.length;s++)!function(){var t=n[a][c[s]+"_a"]=[];n[a][c[s]]=function(){t.push(arguments)}}();var i=t.createElement("script");i.src="https://fast.trychameleon.com/messo/"+o+"/messo.min.js",i.async=!0,t.head.appendChild(i)}}(document,window,"S9m04zjA3YmE7qpw3t1xS5S9sHs7VTVz6EypzPUBJI5Jx5-1GnaTs-B00iCKXH0iRe1tfG");
  // **This is an example script, don't forget to change the PLACEHOLDERS.**
  // Please confirm the user properties to be sent with your project owner.

  // Required:
  chmln.identify(USER.ID_IN_DB, {     // Unique ID of each user in your database (e.g. 23443 or "590b80e5f433ea81b96c9bf6")
    email: USER.EMAIL,                // Put quotes around text strings (e.g. "jim@example.com")

    // Optional - additional user properties:
    created: USER.SIGN_UP_DATE,       // Send dates in ISO or unix timestamp format (e.g. "2017-07-01T03:21:10Z" or 1431432000)
    name: USER.NAME,                  // We will parse this to extra first and surnames (e.g. "James Doe")
    role: USER.ROLE,                  // Send properties useful for targeting types of users (e.g. "Admin")
    logins: USER.LOGIN_COUNT,         // Send any data about user engagement (e.g. 39)
    project: USER.PROJECT_ID,         // Send any unique data for a user that might appear in any page URLs (e.g. 09876 or "12a34b56")

    // Optional - company properties:
    company: {                        // For B2B products, send company / account information here
      uid: COMPANY.ID_IN_DB,          // Unique ID of the company / account in your database (e.g. 9832 or "590b80e5f433ea81b96c9bf7")
      created: COMPANY.SIGN_UP_DATE,  // To enable targeting all users based on this company property
      name: COMPANY.NAME,             // Send any data that appears within URLs, such as subdomains (e.g. "airbnb")
      trial_ends: COMPANY.TRIAL_ENDS, // Send data about key milestones (e.g. "2017-08-01T03:21:10Z")
      version: COMPANY.VERSION,       // If your software varies by version then this will help show the correct guidance (e.g. "1.56")
      plan: COMPANY.PLAN,             // Send null when no value exists (e.g. "Gold", "Advanced")
      spend: COMPANY.CLV              // Send other properties that will help in targeting users (e.g. sales rep, source, stage)
    }
  });
</script>

```

Please note, that on the far right side of the very first line you can find an id, which is _(probably)_ unique for every 
chameleon organisational account. So, when you start preparing your CUBA app for Chameleon tool, you would need to not forget to change this
ID to your own one.

The method this app uses for loading chameleon's JS is based on adding custom JS script component to the main window of the CUBA application.
If your application doesn't have a custom main window, you can take a look at this [documantation pages](https://doc.cuba-platform.com/manual-latest/main_window_layout.html) 
or this [forum thread](https://www.cuba-platform.com/discuss/t/how-to-customize-front-web-app-screen/601). The task of creating a custom Main Window
(if your app doesn't have such yet) task can be easily done from CUBA studio using **Generic UI -> New -> Main Window** tool.

Nevertheless, following these tutorials [1: avascript NON-visual Component](https://www.cuba-platform.com/discuss/t/javascript-non-visual-component/3594/6) 
[2: Fullscreen tabs](https://www.cuba-platform.com/discuss/t/fullscreen-tabs/3079/3) and 
[3: Integrating JavaScript Components and Extensions](https://vaadin.com/docs/v8/framework/gwt/gwt-javascript.html), let's take a look at the next files:

1. [ExtAppMainWindow.java](https://github.com/dyakonoff/cuba-chameleon/blob/master/modules/web/src/com/company/workshop/web/screens/ExtAppMainWindow.java) - 
this class injects our custom Javascript component into the apps' top-level window and do it only if this option is enabled through configuration 
_(see [Using Configuration Interfaces](https://doc.cuba-platform.com/manual-latest/config_interface_usage.html) page for details)_.
2. [ChameleonJsSnippetInjector.java](https://github.com/dyakonoff/cuba-chameleon/blob/master/modules/web/src/com/company/workshop/web/ext/ChameleonJsSnippetInjector.java) - 
ChameleonJsSnippetInjector defines our custom JavaScript extension component and gives us couple set methods to pass data to the script.
3. [ChameleonJsExtState.java](https://github.com/dyakonoff/cuba-chameleon/blob/master/modules/web/src/com/company/workshop/web/ext/ChameleonJsExtState.java) - 
simple POJO to carry data to the client side through Vaadin's mechanisms.
4. [hameleon-init.js](https://github.com/dyakonoff/cuba-chameleon/blob/master/modules/web/web/VAADIN/chameleon-init.js) - the Javascript we loads to the page.
It is somewhat tricky. Since we need to pass some dynamic data from out Java code (like user ID, email etc) and they are coming to the `init` 
function of this script, we have no choice, but to build the required chameleon's script dynamically and add it to `<head>` section
using `appendChild` method.

## Adding ID-s to locate CUBA components reliably

### Automatic method

The simpliest way to do this is to add `cuba.testMode = true` statement to the [web-app.properties](https://github.com/dyakonoff/cuba-chameleon/blob/master/modules/web/src/web-app.properties)
file. In this case, CUBA will start adding `cuba-id` attributes to each UI component on the screen. 
(See [documentation](https://doc.cuba-platform.com/manual-latest/app_properties_reference.html#cuba.testMode) for details).

### Manual method

The idea of this method is to adding a dummy css classes to HTML representations of CUBA components, which can be achieved either by
using `stylename` attribute in screen's XML:

```xml
<buttonsPanel id="buttonsPanel">
    <!-- button "saveOrder" will be rendered to:
    <div role="button" class="v-button v-widget chameleon-order-save v-button-chameleon-order-save icon v-button-icon">...</div>
    -->
    <button action="saveOrder" stylename="chameleon-order-save"/>
    <button action="cancelOrder" stylename="chameleon-order-cancel"/>
</buttonsPanel>
```

[order-edit.xml](https://github.com/dyakonoff/cuba-chameleon/blob/master/modules/web/src/com/company/workshop/web/order/order-edit.xml)

Or can be done in screen controllers.

For individual components:
```java
public class ExtAbstractFrame extends AbstractFrame {
    @Inject
    private Button windowCommit;
    @Inject
    private Button windowClose;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        windowCommit.addStyleName("chameleon-button-commit");
        windowClose.addStyleName("chameleon-button-close");
    }
}
```

[ExtAbstractFrame.java](https://github.com/dyakonoff/cuba-chameleon/blob/master/modules/web/src/com/company/workshop/web/frames/ExtAbstractFrame.java)

Please, note that here we have redefined our standard CUBA `editWindowActions` frame. (See [this page](https://www.cuba-platform.com/discuss/t/edit-extended-windows-button/510/2) for details).

Another approach would be beter suitable to tables and other composite components, it uses `addStyleProvider` call and looks like thos:


```java
public class ClientBrowse extends AbstractLookup {
    @Inject
    private Table<Client> clientsTable;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        clientsTable.addStyleProvider((client, property) -> {
            if (property == null && client!=null) {
                // set style for the whole row
                // row would be rendered as (for example)
                // <tr class="v-table-row-cs cs chameleon-client-row-8fa55caa-59de-cd89-1252-252b73360ff4 v-table-row-odd" style="">...</tr>
                return "chameleon-client-row-" + client.getId();
            } else {
                return null;
            }
        });
    }
}
``` 

[ClientBrowse.java](https://github.com/dyakonoff/cuba-chameleon/blob/master/modules/web/src/com/company/workshop/web/client/ClientBrowse.java)

# Bicycle Workshop

Sample application from [Full-scale CUBA application: Step by Step Guide](https://github.com/cuba-platform/sample-workshop/wiki) tutorial.

The Workshop application is a simple order management system that enables tracking orders made by clients. Each order contains information about client, mechanic, spare parts used and services provided. Clients, mechanics, spare parts and orders can be created, edited and deleted through the system user interface.

This sample covers basic and most used features of the CUBA Platform, including development approach and runtime features of the platform.

Based on CUBA Platform 6.10.3

## Issues
Please use https://www.cuba-platform.com/discuss for discussion, support, and reporting problems coressponding to this sample.
