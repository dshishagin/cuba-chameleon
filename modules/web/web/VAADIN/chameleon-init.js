window.com_company_workshop_web_ext_ChameleonJsSnippetInjector = function() {
    !function(t,n,o){var a="chmln",e="adminPreview",c="setup identify alias track clear set show on off custom help _data".split(" ");if(n[a]||(n[a]={}),n[a][e]&&(n[a][e]=!1),!n[a].root){n[a].accountToken=o,n[a].location=n.location.href.toString(),n[a].now=new Date;for(var s=0;s<c.length;s++)!function(){var t=n[a][c[s]+"_a"]=[];n[a][c[s]]=function(){t.push(arguments)}}();var i=t.createElement("script");i.src="https://fast.trychameleon.com/messo/"+o+"/messo.min.js",i.async=!0,t.head.appendChild(i)}}(document,window,"S9m04zjA3YmE7qpw3t1xS5S9sHs7VTVz6EypzPUBJI5Jx5-1GnaTs-B00iCKXH0iRe1tfG");
    // **This is an example script, don't forget to change the PLACEHOLDERS.**

    // Required:
    chmln.identify(USER.ID_IN_DB, {     // Unique ID of each user in your database (e.g. 23443 or "590b80e5f433ea81b96c9bf6")
        email: USER.EMAIL,              // Put quotes around text strings (e.g. "jim@example.com")

        // Optional - additional user properties:
        name: USER.NAME,                // We will parse this to extra first and surnames (e.g. "James Doe")
        role: USER.ROLE                 // Send properties useful for targeting types of users (e.g. "Admin")
    })
}