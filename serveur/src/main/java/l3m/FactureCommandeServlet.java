/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l3m;

import classesgen.commande.Commande;
import database.GestionnaireCommande;
import facture.GestionnaireFactures;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kadidiatou
 */
public class FactureCommandeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Commande commande = new Commande();
        
        String idClient = request.getParameter("idClient");
        String id = request.getParameter("id");
        
        GestionnaireCommande gc ;
        try {
            commande = GestionnaireCommande.getCommande(id);
            
            GestionnaireFactures gf = new GestionnaireFactures();
            String lg = gf.recupererFacture(idClient, id);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(commande.toString());

        } catch (SQLException ex) {
            Logger.getLogger(FactureCommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FactureCommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
