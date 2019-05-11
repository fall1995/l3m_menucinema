package l3m;

import classesgen.plat.Plat;
import classesgen.plats.Plats;
import com.google.gson.Gson;
import database.GestionnaireMenu;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**

/**
 * @author Groupe6
 LaCarteServlet est une classe qui permet d'afficher les plats stocker dans 
 la base donnee xml dans une forme json
 * 
 */
public class LaCarteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        System.out.println( "========================================================== LaCarteServlet [doGet] =========================================================" );
        System.out.print("Récupération de la carte des plats ...");
        
        List<Plat> laCarte;

        laCarte = GestionnaireMenu.getCartesDB();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(new Gson().toJson(laCarte));
        System.out.println("la carte est récupérée !");

    }
}
