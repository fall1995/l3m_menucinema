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
    public void connectToDatabase() throws SQLException {
        if (conn == null || conn != null && conn.isClosed()) {
            String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
            String USER = "hasdit";
            String PASSWD = "az1ER2t3";
            System.out.print("Loading Oracle driver... ");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");

            // Etablissement de la connection
            System.out.print("Connecting to the database... ");
            conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
            conn.setAutoCommit(false);
            System.out.println("connected");
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
