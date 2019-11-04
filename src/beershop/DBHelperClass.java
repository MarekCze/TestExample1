/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beershop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @name Owner
 */
public class DBHelperClass {
        public static Connection getConnection() throws SQLException, ClassNotFoundException{
        String host = "localhost";
        String dbName = "products";
        int port = 3306 ;
        String mySqlUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
//        String embeddedMySqlUrl = "jdbc:mysql" + dbName;
        
        Properties userInfo = new Properties();
        userInfo.put("user", "root");
        userInfo.put("password", "");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
            return connection;
        } catch (Exception ex) {
            System.out.println("error on load of driver" + ex);
        }
        return null;
    }
}
