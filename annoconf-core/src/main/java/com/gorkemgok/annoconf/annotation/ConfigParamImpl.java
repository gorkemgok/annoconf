package com.gorkemgok.annoconf.annotation;

import java.lang.annotation.Annotation;

/**
 * Created by gorkem on 15.06.2017.
 */
public class ConfigParamImpl implements ConfigParam{
    private final String[] keys;
    private final String defaultValue;

    public ConfigParamImpl(String[] keys, String defaultValue) {
        this.keys = keys;
        this.defaultValue = defaultValue;
    }

    @Override
    public String[] keys() {
        return keys;
    }

    @Override
    public String defaultValue() {
        return defaultValue;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return ConfigParam.class;
    }
}
