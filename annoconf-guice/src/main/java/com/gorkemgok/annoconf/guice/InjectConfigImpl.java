package com.gorkemgok.annoconf.guice;

import java.io.Serializable;
import java.lang.annotation.Annotation;

/**
 * Created by gorkem on 09.05.2017.
 */
public class InjectConfigImpl implements InjectConfig, Serializable {

    private final String value;

    public InjectConfigImpl(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InjectConfig)){
            return false;
        }else{
            return this.value.equals(((InjectConfig) o).value());
        }
    }

    @Override
    public int hashCode() {
        return 127 * "value".hashCode() ^ this.value.hashCode();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return InjectConfig.class;
    }
}
