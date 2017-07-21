package com.gorkemgok.annoconf;

import java.util.List;

/**
 * Created by gorkem on 21.07.2017.
 */
public class CrossDependencyException extends Exception{

    private final Service serviceName;

    private final List<Service> cycList;

    public CrossDependencyException(Service serviceName, List<Service> cycList) {
        this.serviceName = serviceName;
        this.cycList = cycList;
    }

    public Service getServiceName() {
        return serviceName;
    }

    public List<Service> getCycList() {
        return cycList;
    }
}
