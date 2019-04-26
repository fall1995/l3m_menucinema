/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l3m;

import database.SQLAble;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Groupe6
 * BD_connection la classe pour l'etablir la connection avec la base de donnée
 */
public class BD_Connection extends SQLAble {

    protected static Connection conn;
    private static String USER = "coulibka";
    private static String PASSWD = "Yah123456";
    /**
     *Constructreur par defaut de la classe connection
     * @throws SQLException
     */
    public BD_Connection() throws SQLException {
    }

    /**
     * Methode permert de se connecter la base de donnee oracle
     */
    public  void connectToDataBase() {

        try {
            if (conn == null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag", "coulibka", "Yah123456");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Methode permet de se deconnecter a la base de donner oracle
     */
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ignore) {
        }

    }
    /**
     * Methode qui permet d'execute une requete passer en parametre
     *
     * @param requete
     * @return res qui contient le resultat du requete excecute
     */
    public ResultSet request(String requete) {
        ResultSet res = null;
        return res;
    }

}
