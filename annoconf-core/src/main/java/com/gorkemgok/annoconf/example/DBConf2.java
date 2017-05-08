package com.gorkemgok.annoconf.example;

import com.gorkemgok.annoconf.ConfParam;
import com.gorkemgok.annoconf.Configuration;

/**
 * Created by gorkem on 04.04.2017.
 */
@Configuration
public class DBConf2 {

    @ConfParam(key="test.conf.host", defaultValue = "localhost")
    private String host;

    @ConfParam(key="test.conf.port", defaultValue = "5112")
    private int port;

    @ConfParam(key="test.conf.schema")
    private String schema;

    @ConfParam(key="test.conf.username")
    private String userName;

    @ConfParam(key="test.conf.password")
    private String passsword;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getSchema() {
        return schema;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasssword() {
        return passsword;
    }

    @Override
    public String toString() {
        return "DBConf2{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", schema='" + schema + '\'' +
                ", userName='" + userName + '\'' +
                ", passsword='" + passsword + '\'' +
                '}';
    }
}
