package com.gorkemgok.annoconf.source;

import com.gorkemgok.annoconf.AbstractConfigurationSource;
import com.gorkemgok.annoconf.ConfParam;

/**
 * Created by gorkem on 31.03.2017.
 */
public class SystemPropertySource extends AbstractConfigurationSource {


    @Override
    public boolean hasValue(ConfParam confParam) {
        return System.getProperty(confParam.key()) != null;
    }

    @Override
    public String getString(ConfParam confParam) {
        String value = System.getProperty(confParam.key());
        if ( value != null ){
            return value;
        }
        return confParam.defaultValue();
    }

}
