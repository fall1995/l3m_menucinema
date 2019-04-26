package l3m;

import database.GestionnaireClient;
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
 * ClientUpdateServelet permet de mettre a jour les informations consernant un 
 * client
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
        try {
            GestionnaireClient gestionClient = new GestionnaireClient(idClient, nom, prenom);
            gestionClient.editAdresse(adresse);
        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
     

    }

}
