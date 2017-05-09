package com.gorkemgok.annoconf;

import java.util.Collections;
import java.util.Map;

/**
 * Created by gorkem on 09.05.2017.
 */
public class Config {

    private final Map<Class, Object> configPojoSet;

    private final Map<String, Object> configMap;

    public Config(Map<Class, Object> configPojoSet, Map<String, Object> configMap) {
        this.configPojoSet = configPojoSet;
        this.configMap = configMap;
    }

    public <T> T get(Class<T> clazz) {
        return (T) configPojoSet.get(clazz);
    }

    public Map<Class, Object> getAll(){
        return Collections.unmodifiableMap(configPojoSet);
    }

    public Map<String, Object> getMap() {
        return Collections.unmodifiableMap(configMap);
    }
}
