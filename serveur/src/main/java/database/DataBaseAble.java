package database;

import java.sql.SQLException;

/**
 * @author Groupe6 
 * DataBaseAble Intteface  permet la gestion de la connection
 */
interface DataBaseAble {
    public void connectToDatabase() throws SQLException;
    public void disconnect() throws SQLException;
    //public ResultFormat request(String request) throws SQLException, Exception;
    
}
