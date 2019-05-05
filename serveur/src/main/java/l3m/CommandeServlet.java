package l3m;

import classesgen.commande.Commande;
import com.google.gson.Gson;
import database.GestionnaireCommande;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6 CommandeServlet est une classe qui permet de modeliser les
 commandes d'un client
 */
public class CommandeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String idCommande = request.getParameter("idCommande");
        Commande commande;
        try {
            commande = GestionnaireCommande.getCommande(idCommande);
            
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println( new Gson().toJson(commande) );
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Commande commande = new Commande();
        commande.setIdClient( request.getParameter("idClient") );
        commande.setAdresseLivraison(request.getParameter("adresseLivraison") );
        List<String> idPlats = new ArrayList<String>();
        List<String> idFilms = new ArrayList<String>();
        
        String[] tabPlats = (String[] )request.getParameterValues("idPlats");

        for ( int i = 0 ; i < tabPlats.length ; i ++ ){
            idPlats.add(tabPlats[i]);
        }
        
        String[] tabFilms = request.getParameterValues("idFilms");
        for ( int i = 0 ; i < tabFilms.length ; i ++ ){
            idFilms.add(tabFilms[i]);
        }
        
        commande.setIdPlat(idPlats);
        commande.setIdFilm(idFilms);

        GestionnaireCommande gc;
        try {
            gc = new GestionnaireCommande(  commande.getIdClient(),
                                            commande.getIdPlat(),
                                            commande.getFilm(),
                                            commande.getAdresseLivraison()
                                         );
            gc.enregistrerCommandeDB();
            
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println( gc.CommandeToJson() );
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
        /*Client client = new Client();

            try {
                //mise à jour
                GestionnaireClient gc = new GestionnaireClient(client.getId(), client.getNom(), client.getPrenom());
                gestionClient.editClientDB();
                
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(client.toString());
            } catch (SQLException ex) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println(ex.getMessage());
            }
            */

    }
      

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idClient = request.getParameter("idCommande");
        
    }

    /**
     * Methode qui permet de convertir un tableau de string en liste
     * @param Tableau
     */
    static <T> List<T> toArrayList(T[] Tableau) {
        List<T> al = new ArrayList<>();
        for (T obj : Tableau) {
            al.add(obj);
        }
        return al;
    }
}
