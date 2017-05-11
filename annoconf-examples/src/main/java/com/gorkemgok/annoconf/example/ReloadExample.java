package com.gorkemgok.annoconf.example;

import com.gorkemgok.annoconf.Config;
import com.gorkemgok.annoconf.ConfigLoader;
import com.gorkemgok.annoconf.ConfigOptions;
import com.gorkemgok.annoconf.example.bean.MemConfig;

import java.util.concurrent.TimeUnit;

/**
 * Created by gorkem on 11.05.2017.
 */
public class ReloadExample {

    public static void main(String[] args){
        System.setProperty("annoconf.example.maxmem", "1G");
        System.setProperty("annoconf.example.minmem", "500");
        Config config = new ConfigLoader(ConfigOptions.withSystemPropertySource().setReloadPeriod(1)).load();
        MemConfig memConfig = config.get(MemConfig.class);

        System.out.println(memConfig.getMax());
        System.out.println(memConfig.getMin());

        System.setProperty("annoconf.example.maxmem", "2G");
        System.setProperty("annoconf.example.minmem", "1000");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(memConfig.getMax());
        System.out.println(memConfig.getMin());
    }
}
