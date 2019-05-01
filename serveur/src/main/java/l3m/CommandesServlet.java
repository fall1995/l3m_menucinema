package l3m;

import classesgen.commande.Commande;
import database.GestionnaireCommande;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private String[] idplats;
    private String[] idFilms;

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
        
        GestionnaireCommande gestonCommande;
        gestonCommande = new GestionnaireCommande(id);
        Commande commande = gestonCommande.getCommande(id);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(commande.toString());

    }

    /**
     * *
     * Methode qui permet d'enregistrer un une commande
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Commande commande = new Commande();
        // recuperation des donnes
        idClient = request.getParameter("idClient");
        idplats = request.getParameterValues("idPlat");
        idFilms = request.getParameterValues("idFilms");
        adresseLivraison = request.getParameter("adresseLivraison");
        
        List<String> listeplat = (ArrayList<String>) toArrayList(idplats);
        List<String> listeFilms = (ArrayList<String>) toArrayList(idFilms);

        commande.setIdClient(idClient);
        commande.setIdPlat(listeplat);
        commande.setIdFilm(listeFilms);
        commande.setAdresseLivraison(adresseLivraison);
        //insertion dans la base de donnes
        GestionnaireCommande addCommande;
        try {
            addCommande = new GestionnaireCommande(idClient, listeplat, listeFilms, adresseLivraison);
            commande.setPrix(prix);
            addCommande.enregistrerCommandeDB();
        } catch (SQLException ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Methode pour modifier une commande 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    // AJOUTER PLAT A UNE COMMANDE EXISTANTE
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
