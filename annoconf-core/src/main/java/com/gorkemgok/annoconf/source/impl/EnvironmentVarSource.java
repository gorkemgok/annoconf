package com.gorkemgok.annoconf.source.impl;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.source.AbstractConfigSource;

/**
 * Created by gorkem on 04.04.2017.
 */
public class EnvironmentVarSource extends AbstractConfigSource {

    @Override
    public boolean hasValue(ConfigParam configParam) {
        return System.getenv().get(configParam.env()) != null;
    }

    @Override
    public String getString(ConfigParam configParam) {
        return System.getenv().get(configParam.env());
    }
}
