package com.gorkemgok.annoconf.source;

import com.gorkemgok.annoconf.AbstractConfigurationSource;
import com.gorkemgok.annoconf.ConfParam;

/**
 * Created by gorkem on 04.04.2017.
 */
public class EnvironmentVarSource extends AbstractConfigurationSource {

    @Override
    public boolean hasValue(ConfParam confParam) {
        return System.getenv().get(confParam.env()) != null;
    }

    @Override
    public String getString(ConfParam confParam) {
        return System.getenv().get(confParam.env());
    }
}
