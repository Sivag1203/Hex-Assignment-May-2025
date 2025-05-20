package com.ecom.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtility {
    private String url = "jdbc:mysql://localhost:3306/ecom_assignment";
    private String DbUser = "root";
    private String DbPass = "SivaG@1203";
    private String driver = "com.mysql.cj.jdbc.Driver";

    //single-ton inmplementation is below
    private static DbUtility db = new DbUtility();
    public static DbUtility getInstance(){
        return db;
    }
    private DbUtility(){};


    private Connection con;

    public  Connection connect() {
        //step 1: load the driver
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //step 2: establish the connection
        try {
            con =  DriverManager.getConnection(url,DbUser,DbPass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return con;
    }

    public void close() {
        try {
            if(!con.isClosed())
                con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
