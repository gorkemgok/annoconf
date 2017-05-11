package com.gorkemgok.annoconf;

import com.gorkemgok.annoconf.source.ConfigSource;
import com.gorkemgok.annoconf.source.impl.SystemPropertySource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gorkem on 04.04.2017.
 */
public class ConfigOptions {

    private List<ConfigSource> configSourceList;

    private String scan;

    private long reloadPeriod = 60;

    public static ConfigOptions withSystemPropertySource(){
        return new ConfigOptions().addSource(new SystemPropertySource());
    }

    public ConfigOptions() {
        this.configSourceList = new ArrayList<>();
    }

    public ConfigOptions addSource(ConfigSource configSource){
        configSourceList.add(configSource);
        return this;
    }

    public ConfigOptions setScanPackage(String scan){
        this.scan = scan;
        return this;
    }

    public ConfigOptions setReloadPeriod(long reloadPeriod) {
        this.reloadPeriod = reloadPeriod;
        return this;
    }

    public String getScan() {
        return scan;
    }

    public long getReloadPeriod() {
        return reloadPeriod;
    }

    public List<ConfigSource> getSourceList(){
        return Collections.unmodifiableList(configSourceList);
    }
}
