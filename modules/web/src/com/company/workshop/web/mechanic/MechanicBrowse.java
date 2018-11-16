package com.company.workshop.web.mechanic;

import com.company.workshop.entity.Mechanic;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;

import javax.inject.Inject;
import java.util.Map;

public class MechanicBrowse extends AbstractLookup {
    @Inject
    private Table<Mechanic> mechanicsTable;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        mechanicsTable.setStyleProvider((mechanic, property) -> {
            if (property == null && mechanic != null) {
                return "chameleon-mechanic-" + mechanic.getId();
            } else {
                return null;
            }
        });
    }
}