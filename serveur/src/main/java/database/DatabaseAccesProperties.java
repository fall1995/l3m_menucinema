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
 * @author Tariq
 */
public class DatabaseAccesProperties {
    
    private Properties prop;
    private String jdbcDriver;
    private String dbUrl;
    private String username, password;
    
    public DatabaseAccesProperties ( String bdProperties ){
        try{
            prop = new Properties();
            prop.load( new FileInputStream(bdProperties) );
            jdbcDriver = prop.getProperty("jdbcDriver");
            dbUrl = prop.getProperty("dbUrl");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        }catch( FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
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
