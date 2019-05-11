/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Groupe 6
 */

public class DatabaseAccesProperties {
    
    private Properties prop = new Properties();
    private String jdbcDriver;
    private String dbUrl;
    private String username, password;
    
    public DatabaseAccesProperties ( String bdProprietes){
        
        try {
            
            prop = new Properties();
            prop.load(new FileInputStream(bdProprietes));
            jdbcDriver = prop.getProperty("jdbcDriver");
            dbUrl = prop.getProperty("dbUrl");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            
        }catch( FileNotFoundException e){
            System.err.println( "FileNotFoundException: " + e.getMessage()) ;
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println( "IOException: " + e.getMessage()) ;
            e.printStackTrace();
        }
       
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
