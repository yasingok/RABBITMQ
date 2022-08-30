package com.example.rabbit.common;

import com.rabbitmq.client.impl.Environment;

public class EnvConstant {
    ///Exchange Declaration
    private static final String DIRECT_EXCHANGE_NAME = System.getenv("DIRECT_EXCHANGE_NAME");
    private static final String DIRECT_ROUTING = System.getenv("DIRECT_ROUTING");

    private static final String FANOUT_EXCHANGE_NAME = System.getenv("FANOUT_EXCHANGE_NAME");
    private static final String FANOUT_ROUTING = System.getenv("FANOUT_ROUTING");

    private static final String FANOUT_TYPE = "fanout";
    private static final String DIRECT_TYPE = "direct";


    public static String getFanoutExchangeName() {
        return FANOUT_EXCHANGE_NAME;
    }

    public static String getFanoutRouting() {
        return FANOUT_ROUTING;
    }

    public static String getDirectExchangeName() {
        return DIRECT_EXCHANGE_NAME;
    }

    public static String getDirectRouting() {
        return DIRECT_ROUTING;
    }

    public static String getFanoutType() {
        return FANOUT_TYPE;
    }
    public static String getDirectType() {
        return DIRECT_TYPE;
    }

    ///Credentials
    private static final String USER_NAME = System.getenv("USER_NAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static final String HOST = System.getenv("HOST");
    private static final int PORT = Integer.parseInt(System.getenv("PORT"));

    public static String getUserName() {
        return USER_NAME;
    }
    public static String getPassword() {
        return PASSWORD;
    }
    public static String getHost() {
        return HOST;
    }
    public static Integer getPort() {
        return PORT;
    }


}
