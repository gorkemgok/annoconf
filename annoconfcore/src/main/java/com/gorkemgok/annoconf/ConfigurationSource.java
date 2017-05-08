package com.gorkemgok.annoconf;

/**
 * Created by gorkem on 31.03.2017.
 */
public interface ConfigurationSource {

    boolean hasValue(ConfParam confParam);

    String getString(ConfParam confParam);

    Integer getInt(ConfParam confParam);

}
