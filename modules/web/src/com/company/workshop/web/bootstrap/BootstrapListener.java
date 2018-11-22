package com.company.workshop.web.bootstrap;

import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.web.App;
import com.haulmont.cuba.web.sys.CubaBootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class BootstrapListener extends CubaBootstrapListener {

    private final Logger log = LoggerFactory.getLogger(BootstrapListener.class);

//    @Inject
//    protected UserSessionSource userSessionSource;

    @Override
    public void modifyBootstrapPage(BootstrapPageResponse response) {
        log.debug("We are HERE!");
//        User user = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
//        String userId = user.getId().toString();
//        String userEmail = user.getEmail();
//        log.debug(String.format("BootstrapListener: %s, %s", userId, userEmail));
//        App.getInstance().u
//        super.modifyBootstrapPage(response);
//
//        Element head = response.getDocument().getElementsByTag("head").get(0);
//        head.appendElement("script")
//
//        <script type="text/javascript">!function(t,n,o){var a="chmln",e="adminPreview",c="setup identify alias track clear set show on off custom help _data".split(" ");if(n[a]||(n[a]={}),n[a][e]&&(n[a][e]=!1),!n[a].root){n[a].accountToken=o,n[a].location=n.location.href.toString(),n[a].now=new Date;for(var s=0;s<c.length;s++)!function(){var t=n[a][c[s]+"_a"]=[];n[a][c[s]]=function(){t.push(arguments)}}();var i=t.createElement("script");i.src="https://fast.trychameleon.com/messo/"+o+"/messo.min.js",i.async=!0,t.head.appendChild(i)}}(document,window,"S9m04zjA3YmE7qpw3t1xS5S9sHs7VTVz6EypzPUBJI5Jx5-1GnaTs-B00iCKXH0iRe1tfG");
//        chmln.identify(USER.ID_IN_DB, {     // Unique ID of each user in your database (e.g. 23443 or "590b80e5f433ea81b96c9bf6")
//                email: USER.EMAIL});        // Put quotes around text strings (e.g. "jim@example.com")
//        </script>
    }
}
