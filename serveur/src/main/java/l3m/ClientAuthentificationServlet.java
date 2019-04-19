package l3m;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( this.processQueryTest(request) );
    }

	/*____________________________________________________________________________________________________________________
	 * doPost is expecting a HTTP parameter userId
	 * It sends back a XML serialization of the previous command with HTTP code 200 if a userId is specifyed
	 * It sends back a HTTP code 401 error if the userId is not specified or empty
	 * It sends back a HTTP code 500 error if a problem occured when accessing to the database
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		// Extract userId from HTTP parameters
        String userId = null;
        // Call the database and return result
        try {
	        String res = BdAccess.authentifyUser(userId);
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.getWriter().println( res  + ". " + processQueryTest(request) );
        } catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println( e.toString() );
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
