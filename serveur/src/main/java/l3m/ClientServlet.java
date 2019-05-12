package l3m;

import classesgen.commande.Commande;
import com.google.gson.Gson;
import database.GestionnaireClient;
import database.GestionnaireCommande;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6 ClientServlet est une classe qui permet
 d'enregistre un client dans la base de donnee oracle
 */
public class ClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println( "=========================================================== ClientServlet [doGet] ===========================================================" );
        try {
            
            if (request.getServletPath().equals("/api/client")) {
                
                System.out.print("Recupération de toutes les commandes du client ...");
                String idClient = request.getParameter("idClient");
                GestionnaireClient gc = new GestionnaireClient(idClient, "", "");
                List<String> listeCommandes;
                listeCommandes = gc.getListeCommandes();
                Commande[] commandes = new Commande[listeCommandes.size()];
                for (int i = 0; i < listeCommandes.size(); i++) {
                    commandes[i] = GestionnaireCommande.getCommande(listeCommandes.get(i));
                }

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(new Gson().toJson(commandes));
                System.out.println("la récupératation est effectuée avec succès !");
                
            } else if (request.getServletPath().equals("/api/client/dernierecommande")) {
                
                System.out.print("Récupération de la dernière commande du Client ...");
                String idClient = request.getParameter("idClient");
                Commande commande = GestionnaireCommande.getLastCommande(idClient);
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(new Gson().toJson(commande));
                System.out.println("la récupératation est effectuée avec succès!");
            }
        
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

 
    @Override
     protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException {}
   

}
