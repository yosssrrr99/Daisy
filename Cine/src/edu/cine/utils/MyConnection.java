/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class MyConnection {
    
    public String url="jdbc:mysql://localhost:3306/cinepro";
    public String login="root";
    public String pwd="";
    public Connection cnx;

    public MyConnection() {
        
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println("lennaa");
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getCnx(){
        return cnx;
     }
   
}
