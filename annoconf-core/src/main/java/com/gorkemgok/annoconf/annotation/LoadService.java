package com.gorkemgok.annoconf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gorkem on 15.06.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadService {

    boolean autoLoad() default false;

    String value() default "";

    String name() default "";

    String[] dependingModules() default {};

    String[] implementationOf() default {};

    int order() default 50;

    String description() default "";

    String[] ifConfig() default {};

    String equalsTo() default "";
}
