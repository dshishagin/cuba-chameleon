/**
 * Extending editWindowActions frame to help Chameleon tool to locate OK & Close buttons
 * see https://www.cuba-platform.com/discuss/t/edit-extended-windows-button/510 for details
 * of extending standard CUBA frames.
 */
package com.company.workshop.web.frames;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Button;

import javax.inject.Inject;
import java.util.Map;

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