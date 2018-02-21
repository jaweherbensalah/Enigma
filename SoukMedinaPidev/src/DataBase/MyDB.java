/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wieme
 */
public class MyDB {
    
    
    String url = "jdbc:mysql://localhost:3306/pidev2";
    String username = "root";
    String password  = "";

    private Connection connection;
    private static MyDB instance;

    private MyDB() {
        try {
            connection = DriverManager
                    .getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MyDB getInstance(){
        if(instance == null){
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    

}

    
