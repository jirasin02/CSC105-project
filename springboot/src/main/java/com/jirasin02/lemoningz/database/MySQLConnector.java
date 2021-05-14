package com.jirasin02.lemoningz.database;

import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MySQLConnector {
    public static final String URL = "jdbc:mysql://csproject.sit.kmutt.ac.th:3306/db63130500202";
    public static final String USERNAME = "63130500202";
    public static final String PASSWORD = "abcd1234";

//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//    }
    private static Connection connection;

    public MySQLConnector() throws SQLException {
        reconnect();
    }

    public static Connection getConnection() throws SQLException {
        if (connection.isClosed())
            reconnect();
        return connection;
    }

    private static void reconnect() throws SQLException {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

}
