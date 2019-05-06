package l3m;

import classesgen.plats.Plats;
import com.google.gson.Gson;
import database.GestionnaireMenu;
import static database.GestionnaireMenu.getCarteDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**

/**
 * @author Groupe6
 LaCarteServlet est une classe qui permet d'afficher les plats stocker dans 
 la base donnee Xml
 * 
 */
public class LaCarteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String json = "json";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        json = request.getParameter("json");
        
        Plats laCarte = GestionnaireMenu.getCarteDB();
        
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( new Gson().toJson(laCarte) );

    }
}
