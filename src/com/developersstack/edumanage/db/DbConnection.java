package com.developersstack.edumanage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection
{
    // 1 private static reference var
    private static DbConnection dbConnection;
    private Connection connection;
    // constructor private
    private DbConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/edu_manage?user=root&password=" );
    }

    public static DbConnection getInstance() throws SQLException, ClassNotFoundException
    {
        return dbConnection == null ? (dbConnection= new DbConnection()) : dbConnection;
    }

    public Connection getConnection()
        {
        return connection;
    }

}