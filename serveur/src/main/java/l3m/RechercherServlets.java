/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l3m;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Groupe6 RechercherServlets est une classe qui 
 * permet de rechercher un ingredient
 */
public class RechercherServlets extends HttpServlet {

    private String json = "json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();

        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter((String) p));
        }

        response.setContentType("application/json");
        json = request.getParameter("json");

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

    }

}
