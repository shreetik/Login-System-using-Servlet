
package com.login.helper;
import java.sql.*;

public class ConnectionProvider {
    public static Connection conn;
    public static Connection getConnection(){
       try{
           if(conn == null){
               Class.forName("com.mysql.jdbc.Driver");
               conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb","root","root");
           }
       } catch(Exception e){
           e.printStackTrace();
       }
        return conn;
    }
}
