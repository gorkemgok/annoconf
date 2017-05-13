package com.gorkemgok.annoconf.source.impl;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.source.AbstractConfigSource;

/**
 * Created by hasan on 13.05.2017.
 */
public class ClasspathFileSource extends AbstractConfigSource {
    @Override
    public boolean hasValue(ConfigParam configParam) {
        return false;
    }

    @Override
    public String getString(ConfigParam configParam) {
        return null;
    }
}
