package com.company.workshop.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.DefaultBoolean;

@Source(type = SourceType.DATABASE)
public interface ChameleonConfig extends Config {

    @Property("application.chameleonEnabled")
    @DefaultBoolean(false)
    boolean isChameleonEnabled();
}