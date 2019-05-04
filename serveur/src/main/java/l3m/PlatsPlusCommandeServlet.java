package l3m;

import classesgen.commande.Commande;
import database.GestionnaireCommande;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6 PlatsPlusCommandeServlet est la classe qui permet d'afficher
 * les plats les plus commandés
 */
public class PlatsPlusCommandeServlet extends HttpServlet {

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
        String id = "";

        GestionnaireCommande gestionClient = new GestionnaireCommande(id);
        List<Commande> listeCommandes = null;
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(listeCommandes.toString());
    }

}
