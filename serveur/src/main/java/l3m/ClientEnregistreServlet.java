package l3m;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6 
 * Cette classe permet d'enregistre un client
 */
public class ClientEnregistreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean processQueryTest(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idClient = request.getParameter("idClient");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String photo = request.getParameter("photo");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String adresse = request.getParameter("adresse");
        
        Connection conn = BD_Connection.getConnection();
       //String sql = "{ = call enregistrerClient(?,?,?,?,?,?,?) }";
       String sql =("insert into Client (idClient ,nom ,prenom ,photo ,email ,tel,adresse)"
               + "values (?,?,?,?,?,?,?)");
         System.out.println(conn);
        CallableStatement cstmt;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, idClient);
            cstmt.setString(2, nom);
            cstmt.setString(3, prenom);
            cstmt.setString(4, photo);
            cstmt.setString(5, email);
            cstmt.setString(6, tel);
            cstmt.setString(7, adresse);
            cstmt.execute();
             System.out.println("cest ok");
            cstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
