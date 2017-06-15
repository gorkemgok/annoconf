package com.gorkemgok.annoconf;

/**
 * Created by gorkem on 15.06.2017.
 */
public class Service {

    private final Object intance;

    private final String name;

    private final String decription;

    public Service(Object intance, String name, String decription) {
        this.intance = intance;
        this.name = name;
        this.decription = decription;
    }

    public Object getIntance() {
        return intance;
    }

    public String getName() {
        return name;
    }

    public String getDecription() {
        return decription;
    }

    @Override
    public String toString() {
        return String.format("Service : %s (%s)", name, decription);
    }
}
