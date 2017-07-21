package com.gorkemgok.annoconf;

import com.gorkemgok.annoconf.annotation.LoadService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gorkem on 21.07.2017.
 */
public class ServiceManager {

    private final Set<Service> services;

    public ServiceManager(Set<Service> services) {
        this.services = services;
    }

    public Set<Service> getServiceSet() throws CrossDependencyException {
        Set<Service> serviceSet = new HashSet<>();
        for (Service service : services){
            if (service.isLoadable()) {
                LoadService loadService = service.getInstance().getClass().getAnnotation(LoadService.class);
                if (loadService != null && loadService.autoLoad()) {
                    fillServiceSet(service, new ArrayList<>(), serviceSet);
                }
            }
        }
        return serviceSet;
    }

    private void fillServiceSet(Service service, List<Service> cycleList, Set<Service> resultServiceSet) throws CrossDependencyException{
        if (cycleList.contains(service)){
            throw new CrossDependencyException(service, cycleList);
        }
        cycleList.add(service);
        resultServiceSet.add(service);
        LoadService loadService = service.getInstance().getClass().getAnnotation(LoadService.class);
        if ( null != loadService ){
            String[] depending = loadService.dependingModules();
            for (Service _service : services){
                if (_service.isLoadable()) {
                    if (matchesAny(_service.getInstance().getClass().getAnnotation(LoadService.class), depending)) {
                        fillServiceSet(_service, cycleList, resultServiceSet);
                    }
                }
            }
        }
    }

    private boolean matchesAny(LoadService loadServices, String[] depending){
        if ( null == loadServices ){
            return false;
        }
        for (String dep : depending){
            if (loadServices.name().equals(depending)){
                return true;
            }
            for (String impl : loadServices.implementationOf()){
                if (impl.equals(dep)){
                    return true;
                }
            }
        }
        return false;
    }
}
