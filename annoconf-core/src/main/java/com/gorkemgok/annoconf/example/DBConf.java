package com.gorkemgok.annoconf.example;

import com.gorkemgok.annoconf.ConfParam;
import com.gorkemgok.annoconf.Configuration;

/**
 * Created by gorkem on 31.03.2017.
 */
@Configuration
public class DBConf {

    public final String host;

    public final int port;

    public final String schema;

    public final String userName;

    public final String passsword;

    public DBConf(@ConfParam(key="test.conf.host", defaultValue = "localhost") String host,
                  @ConfParam(key="test.conf.port", defaultValue = "8080") int port,
                  @ConfParam(key="test.conf.schema") String schema,
                  @ConfParam(key="test.conf.username") String userName,
                  @ConfParam(key="test.conf.password") String password) {

        this.host = host;
        this.port = port;
        this.schema = schema;
        this.userName = userName;
        this.passsword = password;
    }

    @Override
    public String toString() {
        return "DBConf{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", schema='" + schema + '\'' +
                ", userName='" + userName + '\'' +
                ", passsword='" + passsword + '\'' +
                '}';
    }
}
