package l3m;

import classesgen.commande.Commande;
import classesgen.plat.Plat;
import database.GestionnaireCommande;
import database.GestionnaireMenu;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**

/**
 *
 * @author firsovol
 */
public class PlatsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String json = "json";

    /**
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
        json = request.getParameter("json");
        GestionnaireMenu gm;
        gm = new GestionnaireMenu();
        
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(
        gm.platsToJson());

    }
}
