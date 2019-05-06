package l3m;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facture.GestionnaireFactures;
/**

/**
 *
 * @author firsovol
 */
public class SuggestionPlatsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String idFilm = "idFilm";

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
        idFilm = request.getParameter("idFilm");
        GestionnaireFactures gf;
        gf = new GestionnaireFactures();
        
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(
        gf.suggestionPlatToJson(idFilm));
    }
}
