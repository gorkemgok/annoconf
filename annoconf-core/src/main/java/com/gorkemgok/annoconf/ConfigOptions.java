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

    public static final ConfigOptions SYSTEM_PROPERTY = new ConfigOptions().addSource(new SystemPropertySource());

    private List<ConfigSource> configSourceList;

    public ConfigOptions() {
        this.configSourceList = new ArrayList<>();
    }

    public ConfigOptions addSource(ConfigSource configSource){
        configSourceList.add(configSource);
        return this;
    }

    public List<ConfigSource> getSourceList(){
        return Collections.unmodifiableList(configSourceList);
    }
}
