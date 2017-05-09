package com.gorkemgok.annoconf.examples;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.gorkemgok.annoconf.examples.bean.DbConfig;
import com.gorkemgok.annoconf.guice.AnnoConfGuiceModule;
import com.gorkemgok.annoconf.ConfigOptions;
import com.gorkemgok.annoconf.guice.InjectConfig;
import com.gorkemgok.annoconf.guice.InjectConfigImpl;

/**
 * Created by gorkem on 09.05.2017.
 */
public class GuiceInjectConfigExample {

    public static class TestClass{
        private final DbConfig dbConfig;
        private final int port;

        @Inject
        public TestClass(DbConfig dbConfig, @InjectConfig("test.conf.host") String host,  @InjectConfig("TEST") Integer port) {
            this.dbConfig = dbConfig;
            this.port = port;
        }
    }

    public static void main(String[] args){
        System.setProperty("test.conf.host", "127.0.0.1");
        System.setProperty("test.conf.port", "5112");

        InjectConfig injectConfig = (InjectConfig)TestClass.class.getConstructors()[0].getParameterAnnotations()[1][0];
        boolean hi = new InjectConfigImpl("TEST").equals(injectConfig);
        System.out.println(hi);

        Injector injector =
                Guice.createInjector(new AnnoConfGuiceModule(ConfigOptions.SYSTEM_PROPERTY));

        TestClass testClass = injector.getInstance(TestClass.class);

        System.out.println(testClass.dbConfig.host);
        System.out.println(testClass.port);
    }
}
