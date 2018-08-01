package com.gorkemgok.annoconf.example.bean;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.annotation.ConfigBean;
import com.gorkemgok.annoconf.annotation.ConfigReloadable;

/**
 * Created by gorkem on 04.04.2017.
 */
@ConfigBean
public class MemConfig {

    @ConfigParam(keys ="annoconf.example.maxmem", defaultValue = "1G")
    private String max;

    @ConfigParam(keys ="annoconf.example.minmem", defaultValue = "256")
    @ConfigReloadable
    private int min;

    public String getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

}
