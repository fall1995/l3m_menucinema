package l3m;

import database.GestionnaireClient;
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
 * @author Groupe6 Cette classe permet d'enregistre un client dans la base de
 * donnee oracle
 */
public class ClientEnregistreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Methode qui permet enregistre un nouveau client en prenant en parametre
     * la requete envoyee par le client et la reponse de retour de la part du
     * server
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/updateClient":
                    updateClient(request, response);
                    break;
                case "/listeCommandeClient":
                    listeCommandePourClient(request, response);
                    break;
                case "/deleteClient":
                    deleteClient(request, response);
                    break;
                default:
                    listeCommandePourClient(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }
    /**
     * Methode qui permet enregistrer un nouveau client en prenant en parametre
     * la requete envoyee par le client et la reponse de retour de la part du
     * server
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idClient = request.getParameter("idClient");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        System.out.print(idClient);
        try {
            GestionnaireClient gestionClient = new GestionnaireClient(idClient, nom, prenom);
            System.out.print(gestionClient.getNom());
            gestionClient.enregistreClientDB();

        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Methode qui permet supprimer client en prenant en parametre la requete
     * envoyee par le client et la reponse de retour de la part du server
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String idClient = request.getParameter("idClient");

        GestionnaireClient gestionclientsup = new GestionnaireClient(idClient);
        gestionclientsup.deleteClientId(idClient);
        //response.sendRedirect("list");
    }

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
    private void listeCommandePourClient(HttpServletRequest request,
            HttpServletResponse response) throws SQLException, IOException,
            ServletException {
        String idClient = request.getParameter("idClient");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        try {
            GestionnaireClient gestionClient = new GestionnaireClient(idClient, nom, prenom);
            List<String> listeCommandes = gestionClient.getListeCommandes();
        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
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
            gestionClient.editClientDB();
        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}