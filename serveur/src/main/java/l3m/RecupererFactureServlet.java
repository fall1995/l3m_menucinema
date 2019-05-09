/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l3m;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import facture.GestionnaireFactures;/**
 *
 * @author firsovol
 */
public class RecupererFactureServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
    private String idClient = "idClient";
    private String id = "id";
    private String date = "date";

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
        idClient = request.getParameter("idClient");
        id = request.getParameter("id");
        GestionnaireFactures gf;
        gf = new GestionnaireFactures();
        
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(
        gf.recupererFacture(idClient, id));
    }
}
