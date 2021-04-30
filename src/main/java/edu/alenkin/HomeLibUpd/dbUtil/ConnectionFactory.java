package edu.alenkin.HomeLibUpd.dbUtil;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
    Connection getConnection() throws SQLException, NamingException;
}
