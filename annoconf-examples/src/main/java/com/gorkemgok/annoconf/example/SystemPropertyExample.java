package com.gorkemgok.annoconf.example;

import com.gorkemgok.annoconf.ConfigOptions;
import com.gorkemgok.annoconf.Config;
import com.gorkemgok.annoconf.ConfigLoader;
import com.gorkemgok.annoconf.example.bean.DbConfig;
import com.gorkemgok.annoconf.example.bean.MemConfig;
import com.gorkemgok.annoconf.source.ConfigSource;
import com.gorkemgok.annoconf.source.impl.PropertyFileSource;
import com.gorkemgok.annoconf.source.impl.SystemPropertySource;

import java.io.IOException;

/**
 * Created by gorkem on 31.03.2017.
 */
public class SystemPropertyExample {

    public static void main(String[] args){
        System.setProperty("test.conf.host", "127.0.0.1");
        System.setProperty("test.conf.port", "5112");

        ConfigOptions configOptions = new ConfigOptions().addSource(new SystemPropertySource());
        try {
            ConfigSource fileCS = new PropertyFileSource("app.properties");
            configOptions.addSource(fileCS);
        } catch (IOException e) {
            System.err.println("ConfigBean file source error:"+e.getMessage());
        }

        Config conf = new ConfigLoader(configOptions).load();

        DbConfig DbConfig = conf.get(DbConfig.class);
        System.out.println(DbConfig.toString());

        MemConfig MemConfig = conf.get(MemConfig.class);
        System.out.println(MemConfig.toString());
    }
}
