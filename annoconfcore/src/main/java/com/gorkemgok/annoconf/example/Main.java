package com.gorkemgok.annoconf.example;

import com.gorkemgok.annoconf.ConfigOptions;
import com.gorkemgok.annoconf.ConfigurationLoader;
import com.gorkemgok.annoconf.ConfigurationSource;
import com.gorkemgok.annoconf.source.PropertyFileSource;
import com.gorkemgok.annoconf.source.SystemPropertySource;

import java.io.IOException;

/**
 * Created by gorkem on 31.03.2017.
 */
public class Main {

    public static void main(String[] args){
        System.setProperty("test.conf.host", "127.0.0.1");
        System.setProperty("test.conf.port", "5112");

        ConfigOptions configOptions = new ConfigOptions().addSource(new SystemPropertySource());
        try {
            ConfigurationSource fileCS = new PropertyFileSource("app.properties");
            configOptions.addSource(fileCS);
        } catch (IOException e) {
            System.err.println("Configuration file source error:"+e.getMessage());
        }

        ConfigurationLoader cl = new ConfigurationLoader(configOptions);
        cl.load();

        DBConf DBConf = cl.getConfiguration(DBConf.class);
        System.out.println(DBConf.toString());

        DBConf2 DBConf2 = cl.getConfiguration(DBConf2.class);
        System.out.println(DBConf2.toString());
    }
}
