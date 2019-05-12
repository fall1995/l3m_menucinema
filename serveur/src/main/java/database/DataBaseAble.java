package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Groupe6 
 * DataBaseAble Intteface  permet la gestion de la connection
 */
interface DataBaseAble {
    public void connectToDatabase() throws SQLException, ClassNotFoundException;
    public void disconnect() throws SQLException;
    public ResultSet request(String request) throws SQLException, Exception;
    
}
