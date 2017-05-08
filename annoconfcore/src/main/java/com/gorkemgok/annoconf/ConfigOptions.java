package com.gorkemgok.annoconf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gorkem on 04.04.2017.
 */
public class ConfigOptions {

    private List<ConfigurationSource> configurationSourceList;

    public ConfigOptions() {
        this.configurationSourceList = new ArrayList<>();
    }

    public ConfigOptions addSource(ConfigurationSource configurationSource){
        configurationSourceList.add(configurationSource);
        return this;
    }

    public List<ConfigurationSource> getSourceList(){
        return Collections.unmodifiableList(configurationSourceList);
    }
}
