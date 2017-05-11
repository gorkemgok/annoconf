package com.gorkemgok.annoconf.example;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.gorkemgok.annoconf.example.bean.DbConfig;
import com.gorkemgok.annoconf.guice.AnnoConfModule;
import com.gorkemgok.annoconf.ConfigOptions;
import com.gorkemgok.annoconf.guice.InjectConfig;
import com.gorkemgok.annoconf.guice.InjectConfigImpl;

/**
 * Created by gorkem on 09.05.2017.
 */
public class GuiceInjectConfigExample {

    public static class TestClass{
        private final DbConfig dbConfig;
        private final String host;
        private final int port;

        @Inject
        public TestClass(DbConfig dbConfig,
                         @InjectConfig("test.conf.host") String host,
                         @InjectConfig("test.conf.port") int port) {
            this.dbConfig = dbConfig;
            this.port = port;
            this.host = host;
        }
    }

    public static void main(String[] args){
        System.setProperty("test.conf.host", "127.0.0.1");
        System.setProperty("test.conf.port", "5112");

        InjectConfig injectConfig = (InjectConfig)TestClass.class.getConstructors()[0].getParameterAnnotations()[1][0];
        boolean hi = new InjectConfigImpl("test.conf.host").equals(injectConfig);
        System.out.println(hi);

        AnnoConfModule module =
                new AnnoConfModule(ConfigOptions.withSystemPropertySource().setScanPackage("com.gorkemgok.annoconf.example.bean"));
        Injector injector = Guice.createInjector(module);

        TestClass testClass = injector.getInstance(TestClass.class);

        System.out.println(testClass.dbConfig.host);
        System.out.println(testClass.host);
        System.out.println(testClass.port);

    }
}
