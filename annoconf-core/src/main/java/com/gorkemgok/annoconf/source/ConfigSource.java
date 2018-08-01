package com.gorkemgok.annoconf.source;

import com.gorkemgok.annoconf.annotation.ConfigParam;

import java.util.Optional;

/**
 * Created by gorkem on 31.03.2017.
 */
public interface ConfigSource {

    default Optional<String> getString(ConfigParam configParam) {
        return getString(configParam.keys());
    }

    Optional<String> getString(String[] keys);

    Optional<Integer> getInt(ConfigParam configParam);

}
