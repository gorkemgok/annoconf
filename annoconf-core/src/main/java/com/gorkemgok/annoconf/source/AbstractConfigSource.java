package com.gorkemgok.annoconf.source;

import com.gorkemgok.annoconf.annotation.ConfigParam;

/**
 * Created by gorkem on 31.03.2017.
 */
public abstract class AbstractConfigSource implements ConfigSource {

    @Override
    public Integer getInt(ConfigParam configParam) {
        String value = getString(configParam);
        return Integer.valueOf(value);
    }

}
