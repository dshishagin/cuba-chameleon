package com.company.workshop.web.order;

import com.company.workshop.entity.Order;
import com.company.workshop.entity.OrderStatus;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class OrderBrowse extends AbstractLookup {

    @Inject
    private CollectionDatasource<Order, UUID> ordersDs;

    @Inject
    private Table<Order> ordersTable;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        ordersTable.setStyleProvider((order, property) -> {
            if (property == null && order != null) {
                return "chameleon-order-" + order.getId();
            } else {
                return null;
            }
        });
    }

    public void onBtnSetNewStatusClick(Component source) {
        Order o = ordersDs.getItem();
        if (o != null) {
            o.setStatus(OrderStatus.NEW);
            ordersDs.commit();
        }
    }

    public void onBtnSetReadyStatusClick(Component source) {
        Order o = ordersDs.getItem();
        if (o != null) {
            o.setStatus(OrderStatus.READY);
            ordersDs.commit();
        }
    }

    public void onBtnSetInProgressStatusClick(Component source) {
        Order o = ordersDs.getItem();
        if (o != null) {
            o.setStatus(OrderStatus.IN_PROGRESS);
            ordersDs.commit();
        }
    }
}