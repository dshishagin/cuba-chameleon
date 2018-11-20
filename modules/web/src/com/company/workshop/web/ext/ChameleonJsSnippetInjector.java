package com.company.workshop.web.ext;

import com.vaadin.server.AbstractClientConnector;
import com.vaadin.ui.Window;
import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;

@JavaScript("vaadin://chameleon-init.js")
public class ChameleonJsSnippetInjector extends AbstractJavaScriptExtension {

    @Override
    public void extend(AbstractClientConnector target) {
        super.extend(target);
        callFunction("init", getState().userId, getState().userEmail, getState().userName, getState().userRole);
    }

    @Override
    protected ChameleonJsExtState getState() {
        return (ChameleonJsExtState) super.getState();
    }

    @Override
    protected ChameleonJsExtState getState(boolean markAsDirty) {
        return (ChameleonJsExtState) super.getState(markAsDirty);
    }

    public void setUserId(String id) {
        getState().userId = id;
    }

    public void setUserEmail(String email) {
        getState().userEmail = email;
    }

    public void setUserName(String name) {
        getState().userName = name;
    }

    public void setUserRole(String role) {
        getState().userRole = role;
    }
}
