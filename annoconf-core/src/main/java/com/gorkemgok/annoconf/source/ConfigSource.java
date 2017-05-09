package com.gorkemgok.annoconf.source;

import com.gorkemgok.annoconf.annotation.ConfigParam;

/**
 * Created by gorkem on 31.03.2017.
 */
public interface ConfigSource {

    boolean hasValue(ConfigParam configParam);

    String getString(ConfigParam configParam);

    Integer getInt(ConfigParam configParam);

}
