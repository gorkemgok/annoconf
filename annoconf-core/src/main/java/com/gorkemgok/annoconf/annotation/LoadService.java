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

    String value() default "";

    String description() default "";

    String ifConfig() default "";

    String equalsTo() default "";
}
