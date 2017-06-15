package com.gorkemgok.annoconf;

/**
 * Created by gorkem on 15.06.2017.
 */
public class Service {

    private final Object instance;

    private final String name;

    private final String description;

    public Service(Object intsance, String name, String description) {
        this.instance = intsance;
        this.name = name;
        this.description = description;
    }

    public Object getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLoadable(){
        return instance != null;
    }

    @Override
    public String toString() {
        return String.format("Service : %s (%s)", name, description);
    }
}
