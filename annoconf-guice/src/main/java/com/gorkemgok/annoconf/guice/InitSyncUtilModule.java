package com.gorkemgok.annoconf.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * Created by gorkem on 05.06.2017.
 */
public class InitSyncUtilModule extends AbstractModule {
    @Override
    protected void configure() {
        //Bind Listener for Initiables
        bindListener(new GuiceSubclassMatcher(Initiable.class), new TypeListener() {
            @Override
            public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
                typeEncounter.register((InjectionListener<I>) i -> {
                    Initiable initiable = (Initiable) i;
                    initiable.init();
                });
            }
        });

        //Bind Listener for Syncables
        bindListener(new GuiceSubclassMatcher(Syncable.class), new TypeListener() {
            @Override
            public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
                typeEncounter.register((InjectionListener<I>) i -> {
                    Syncable syncable = (Syncable) i;
                    //TODO: put to sync list
                });
            }
        });

        //Bind Listener for Terminatables
        bindListener(new GuiceSubclassMatcher(Terminatable.class), new TypeListener() {
            @Override
            public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
                typeEncounter.register((InjectionListener<I>) i -> {
                    Terminatable terminatable = (Terminatable) i;
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> {terminatable.terminate();}));
                });
            }
        });
    }
}
