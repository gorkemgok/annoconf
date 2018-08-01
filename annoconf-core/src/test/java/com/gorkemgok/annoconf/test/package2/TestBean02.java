package com.gorkemgok.annoconf.test.package2;

import com.gorkemgok.annoconf.annotation.ConfigBean;
import com.gorkemgok.annoconf.annotation.ConfigParam;

/**
 * Created by gorkem on 20.07.2017.
 */
@ConfigBean
public class TestBean02 {

    public final String prop3;

    public TestBean02(
            @ConfigParam(keys = "test.prop1") String prop3) {
        this.prop3 = prop3;
    }
}
