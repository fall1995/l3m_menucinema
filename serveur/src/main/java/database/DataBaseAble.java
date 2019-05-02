package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Groupe6 
 * DataBaseAble Intteface  permet la gestion de la connection
 */
interface DataBaseAble {
    public void connectToDatabase() throws SQLException;
    public void disconnect() throws SQLException;
    public ResultSet request(String request) throws SQLException, Exception;
    
}
