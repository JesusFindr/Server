package org.academiadecodigo.hackathon.jesusfindrserver.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class DatabaseConnector {

    private static final String dbUrl = "jdbc:mysql:///cadetlogin?useSSL=false";
    private static final String user = "root";
    private static final String pass = "";

    private static DatabaseConnector databaseConnector;
    private Connection connection = null;

    public static DatabaseConnector getInstance() {

        if (databaseConnector == null) {
            synchronized (DatabaseConnector.class) {
                if (databaseConnector == null) {
                    databaseConnector = new DatabaseConnector();
                }
            }
        }

        return databaseConnector;

    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;

    }

    public void close() {
        System.out.println("closing connection to db");
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }

}
