package com.gorkemgok.annoconf.guice;

import com.google.inject.AbstractModule;
import com.gorkemgok.annoconf.Config;
import com.gorkemgok.annoconf.ConfigLoader;
import com.gorkemgok.annoconf.ConfigOptions;

import java.util.Map;

/**
 * Created by gorkem on 09.05.2017.
 */
public class AnnoConfGuiceModule extends AbstractModule {

    private final ConfigOptions configOptions;

    public AnnoConfGuiceModule(ConfigOptions configOptions) {
        this.configOptions = configOptions;
    }

    @Override
    protected void configure() {
        Config config = new ConfigLoader(configOptions).load();
        Map<Class, Object> configPojoMap = config.getAll();
        bind(Config.class).toInstance(config);

        configPojoMap.forEach((key, value) -> bind(key).toInstance(value));

        Map<String, Object> configMap = config.getMap();

        //bindConstant().annotatedWith(new InjectConfigImpl("TEST")).to(500);
        configMap.forEach((key, value) -> {
            if (value instanceof String){
                bindConstant().annotatedWith(new InjectConfigImpl(key)).to((String) value);
            }else if (value instanceof Integer){
                bindConstant().annotatedWith(new InjectConfigImpl(key)).to((Integer) value);
            }
        });

    }

}
