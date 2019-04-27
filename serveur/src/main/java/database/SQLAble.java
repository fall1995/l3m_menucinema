package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Groupe6 la classe SQLAble permet gerer la connection a la base oracle
 * et l'excecution des requete
 */
public abstract class SQLAble implements DataBaseAble {

    Connection conn;

    /**
     * Methode permert de se connecter la base de donnee oracle
     */
    @Override
    public void connectToDatabase() throws SQLException {
        String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
        String USER = "coulibka";
        String PASSWD = "Yah123456";
        System.out.print("Loading Oracle driver... ");
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        System.out.println("loaded");

        // Etablissement de la connection
        System.out.print("Connecting to the database... ");
        conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
        System.out.println("connected");
        /* 
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            conn = DriverManager.getConnection(
                    CONN_URL, USER, PASSWD);
        }
        **/
    }

    /**
     * Methode permet de se deconnecter a la base de donner oracle
     */
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
     */
    public ResultSet request(String requete) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery(requete);
        return rset;
    }

}
