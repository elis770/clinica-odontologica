package com.dh.dentalClinicMVCE.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {

    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:./dc";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    public static Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTables() {
        Connection connection = null;
        try {
            connection = getConnection();
            // Use RunScript from H2 to execute the SQL file
            org.h2.tools.RunScript.execute(connection, new FileReader("create.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}