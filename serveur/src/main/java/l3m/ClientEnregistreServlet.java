package l3m;

import PoubelleTmp.BD_Connection;
import database.GestionnaireClient;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Groupe6 
 * Cette classe permet d'enregistre un client dans la base de donnee oracle
 */
public class ClientEnregistreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean processQueryTest(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idClient = request.getParameter("idClient");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        try {
            GestionnaireClient gestionClient = new GestionnaireClient(idClient, nom, prenom);
            gestionClient.enregistreClientDB();
        } catch (SQLException ex) {
            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
