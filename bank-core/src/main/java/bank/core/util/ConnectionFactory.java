/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.util;

import bank.core.exception.DataAccessException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDataSource;

/**
 *
 * @author Przemek DELL
 */
public class ConnectionFactory {
    private final ClientDataSource ds;

    private ConnectionFactory() {
        ds = new ClientDataSource();
        ds.setDatabaseName("bankDB");
        ds.setUser("Zawadzki");
        ds.setPassword("password");
        ds.setPortNumber(1527);
        ds.setServerName("localhost");
    }
    
    private static class SingletonHolder {
        private static final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    public static ConnectionFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static Connection getConnection() {
        try {
            return getInstance().ds.getConnection();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }
}
