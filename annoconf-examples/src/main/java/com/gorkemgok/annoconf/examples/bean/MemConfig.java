package com.gorkemgok.annoconf.examples.bean;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.annotation.ConfigBean;

/**
 * Created by gorkem on 04.04.2017.
 */
@ConfigBean
public class MemConfig {

    @ConfigParam(key="annoconf.examples.maxmem", defaultValue = "1G")
    private String max;

    @ConfigParam(key="annoconf.examples.minmem", defaultValue = "256")
    private int min;

    public String getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

}
