package com.gorkemgok.annoconf.source;

import com.gorkemgok.annoconf.AbstractConfigurationSource;
import com.gorkemgok.annoconf.ConfParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by gorkem on 04.04.2017.
 */
public class PropertyFileSource extends AbstractConfigurationSource {

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
    public boolean hasValue(ConfParam confParam) {
        return properties.getProperty(confParam.key()) != null;
    }

    @Override
    public String getString(ConfParam confParam) {
        return properties.getProperty(confParam.key());
    }
}
