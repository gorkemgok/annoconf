package com.gorkemgok.annoconf;

/**
 * Created by gorkem on 31.03.2017.
 */
public abstract class AbstractConfigurationSource implements ConfigurationSource {

    @Override
    public Integer getInt(ConfParam confParam) {
        String value = getString(confParam);
        return Integer.valueOf(value);
    }

}
