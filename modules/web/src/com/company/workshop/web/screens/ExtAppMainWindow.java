package com.company.workshop.web.screens;

import com.company.workshop.config.ChameleonConfig;
import com.company.workshop.web.ext.ChameleonJsSnippetInjector;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtAppMainWindow extends AppMainWindow {

    protected Logger log = LoggerFactory.getLogger(ExtAppMainWindow.class);

    @Inject
    private UserSession userSession;
    @Inject
    private ChameleonConfig chameleonConfig;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        log.debug("Pre-INIT Chameleon");

        if (chameleonConfig.isChameleonEnabled()) {
            log.debug("INIT Chameleon");

            AbstractClientConnector vMainWindow = this.unwrapComposition(Window.class);
            ChameleonJsSnippetInjector injector = new ChameleonJsSnippetInjector();

            User user = userSession.getCurrentOrSubstitutedUser();
            if (user != null) {
                injector.setUserId(user.getId().toString());
                injector.setUserEmail(user.getEmail());
                injector.setUserName(user.getName());
                injector.setUserRole(user.getUserRoles().stream().map(userRole -> userRole.getRole().getName()).collect(Collectors.joining(",")));
            }

            injector.extend(vMainWindow);

            log.debug("Post INIT Chameleon");
        }
    }
}