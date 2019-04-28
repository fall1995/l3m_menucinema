package l3m;

import database.GestionnaireClient;
import database.GestionnaireCommande;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6 
 * CommandesServlet
 */
public class CommandesServlet extends HttpServlet {
    
    
     @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //id commande auto incrementer a faire
        
        String idClient = request.getParameter("idClient");
        String idPlat= request.getParameter("idPlat");
        String prenom = request.getParameter("prenom");
        String photo = request.getParameter("photo");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String adresse = request.getParameter("adresse");
         // GestionnaireCommande gestionCommande = new GestionnaireCommande();
         //gestionCommande.enregistreCommanderDB();
    }
    
}
