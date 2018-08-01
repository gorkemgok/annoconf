package com.gorkemgok.annoconf.source.impl;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.source.AbstractConfigSource;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by gorkem on 04.04.2017.
 */
public class EnvironmentVarSource extends AbstractConfigSource {

    @Override
    public Optional<String> getString(String[] keys) {
        return Stream.of(keys)
                .map(System.getenv()::get)
                .filter(Objects::nonNull)
                .findFirst();
    }
}
