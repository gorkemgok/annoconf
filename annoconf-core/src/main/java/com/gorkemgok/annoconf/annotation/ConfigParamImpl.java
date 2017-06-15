package com.gorkemgok.annoconf.annotation;

import java.lang.annotation.Annotation;

/**
 * Created by gorkem on 15.06.2017.
 */
public class ConfigParamImpl implements ConfigParam{
    private final String key;
    private final String env;

    public ConfigParamImpl(String key, String env) {
        this.key = key;
        this.env = env;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String env() {
        return env;
    }

    @Override
    public String defaultValue() {
        return "";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return ConfigParam.class;
    }
}
