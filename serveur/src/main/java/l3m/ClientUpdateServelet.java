package l3m;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6
 */
public class ClientUpdateServelet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idClient = request.getParameter("idClient");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String photo = request.getParameter("photo");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String adresse = request.getParameter("adresse");

        request.setAttribute("idClient", idClient);
        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);
        request.setAttribute("photo", photo);
        request.setAttribute("tel", tel);
        request.setAttribute("adresse", adresse);
        //Connection conn = BD_Connection.getConnection();
        String sql = "{editClient(?,?,?,?,?,?,?)}";
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
            cstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
