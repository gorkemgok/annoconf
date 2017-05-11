package com.gorkemgok.annoconf.guice;

import com.google.inject.AbstractModule;
import com.gorkemgok.annoconf.Config;
import com.gorkemgok.annoconf.ConfigLoader;
import com.gorkemgok.annoconf.ConfigOptions;

import java.util.Map;

/**
 * Created by gorkem on 09.05.2017.
 */
public class AnnoConfModule extends AbstractModule {

    private final ConfigOptions configOptions;

    public AnnoConfModule(ConfigOptions configOptions) {
        this.configOptions = configOptions;
    }

    @Override
    protected void configure() {
        Config config = new ConfigLoader(configOptions).load();
        Map<Class, Object> configPojoMap = config.getAll();
        bind(Config.class).toInstance(config);

        configPojoMap.forEach((key, value) -> bind(key).toInstance(value));

        Map<String, Object> configMap = config.getMap();

        configMap.forEach((key, value) -> {
            if (value instanceof String){
                bind(String.class)
                        .annotatedWith(new InjectConfigImpl(key)).toProvider(() -> (String)config.getMap().get(key));
            }else if (value instanceof Integer){
                bind(Integer.class)
                        .annotatedWith(new InjectConfigImpl(key)).toProvider(() -> (Integer)config.getMap().get(key));
            }
        });
    }
}
