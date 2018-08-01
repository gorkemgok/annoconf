package com.gorkemgok.annoconf.example.bean;

import com.gorkemgok.annoconf.annotation.ConfigParam;
import com.gorkemgok.annoconf.annotation.ConfigBean;

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

    public DbConfig(@ConfigParam(keys ="test.conf.host", defaultValue = "localhost") String host,
                    @ConfigParam(keys ="test.conf.port", defaultValue = "8080") int port,
                    @ConfigParam(keys ="test.conf.schema") String schema,
                    @ConfigParam(keys ="test.conf.username") String userName,
                    @ConfigParam(keys ="test.conf.password") String password) {

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
