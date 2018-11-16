package com.company.workshop.web.sparepart;

import com.company.workshop.entity.SparePart;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

public class SparePartBrowse extends AbstractLookup {
    @Inject
    private Table<SparePart> sparePartsTable;

    private final Logger log = LoggerFactory.getLogger(SparePartBrowse.class);

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        sparePartsTable.setStyleProvider((sparePart, property) -> {
            if (property != null) {
                log.debug("Current property = " + property);
            }
            // setting a custom style just for "Title" cell
            // "Title" cell will be rendered as
            // <td class="v-table-cell-content v-table-cell-content-cs cs
            //            chameleon-cell-spare-part-01e236cc-0c88-8400-56cb-409df8e21408"
            //            style="user-select: none; width: 314px;">...</td>
            if (property != null && property.equals("title") && sparePart != null) {
                return "chameleon-cell-spare-part-" + sparePart.getId();
            } else {
                return "my-chameleon";
            }
        });
    }
}