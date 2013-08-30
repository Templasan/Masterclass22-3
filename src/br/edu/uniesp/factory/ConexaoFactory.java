/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniesp.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ConexaoFactory {
    
    public static Connection con;
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
       
        if ( con == null ) {

            Class.forName("com.mysql.jdbc.Driver");


            String url = "jdbc:mysql://localhost:3306/aplicacao";
            String user = "root";
            String pass = "";

  
            con = DriverManager.getConnection(url, user, pass);

            return con;
            
        } else {
            return con;
        }
    }
}
