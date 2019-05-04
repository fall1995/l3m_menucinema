package l3m;

import classesgen.commande.Commande;
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
 * @author Groupe6 ClientEnregistreServlet est une classe qui permet
 * d'enregistre un client dans la base de donnee oracle
 */
public class ClientEnregistreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Methode qui permet d'afficher la liste des commandes concernant un client
     * en prenant en parametre la requete envoyee par le client et la reponse de
     * retour de la part du server
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idClient = request.getParameter("idClient");

        try {
            GestionnaireClient gestionnaireCli = new GestionnaireClient(idClient);
            GestionnaireCommande[] gc;
            Commande commande;
            
            List<String> listeCommandes;
            listeCommandes = gestionnaireCli.getListeCommandes();
            gc = new GestionnaireCommande[listeCommandes.size()];
            String[] dataJson = new String[listeCommandes.size()];
            
            for (int i = 0; i < listeCommandes.size(); i++) {
               commande = GestionnaireCommande.getCommande(listeCommandes.get(i));
               gc[i] = new GestionnaireCommande(commande);
               dataJson[i] = gc[i].CommandeToJson();
            }
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println( dataJson );

        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idClient = request.getParameter("idClient");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        
        System.out.println();
        try {
            GestionnaireClient gc = new GestionnaireClient(idClient, nom, prenom);
            gc.enregistreClientDB();
            response.getWriter().println( gc.ClientToJson() );
        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Methode qui permet supprimer client en prenant en parametre la requete
     * envoyee par le client et la reponse de retour de la part du server
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    /*
    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String idClient = request.getParameter("idClient");

        GestionnaireClient gestionclientsup = new GestionnaireClient(idClient);
        gestionclientsup.deleteClientId(idClient);
        //response.sendRedirect("list");
    }
    */
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
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Methode qui permet de supprimer un client
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idClient = request.getParameter("idClient");

        GestionnaireClient gestionclientsup;
        try {
            GestionnaireClient.deleteClientId(idClient);
        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
