package com.gorkemgok.annoconf.source.impl;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.source.AbstractConfigSource;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by gorkem on 31.03.2017.
 */
public class SystemPropertySource extends AbstractConfigSource {

    @Override
    public Optional<String> getString(String[] keys) {
        return Stream.of(keys)
                .map(System::getProperty)
                .filter(Objects::nonNull)
                .findFirst();
    }

}
