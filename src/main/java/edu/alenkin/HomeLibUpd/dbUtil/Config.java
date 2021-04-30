package edu.alenkin.HomeLibUpd.dbUtil;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public final class Config {
    private final static Config INSTANCE = new Config();
    private final ConnectionFactory connectionFactory;

    private Config() {
        connectionFactory = (() ->
                ((DataSource) new InitialContext().lookup("jdbc/Library")).getConnection());
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public static Config get(){
        return INSTANCE;
    }

}
