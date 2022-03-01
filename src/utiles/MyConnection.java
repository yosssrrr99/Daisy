/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21653
 */
public class MyConnection {
    public String url ="jdbc:mysql://localhost:3307/cinepro";
    public String login ="root";
    public String pwd ="";
    public Connection cnx2;
    public static MyConnection instance;
    
    public MyConnection(){
        try {
           cnx2 = DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
        
            System.out.println(ex.getMessage());
        }
        

}
    public Connection getCnx(){
    return cnx2;
}

    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }

    public PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}
