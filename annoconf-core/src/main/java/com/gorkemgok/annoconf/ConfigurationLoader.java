package com.gorkemgok.annoconf;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gorkem on 04.04.2017.
 */
public class ConfigurationLoader {

    private final Set<Object> confPojoSet;

    private final ConfigOptions configOptions;

    public ConfigurationLoader(ConfigOptions configOptions) {
        this.confPojoSet = new HashSet<>();
        this.configOptions = configOptions;
    }

    public <T> T getConfiguration(Class<T> clazz) {
        for (Object confPojo : confPojoSet) {
            if (clazz.equals(confPojo.getClass())) {
                return (T) confPojo;
            }
        }
        return null;
    }

    public void load() {
        Reflections reflections = new Reflections();

        System.out.println("Scanning configurations...");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Configuration.class);

        for (Class clazz : classSet) {
            System.out.println("Configuration found : "+clazz.getName());
            try {
                Object object = instantiate(clazz.getConstructors()[0]);
                setFields(object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public Object setFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
            ConfParam confParam = field.getAnnotation(ConfParam.class);
            if (confParam != null){
                if (field.getType().equals(String.class)){
                    String value = getStringFromSourceList(confParam);
                    field.setAccessible(true);
                    try {field.set(object, value);} catch (IllegalAccessException e) {e.printStackTrace();}
                    field.setAccessible(false);
                }else if (field.getType().getName().equals("int")){
                    Integer value = getIntegerFromSourceList(confParam);
                    field.setAccessible(true);
                    try {field.set(object, value);} catch (IllegalAccessException e) {e.printStackTrace();}
                    field.setAccessible(false);
                }
            }
        }
        return object;
    }

    public Object instantiate(Constructor constructor) throws InstantiationException {
        List<Object> newParameters = new ArrayList<>();
        Parameter[] constructorParameters = constructor.getParameters();
        for (Parameter constructorParam : constructorParameters) {
            ConfParam confParam = constructorParam.getAnnotation(ConfParam.class);
            if (confParam != null) {
                if (constructorParam.getType().equals(String.class)) {
                    String value = getStringFromSourceList(confParam);
                    newParameters.add(value);
                } else if (constructorParam.getType().getName().equals("int")) {
                    Integer value = getIntegerFromSourceList(confParam);
                    newParameters.add(value);
                }
            }else{
                throw new InstantiationException("All constructor parameters must have ConfParam annotation");
            }
        }

        try {
            Object confPojo = constructor.newInstance(newParameters.toArray());
            confPojoSet.add(confPojo);
            return confPojo;
        } catch (IllegalAccessException e) {
            System.out.println(e);
            throw new InstantiationException();
        } catch (InvocationTargetException e) {
            System.out.println(e);
            throw new InstantiationException();
        }
    }

    public String getStringFromSourceList(ConfParam confParam){
        for (ConfigurationSource configurationSource : configOptions.getSourceList()){
            if (configurationSource.hasValue(confParam)){
                return configurationSource.getString(confParam);
            }
        }
        return confParam.defaultValue();
    }

    public int getIntegerFromSourceList(ConfParam confParam){
        for (ConfigurationSource configurationSource : configOptions.getSourceList()){
            if (configurationSource.hasValue(confParam)){
                return configurationSource.getInt(confParam);
            }
        }
        return Integer.valueOf(confParam.defaultValue());
    }
}
