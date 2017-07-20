package com.gorkemgok.annoconf.test.package1;

import com.gorkemgok.annoconf.annotation.ConfigBean;
import com.gorkemgok.annoconf.annotation.ConfigParam;

/**
 * Created by gorkem on 20.07.2017.
 */
@ConfigBean
public class TestBean01 {

    public final String prop1;

    public final String prop2;

    public TestBean01(
            @ConfigParam(key = "test.prop1") String prop1,
            @ConfigParam(key = "test.prop2") String prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;
    }
}
