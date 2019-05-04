package l3m;

import classesgen.client.Client;
import classesgen.commande.Commande;
import database.GestionnaireClient;
import database.GestionnaireCommande;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Groupe6 CommandesServlet est une classe qui permet de modeliser les
 * commandes d'un client
 */
public class CommandesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String id = "id";
    private String idClient = "idClient";
    private double prix;
    private String adresseLivraison;
    private List<String> idPlats = new ArrayList<String>();
    private List<String> idFilms = new ArrayList<String>();

    /**
     * Methode qui permet d'afficher les commander d'un client donné
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        id = request.getParameter("id");
        
        Commande commande;
        try {
            commande = GestionnaireCommande.getCommande(id);
            GestionnaireCommande gc = new GestionnaireCommande(commande);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(gc.CommandeToJson());
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Commande commande = new Commande();
        // recuperation des donnes
        idClient = request.getParameter("idClient");
        
        String[] tabPlats = request.getParameterValues("idPlat");
        for ( int i = 0 ; i < tabPlats.length ; i ++ ){
            idPlats.add(tabPlats[i]);
        }
        
        String[] tabFilms = request.getParameterValues("idFilms");
        for ( int i = 0 ; i < tabFilms.length ; i ++ ){
            idFilms.add(tabFilms[i]);
        }
        
        adresseLivraison = request.getParameter("adresseLivraison");

        commande.setIdClient(idClient);
        commande.setIdPlat(idPlats);
        commande.setIdFilm(idFilms);
        commande.setAdresseLivraison(adresseLivraison);
        //insertion dans la base de donnes
        GestionnaireCommande gc;
        try {
            gc = new GestionnaireCommande(idClient, idPlats, idFilms, adresseLivraison);
            gc.enregistrerCommandeDB();
            
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println( gc.CommandeToJson() );
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        id = request.getParameter("id");
        
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
