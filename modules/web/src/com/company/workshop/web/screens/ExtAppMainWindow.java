package com.company.workshop.web.screens;

import com.company.workshop.web.ext.ChameleonJsSnippetInjector;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.App;
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.ui.Window;

import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;
import javax.management.relation.Role;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtAppMainWindow extends AppMainWindow {

    @Inject
    private UserSession userSession;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        AbstractClientConnector vMainWindow  = this.unwrapComposition(Window.class);
        ChameleonJsSnippetInjector injector = new ChameleonJsSnippetInjector();

        User user = userSession.getCurrentOrSubstitutedUser();
        if (user != null) {
            injector.setUserId(user.getId().toString());
            injector.setUserEmail(user.getEmail());
            injector.setUserName(user.getName());
            injector.setUserRole(user.getUserRoles().stream().map(userRole -> userRole.getRole().getName()).collect(Collectors.joining(",")));
        }

        injector.extend(vMainWindow);
    }
}