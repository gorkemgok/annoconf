package com.gorkemgok.annoconf.annotation;

/**
 * Created by gorkem on 15.06.2017.
 */
public class ConfigParams {

    public static ConfigParam getWithKey(String key, String defaultValue){
        return new ConfigParamImpl(new String[]{key}, defaultValue);
    }

}
