package l3m;

import database.GestionnaireClient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Je suis passé par l'itération 0 du serveur...
public class ClientAuthentificationServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idClient = request.getParameter("idClient");
        GestionnaireClient gestonClient;
        //gestonClient = new GestionnaireClient(idClient);
        //Client client = gestonClient;

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        //response.getWriter().println(client.toString());
    }

	/*____________________________________________________________________________________________________________________
	 * doPost is expecting a HTTP parameter userId
	 * It sends back a XML serialization of the previous command with HTTP code 200 if a userId is specifyed
	 * It sends back a HTTP code 401 error if the userId is not specified or empty
	 * It sends back a HTTP code 500 error if a problem occured when accessing to the database
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        response.setContentType("text/plain");
        // Extract userId from HTTP parameters
        String userId = null;
        userId = request.getParameter("userId");
        // Call the database and return result
        if (userId != null || !userId.equals("")) {
            // CAS HTTP code 200
            try {
                String res = BdAccess.authentifyUser(userId);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(res + ". " + processQueryTest(request));
            } // CAS HTTP code 500
            catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println(e.toString());
            }
        } // CAS HTTP code 401
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("userId n'est pas specifiee");
        }
    }

	private String processQueryTest(HttpServletRequest request) {
		String res = "{";
		Enumeration<String> P = request.getParameterNames();
		while (P.hasMoreElements()) {
			String p = P.nextElement();
			res += "\"" + p + "\": \"" + request.getParameter(p) + "\", ";
		}
		return res + "}";
	}

}
