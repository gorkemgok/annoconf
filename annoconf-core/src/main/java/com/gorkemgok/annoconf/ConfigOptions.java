package com.gorkemgok.annoconf;

import com.gorkemgok.annoconf.source.ConfigSource;
import com.gorkemgok.annoconf.source.impl.SystemPropertySource;

import java.util.*;

/**
 * Created by gorkem on 04.04.2017.
 */
public class ConfigOptions {

    private Set<ConfigSource> configSourceList;

    private Set<String> scanPackages;

    private Map<String, String> defaultsMap;

    private long reloadPeriod = 60;

    public static ConfigOptions withSystemPropertySource(){
        return new ConfigOptions().addSource(new SystemPropertySource());
    }

    public ConfigOptions() {
        this.configSourceList = new HashSet<>();
        this.scanPackages = new HashSet<>();
        this.defaultsMap = new HashMap<>();
    }

    public ConfigOptions addDefault(String key, String defaultValue) {
        this.defaultsMap.put(key, defaultValue);
        return this;
    }

    public ConfigOptions addDefaults(Map<String, String> defaultsMap) {
        this.defaultsMap.putAll(defaultsMap);
        return this;
    }

    public ConfigOptions addSource(ConfigSource configSource){
        configSourceList.add(configSource);
        return this;
    }

    public ConfigOptions addScanPackage(String scanPackage){
        scanPackages.add(scanPackage);
        return this;
    }

    public ConfigOptions addScanPackages(Collection<String> scanPackages){
        this.scanPackages.addAll(scanPackages);
        return this;
    }

    public ConfigOptions setReloadPeriod(long reloadPeriod) {
        this.reloadPeriod = reloadPeriod;
        return this;
    }

    public Collection<String> getScanPackages() {
        return Collections.unmodifiableSet(scanPackages);
    }

    public long getReloadPeriod() {
        return reloadPeriod;
    }

    public Collection<ConfigSource> getSourceList(){
        return Collections.unmodifiableSet(configSourceList);
    }

    public Map<String, String> getDefaultsMap() {
        return Collections.unmodifiableMap(defaultsMap);
    }
}
