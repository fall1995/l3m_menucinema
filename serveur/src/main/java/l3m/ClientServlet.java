package l3m;

import classesgen.commande.Commande;
import com.google.gson.Gson;
import database.GestionnaireClient;
import database.GestionnaireCommande;
import java.io.IOException;
import java.sql.SQLException;
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
        

        if (request.getServletPath().equals("/api/client")) {
            
            String idClient = request.getParameter("userId");
            System.out.println(idClient);
            try {
                GestionnaireClient gc = new GestionnaireClient(idClient, "", "");
                List<String> listeCommandes = gc.getListeCommandes();
                Commande[] commandes = new Commande[listeCommandes.size()];

                for (int i = 0; i < listeCommandes.size(); i++) {
                    commandes[i] = GestionnaireCommande.getCommande(listeCommandes.get(i));
                }

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(new Gson().toJson(commandes));

            } catch (SQLException ex) {
                Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  
        else if (request.getServletPath().equals("/api/derniercommande")) {
            
            String idClient = request.getParameter("userId");
            System.out.println(idClient);
            try {

                Commande commande = GestionnaireCommande.getLastCommande(idClient);

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(new Gson().toJson(commande));

            } catch (SQLException ex) {
                Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        
        String idClient = request.getParameter("idClient");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        
        try {
            GestionnaireClient gc = new GestionnaireClient(idClient, nom, prenom);
            gc.enregistreClientDB();
            response.getWriter().println( gc.ClientToJson() );
        } catch (SQLException ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 
    @Override
     protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException {
        
        String idClient = request.getParameter("idClient");
        boolean supprime = false;
        
        try {
            supprime = GestionnaireClient.deleteClientId(idClient);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println( supprime );
        } catch (Exception ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Methode qui permet de mettre a les info d'un client en prenant en
     * parametre la requete envoyee par le client et la reponse de retour de la
     * part du server
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idClient = request.getParameter("idClient");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String photo = request.getParameter("photo");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String adresse = request.getParameter("adresse");
        try {
            GestionnaireClient gestionClient = new GestionnaireClient(idClient,
                    nom, prenom);
            gestionClient.editPhoto(photo);
            gestionClient.editEmail(email);
            gestionClient.enregistreClientDB();
            gestionClient.editAdresse(adresse);

            gestionClient.editClientDB();
        } catch (SQLException ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
