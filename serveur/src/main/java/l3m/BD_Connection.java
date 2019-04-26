/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l3m;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kadidiatou
 *
 * BD_connection la classe pour l'etablir la connection avec la base de donnée
 *
 */
public class BD_Connection {

    private static Connection conn;
    private  static String USER="coulibka";
    private static String PASSWD="Yah123456";

   // static final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";

    public BD_Connection() throws SQLException {
    }
    public static Connection getConnection()
	{
		try
		{
			if(conn==null)
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag", "coulibka", "Yah123456");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}

}

