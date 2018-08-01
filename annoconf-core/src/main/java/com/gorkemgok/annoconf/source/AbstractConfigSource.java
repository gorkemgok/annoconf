package com.gorkemgok.annoconf.source;

import com.gorkemgok.annoconf.annotation.ConfigParam;

import java.util.Optional;

/**
 * Created by gorkem on 31.03.2017.
 */
public abstract class AbstractConfigSource implements ConfigSource {

    @Override
    public Optional<Integer> getInt(ConfigParam configParam) {
        return getString(configParam).map(Integer::valueOf);
    }

}
