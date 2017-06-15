package com.gorkemgok.annoconf;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by gorkem on 09.05.2017.
 */
public class Config {

    private final Map<Class, Object> configPojoSet;

    private final Map<String, Object> configMap;

    private final List<Service> services;

    public Config(Map<Class, Object> configPojoSet, Map<String, Object> configMap, List<Service> services) {
        this.configPojoSet = configPojoSet;
        this.configMap = configMap;
        this.services = services;
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

    public List<Service> getServices() {
        return Collections.unmodifiableList(services);
    }
}
