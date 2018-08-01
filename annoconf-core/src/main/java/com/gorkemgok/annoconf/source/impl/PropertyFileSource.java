package com.gorkemgok.annoconf.source.impl;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.source.AbstractConfigSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Created by gorkem on 04.04.2017.
 */
public class PropertyFileSource extends AbstractConfigSource {

    private final Properties properties;

    public PropertyFileSource(File file) throws IOException {
        properties = new Properties();
        try (InputStream is = new FileInputStream(file)){
            properties.load(is);
        }
    }

    public PropertyFileSource(String fileName) throws IOException {
        this(new File(fileName));
    }

    @Override
    public Optional<String> getString(String[] keys) {
        return Stream.of(keys)
                .map(properties::getProperty)
                .filter(Objects::nonNull)
                .findFirst();
    }
}
