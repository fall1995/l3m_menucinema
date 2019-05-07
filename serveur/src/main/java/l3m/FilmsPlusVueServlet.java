/******A ENLEVER ******/

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
 * @author Groupe6
 * FilmsPlusVueServlet est la classe qui permet d'affiche les films plus vue 
 * 
 */
public class FilmsPlusVueServlet extends HttpServlet{
    
    /**
     * Methode qui permet d'afficher la liste des films le plus commandes avec
     * les plats ausssi
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
