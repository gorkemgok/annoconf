package com.gorkemgok.annoconf;

import com.gorkemgok.annoconf.test.package1.TestBean01;
import com.gorkemgok.annoconf.test.package2.TestBean02;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.*;

/**
 * Created by gorkem on 20.07.2017.
 */
public class ConfigLoaderTest {

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("test.prop1", "value1");
        System.setProperty("test.prop2", "value2");
        System.setProperty("test.prop3", "value3");
    }

    @Test
    public void testLoad() throws Exception {
        ConfigOptions configOptions = ConfigOptions.withSystemPropertySource()
                .addScanPackage("com.gorkemgok.annoconf.test.package1")
                .addScanPackage("com.gorkemgok.annoconf.test.package2");
        ConfigLoader configLoader = new ConfigLoader(configOptions);
        Config config = configLoader.load();

        TestBean01 testBean01 = config.get(TestBean01.class);
        TestBean02 testBean02 = config.get(TestBean02.class);

        Set<Service> serviceSet = config.getServices();

        assertEquals(2, serviceSet.size());

        assertNotNull(testBean01);
        assertNotNull(testBean02);
    }

}