package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Groupe6 
 * la classe SQLAble permet gerer la connection a la base oracle
 * et l'excecution des requete
 */
public abstract class SQLAble implements DataBaseAble {

    static Connection conn;

    @Override
    public void connectToDatabase() throws SQLException, ClassNotFoundException {
        if (conn == null || conn != null && conn.isClosed()) {
            DatabaseAccesProperties dap = new DatabaseAccesProperties("DBproperties.txt"); 
            String conn_url = dap.getDbUrl();
            String jdbcDriver = dap.getJdbcDriver();
            String user = dap.getUsername();
            String password = dap.getPassword();
            System.out.print("\nLoading jdbc driver... ");
            Class.forName(jdbcDriver);
            System.out.println("loaded");
            System.out.print("Connecting to the database... ");
            conn = DriverManager.getConnection( conn_url , user , password );
            conn.setAutoCommit(false);
            System.out.println("connected \"" + user + "\"" );
        }
    }
    
    
    @Override
    public void disconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    /**
     * Methode qui permet d'execute une requete passer en parametre
     *
     * @param requete
     * @return res qui contient le resultat du requete excecute
     * @throws java.sql.SQLException
     */
    public ResultSet request(String requete) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery(requete);
        return rset;
    }

}
