package com.gorkemgok.annoconf;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by gorkem on 21.07.2017.
 */
public class ServiceManagerTest {
    private Config configOne;
    private Config configTwo;
    @BeforeMethod
    public void setUp() throws Exception {
        ConfigLoader configLoaderOne = new ConfigLoader(
                new ConfigOptions().addScanPackage("com.gorkemgok.annoconf.test.dep.one")
        );
        configOne = configLoaderOne.load();

        ConfigLoader configLoaderTwo = new ConfigLoader(
                new ConfigOptions().addScanPackage("com.gorkemgok.annoconf.test.dep.twq")
        );
        configTwo = configLoaderTwo.load();
    }

    @Test
    public void testOne() throws Exception {
        ServiceManager serviceManagerOne = new ServiceManager(configOne);

        Set<Service> serviceSetOne = serviceManagerOne.getServiceSet();
        Assert.assertEquals(5, serviceSetOne.size());
    }

}