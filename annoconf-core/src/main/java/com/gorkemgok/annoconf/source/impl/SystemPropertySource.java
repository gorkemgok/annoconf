package com.gorkemgok.annoconf.source.impl;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.source.AbstractConfigSource;

/**
 * Created by gorkem on 31.03.2017.
 */
public class SystemPropertySource extends AbstractConfigSource {


    @Override
    public boolean hasValue(ConfigParam configParam) {
        return System.getProperty(configParam.key()) != null;
    }

    @Override
    public String getString(ConfigParam configParam) {
        String value = System.getProperty(configParam.key());
        if ( value != null ){
            return value;
        }
        return configParam.defaultValue();
    }

}
