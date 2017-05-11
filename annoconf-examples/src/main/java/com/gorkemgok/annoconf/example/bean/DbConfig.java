package com.gorkemgok.annoconf.example.bean;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.annotation.ConfigBean;
import com.gorkemgok.annoconf.annotation.ConfigReloadable;

/**
 * Created by gorkem on 31.03.2017.
 */
@ConfigBean
public class DbConfig {

    public final String host;

    public final int port;

    public final String schema;

    public final String userName;

    public final String passsword;

    public DbConfig(@ConfigParam(key="test.conf.host", defaultValue = "localhost") String host,
                    @ConfigParam(key="test.conf.port", defaultValue = "8080") int port,
                    @ConfigParam(key="test.conf.schema") String schema,
                    @ConfigParam(key="test.conf.username") String userName,
                    @ConfigParam(key="test.conf.password") String password) {

        this.host = host;
        this.port = port;
        this.schema = schema;
        this.userName = userName;
        this.passsword = password;
    }

    @Override
    public String toString() {
        return "DbConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", schema='" + schema + '\'' +
                ", userName='" + userName + '\'' +
                ", passsword='" + passsword + '\'' +
                '}';
    }
}
