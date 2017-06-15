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
    private final Config config;

    public AnnoConfModule(ConfigOptions configOptions) {
        this.configOptions = configOptions;
        this.config = new ConfigLoader(configOptions).load();
    }

    public Config unwrapConfig(){
        return config;
    }

    @Override
    protected void configure() {
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
            }else if (value instanceof Long){
                bind(Long.class)
                        .annotatedWith(new InjectConfigImpl(key)).toProvider(() -> (Long)config.getMap().get(key));
            }else if (value instanceof Boolean){
                bind(Boolean.class)
                        .annotatedWith(new InjectConfigImpl(key)).toProvider(() -> (Boolean) config.getMap().get(key));
            }else if (value instanceof Short){
                bind(Short.class)
                        .annotatedWith(new InjectConfigImpl(key)).toProvider(() -> (Short) config.getMap().get(key));
            }else if (value instanceof Byte){
                bind(Byte.class)
                        .annotatedWith(new InjectConfigImpl(key)).toProvider(() -> (Byte) config.getMap().get(key));
            }
        });
    }
}
