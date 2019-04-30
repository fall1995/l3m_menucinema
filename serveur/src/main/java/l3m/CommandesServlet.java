package l3m;

import database.GestionnaireCommande;
import database.GestionnaireMenu;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6 
 * CommandesServlet
 */
public class CommandesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String id = "id";
    private String idClient = "idClient";
    private double prix;
    private String adresseLivraison = "adresseLivraison";
    String[] idplats;
    String[] idFilms;

    /**
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
        try {
            gestonCommande = new GestionnaireCommande(id);
            GestionnaireCommande.getCommande(id);
        } catch (SQLException ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap<String, String> idplat_quantite = new HashMap<>();
        // recuperation des donnes
        id = request.getParameter("id");
        idClient = request.getParameter("idClient");
        idplats = request.getParameterValues("idPlat");
        idFilms = request.getParameterValues("idFilms");
        adresseLivraison = request.getParameter("adresseLivraison");

        // calculer du prix de la commande
        double prixFilms = sommeFilm(idFilms);
        double prixPlats = sommePlat(idplats);
        prix = prixFilms + prixPlats;

        //insertion dans la base de donnes
        GestionnaireCommande addCommande;
        try {
            addCommande = new GestionnaireCommande(id);
            addCommande.enregistrerCommandeDB();
        } catch (SQLException ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
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

        GestionnaireCommande gestioncommandesup;
        try {
            gestioncommandesup = new GestionnaireCommande(id);
            //gestioncommandesup.deleteCommandeId(id);
        } catch (SQLException ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Methode qui permet de calcule le prix total des films choisis par un
     * client client et qui prend en parametre la liste des films
     * @param idFilms
     * @return res
     */
    public double sommeFilm(String[] idFilms) {
        double res;
        int nbreFilm = idFilms.length;
        res = 3.79 * nbreFilm;
        return res;
    }

    /**
     * Methode qui permet de calcule le prix total des plats choisis par un
     * client client et qui prend en parametre la liste des des plats
     * @param idplats
     * @return res
     */
    public double sommePlat(String[] idplats) {
        double res;
        res = 0.0;
        double prixplat = 0.0;
        for (int i = 0; i < idplats.length; i++) {
            String idp = idplats[i];
            GestionnaireMenu gestionmenu= new GestionnaireMenu();
             prixplat=gestionmenu.getPrixPlat(idp);
            res+=prixplat;
        }
        return res;
    }
}
