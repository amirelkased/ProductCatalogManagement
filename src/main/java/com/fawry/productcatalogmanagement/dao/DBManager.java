package com.fawry.productcatalogmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/product_manager";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBManager() {
    }

    public static DBManager getInstance() {
        return SingletonHelper.DB_MANAGER;
    }

    public Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return connection;
    }

    private static class SingletonHelper {
        private static final DBManager DB_MANAGER = new DBManager();
    }
}
