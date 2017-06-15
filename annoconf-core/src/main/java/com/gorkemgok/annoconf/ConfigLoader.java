package com.gorkemgok.annoconf;

import com.gorkemgok.annoconf.annotation.*;
import com.gorkemgok.annoconf.source.ConfigSource;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by gorkem on 04.04.2017.
 */
public class ConfigLoader {

    private final ConfigOptions configOptions;

    private Map<String, Object> configMap;

    private Map<Field, Object> configReloadableMap;

    public ConfigLoader(ConfigOptions configOptions) {
        this.configOptions = configOptions;
    }

    public Config load() {
        configMap = new HashMap<>();
        configReloadableMap = new HashMap<>();
        Map<Class, Object> configPojoSet = new HashMap<>();
        for (Class clazz : findConfigClasses()) {
            System.out.println("ConfigBean found : "+clazz.getName());
            try {
                Object confPojo = instantiate(clazz.getConstructors()[0]);
                setFields(confPojo);
                configPojoSet.put(clazz, confPojo);
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        if ( !configReloadableMap.isEmpty() ){
            startReloadTimer();
        }
        Set<Class<?>> serviceClasses = findServiceClasses();
        List<Service> services = new ArrayList<>(serviceClasses.size());
        serviceClasses.forEach(clazz -> {
            try {
                LoadService loadService = clazz.getAnnotation(LoadService.class);
                String key = loadService.ifConfig();
                String value = loadService.equalsTo();
                if ((key.isEmpty() && value.isEmpty())
                        || getStringFromSourceList(ConfigParams.getWithKey(key)).equals(value)){
                    Object serviceInstance = clazz.getConstructor(null).newInstance();
                    services.add(new Service(serviceInstance, loadService.value(), loadService.description()));
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        return new Config(configPojoSet, configMap, services);
    }

    private void startReloadTimer(){
        Timer timer = new Timer("Configuration reload timer", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                configReloadableMap.forEach((key, value) -> reload(key.getAnnotation(ConfigParam.class).key()));
            }
        }, TimeUnit.SECONDS.toMillis(configOptions.getReloadPeriod()), TimeUnit.SECONDS.toMillis(configOptions.getReloadPeriod()));
    }

    public void reload(String key){
        configReloadableMap.entrySet().stream()
                .filter(entry -> entry.getKey().getAnnotation(ConfigParam.class).key().equals(key))
                .forEach(entry -> setField(entry.getKey(), entry.getValue()));
    }

    private Object setFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
           setField(field, object);
        }
        return object;
    }

    private void setField(Field field, Object object){
        ConfigParam configParam = field.getAnnotation(ConfigParam.class);
        if (configParam != null){
            ConfigReloadable configReloadable = field.getAnnotation(ConfigReloadable.class);
            Object value;
            if (configReloadable != null) configReloadableMap.put(field, object);
            if (field.getType().getName().equals("int")){
                value = getIntegerFromSourceList(configParam);
            }else if (field.getType().getName().equals("boolean")){
                value = getBooleanFromSourceList(configParam);
            }else if (field.getType().getName().equals("long")){
                value = getLongFromSourceList(configParam);
            }else if (field.getType().getName().equals("short")){
                value = getShortFromSourceList(configParam);
            }else if (field.getType().getName().equals("byte")){
                value = getByteFromSourceList(configParam);
            }else{
                value = getStringFromSourceList(configParam);
            }
            field.setAccessible(true);
            try {field.set(object, value);} catch (IllegalAccessException e) {e.printStackTrace();}
            field.setAccessible(false);
            configMap.put(configParam.key(), value);
            if ( !configParam.env().isEmpty() ) configMap.put(configParam.env(), value);
        }
    }

    private Object instantiate(Constructor constructor) throws InstantiationException {
        List<Object> newParameters = new ArrayList<>();
        Parameter[] constructorParameters = constructor.getParameters();
        for (Parameter constructorParam : constructorParameters) {
            ConfigParam configParam = constructorParam.getAnnotation(ConfigParam.class);
            if (configParam != null) {
                Object value;
                if (constructorParam.getType().getName().equals("int")) {
                    value = getIntegerFromSourceList(configParam);
                }else if (constructorParam.getType().getName().equals("boolean")) {
                    value = getBooleanFromSourceList(configParam);
                }else if (constructorParam.getType().getName().equals("long")) {
                    value = getLongFromSourceList(configParam);
                }else if (constructorParam.getType().getName().equals("short")) {
                    value = getShortFromSourceList(configParam);
                }else if (constructorParam.getType().getName().equals("byte")) {
                    value = getByteFromSourceList(configParam);
                }else {
                    value = getStringFromSourceList(configParam);
                }
                newParameters.add(value);
                configMap.put(configParam.key(), value);
                if ( !configParam.env().isEmpty() ) configMap.put(configParam.env(), value);
            }else{
                throw new InstantiationException("All constructor parameters must have ConfigParam annotation");
            }
        }

        try {
            Object confPojo = constructor.newInstance(newParameters.toArray());
            return confPojo;
        } catch (IllegalAccessException e) {
            System.out.println(e);
            throw new InstantiationException();
        } catch (InvocationTargetException e) {
            System.out.println(e);
            throw new InstantiationException();
        }
    }

    private String getStringFromSourceList(ConfigParam configParam){
        for (ConfigSource configSource : configOptions.getSourceList()){
            if (configSource.hasValue(configParam)){
                return configSource.getString(configParam);
            }
        }
        return configParam.defaultValue();
    }

    private int getIntegerFromSourceList(ConfigParam configParam){
        return Integer.valueOf(getStringFromSourceList(configParam));
    }

    private long getLongFromSourceList(ConfigParam configParam){
        return Long.valueOf(getStringFromSourceList(configParam));
    }

    private short getShortFromSourceList(ConfigParam configParam){
        return Short.valueOf(getStringFromSourceList(configParam));
    }

    private byte getByteFromSourceList(ConfigParam configParam){
        return Byte.valueOf(getStringFromSourceList(configParam));
    }

    private boolean getBooleanFromSourceList(ConfigParam configParam){
        return Boolean.valueOf(getStringFromSourceList(configParam));
    }

    public Set<Class<?>> findConfigClasses(){

        Reflections reflections;
        if (configOptions.getScan() != null){
            reflections = new Reflections(configOptions.getScan());
        }else{
            reflections = new Reflections();
        }

        System.out.println("Scanning configurations...");
        Set<Class<?>> clazzSet = reflections.getTypesAnnotatedWith(ConfigBean.class);
        return clazzSet;
    }

    public Set<Class<?>> findServiceClasses(){

        Reflections reflections;
        if (configOptions.getScan() != null){
            reflections = new Reflections(configOptions.getScan());
        }else{
            reflections = new Reflections();
        }

        System.out.println("Scanning services...");
        Set<Class<?>> clazzSet = reflections.getTypesAnnotatedWith(LoadService.class);
        return clazzSet;
    }
}
