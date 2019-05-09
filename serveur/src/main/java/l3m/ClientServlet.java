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
        

        if (request.getServletPath().equals("/api/client")) {
            String idClient = request.getParameter("idClient");
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
        
  
        else if (request.getServletPath().equals("/api/client/dernierecommande")) {
            String idClient = request.getParameter("idClient");
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

        
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
        
        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter( p ));
        }
        
        try {
            //mise à jour
            GestionnaireClient gc = new GestionnaireClient( parametres.get("id"),
                                                            parametres.get("nom"),
                                                            parametres.get("prenom")
                                        );

            gc.editEmail(parametres.get("email"));
            gc.editAdresse(parametres.get("adresse"));
            gc.editPhoto(parametres.get("photo"));
            gc.editTel(parametres.get("tel"));
            gc.editClientDB();

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(gc.ClientToJson());

        } catch (SQLException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(UpdateClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
