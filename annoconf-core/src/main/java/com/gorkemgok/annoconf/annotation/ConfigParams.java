package com.gorkemgok.annoconf.annotation;

/**
 * Created by gorkem on 15.06.2017.
 */
public class ConfigParams {

    public static ConfigParam getWithKey(String key){
        return new ConfigParamImpl(key, "");
    }

    public static ConfigParam getWithEnv(String env){
        return new ConfigParamImpl("", env);
    }

    public static ConfigParam get(String key, String env){
        return new ConfigParamImpl(key, env);
    }
}
