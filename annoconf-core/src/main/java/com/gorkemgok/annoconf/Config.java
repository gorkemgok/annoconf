package com.gorkemgok.annoconf;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gorkem on 09.05.2017.
 */
public class Config {

    private final Map<Class, Object> configPojoSet;

    private final Map<String, Object> configMap;

    private final Set<Service> services;

    public Config(Map<Class, Object> configPojoSet, Map<String, Object> configMap, Set<Service> services) {
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

    public Set<Service> getServices() {
        return Collections.unmodifiableSet(services);
    }
}
