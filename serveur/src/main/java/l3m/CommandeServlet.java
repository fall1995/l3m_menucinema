package l3m;

import classesgen.commande.Commande;
import com.google.gson.Gson;
import database.GestionnaireCommande;
import facture.GestionnaireFactures;
import facture.SauvegarderFacture;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6 CommandeServlet est une classe qui permet de modeliser les
 * commandes d'un client
 */
public class CommandeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println( "========================================================== CommandeServlet [doGet] ==========================================================" );
        System.out.print("Récupération de la commande ...");
        response.setContentType("application/json");
        String idCommande = request.getParameter("idCommande");
        Commande commande;
        try {
            commande = GestionnaireCommande.getCommande(idCommande);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(new Gson().toJson(commande));
            System.out.println("la récupération est effectuée avec succès !");
        
        } catch (Exception ex) {
            Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println( "========================================================= CommandeServlet [doPost] =========================================================" );
        System.out.print("Enregistrement de la commande ...");
        Commande commande = new Commande();
        commande.setIdClient(request.getParameter("idClient"));
        commande.setAdresseLivraison(request.getParameter("adresseLivraison"));
        String buffer = "";
        buffer = request.getParameter("idPlats");
        buffer = buffer.replaceAll("[\\[\\]\"]", "");
        String[] plats = buffer.split(",");

        for (int i = 0; i < plats.length; i++) {
            commande.getIdPlats().add(plats[i]);
        }
        buffer = request.getParameter("idFilms");
        buffer = buffer.replaceAll("[\\[\\]\"]", "");
        String[] films = buffer.split(",");
        for (int i = 0; i < films.length; i++) {
            commande.getIdFilms().add(films[i]);
        }

        GestionnaireCommande gc;

        try {
            gc = new GestionnaireCommande(commande.getIdClient(),
                    commande.getIdPlats(),
                    commande.getIdFilms(),
                    commande.getAdresseLivraison()
            );

            gc.enregistrerCommandeDB();
            
            SauvegarderFacture.saveFacture( 
                    commande.getAdresseLivraison(), commande.getDate(), commande.getId(),commande.getPrix(), 
                    commande.getIdClient(), films, plats);
            GestionnaireFactures gf=new GestionnaireFactures();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println( gf.recupererFacture(commande.getIdClient(),  commande.getId()) );
            System.out.println("l'enregistrement est éffectué avec succès !");
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { }

}
