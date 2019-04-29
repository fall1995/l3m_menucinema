package l3m;

import classesgen.commande.Commande;
import classesgen.plat.Plat;
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
import classesgen.plats.Plats;

/**
 * @author Groupe6
 * CommandesServlet
 */
public class CommandesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String id = "id";
    private String idClient = "idClient";
    private String idPlats = "idPlat";
    private String idFilms = "idFilms";
    private String prix = "prix";
    private String adresseLivraison = "adresseLivraison";
    
    private String idPlat = "idPlat";
    private String idCommande = "idCommande";

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        id = request.getParameter("id");
        GestionnaireCommande gestonCommande;
        gestonCommande = new GestionnaireCommande(id);
        try {
            gestonCommande.getCommande(id);
        } catch (SQLException ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        idClient = request.getParameter("idClient");
        id = request.getParameter("id");
        idPlats = request.getParameter("idPlat");
        idFilms = request.getParameter("idFilms");
        prix = request.getParameter("prix");
        adresseLivraison = request.getParameter("adresseLivraison");
        
        GestionnaireCommande addCommande= new GestionnaireCommande (id);
        try {
            addCommande.enregistreCommanderDB();
        } catch (SQLException ex) {
            Logger.getLogger(CommandesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    // AJOUTER PLAT A UNE COMMANDE EXISTANTE
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           idPlat = request.getParameter("idPlat");
           idCommande = request.getParameter("idCommande");
           
           GestionnaireCommande gComm = new GestionnaireCommande(this.idCommande);
           Commande comm = gComm.getCommande(this.idCommande);
           comm.getPlat().add(###FIND PLAT FROM idPlat);
           
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           id = request.getParameter("id");

        GestionnaireCommande gestioncommandesup;
        gestioncommandesup = new GestionnaireCommande(id);
        //gestioncommandesup.deleteCommandeId(id);

    }

}
