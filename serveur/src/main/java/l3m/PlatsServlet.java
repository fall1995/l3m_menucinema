package l3m;

import database.GestionnaireMenu;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**

/**
 * @author Groupe6
 * PlatsServlet est une classe qui permet d'afficher les plats stocker dans 
 * la base donnee Xml
 * 
 */
public class PlatsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String json = "json";

    /**
     *Methode qui permet lire plats dans la base Xml 
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
