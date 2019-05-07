package l3m;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facture.GestionnaireFactures;

/**
 * @author Groupe6 SuggestionFilmsServlet est une classe qui 
 * permet d'envoyer un liste de couples (film, nombre d'occurance) dans 
 * format json pour une plat quelconque
 */
public class SuggestionFilmsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String idPlat = "idPlat";

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
        idPlat = request.getParameter("idPlat");
        GestionnaireFactures gf;
        gf = new GestionnaireFactures();
        
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(
        gf.suggestionFilmToJson(idPlat));
    }
}
