package com.company.workshop.web.client;

import com.company.workshop.entity.Client;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;

import javax.inject.Inject;
import java.util.Map;

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