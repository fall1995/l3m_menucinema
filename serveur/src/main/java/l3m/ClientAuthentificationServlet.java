package l3m;

import classesgen.client.Client;
import database.GestionnaireClient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Je suis passé par l'itération 0 du serveur...
public class ClientAuthentificationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final String id = "idClient";
    private final String nom = "nom";
    private final String prenom = "prenom";
    private final String email = "email";
    private final String tel = "tel";
    private final String photo = "photo";
    private final String adresse = "adresse";

    /**
     * doGet va nous permettre de retourné les informations d'un client dont l'id est passé en 
     * parametre
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

        String res = "";
        HashMap<String, String> parametres = new HashMap();
        Client client = new Client();
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(this.processQueryTest(request));

        parametres.put(id, request.getParameter("idClient"));
        response.setContentType("application/json");
        System.out.println("idclient " + parametres.get(id));

        if (getClientinfo(parametres)) {
            
            // 200 OK
            try {
                GestionnaireClient gestionClient = new GestionnaireClient(parametres.get(id));
                System.out.println("parametres : "+parametres.get(id));
                client = gestionClient.getClient(id);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(client.toString());
            } catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println(""+e.toString());
                
            }
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Parametre non complet");
        }
    }

    /*____________________________________________________________________________________________________________________
	 * doPost is expecting a HTTP parameter userId
	 * It sends back a XML serialization of the previous command with HTTP code 200 if a userId is specifyed
	 * It sends back a HTTP code 401 error if the userId is not specified or empty
	 * It sends back a HTTP code 500 error if a problem occured when accessing to the database
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

        // Extract userId from HTTP parameters
        String res = "";
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
        Client client = new Client();

        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter((String) p));
        }
        // Call the database and return result
        if (postValide(parametres)) {
            // CAS HTTP code 200
            try {

                client.setId(parametres.get("idClient"));
                client.setNom(parametres.get("nom"));
                client.setPrenom(parametres.get("prenom"));
                res = BdAccess.authentifyUser(client);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(res);
            } // CAS HTTP code 500
            catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println(e.toString());
            }
        } // CAS HTTP code 401
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Parametre non complet");
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

    boolean postValide(HashMap<String, String> parametres) {
        boolean res = false;
        //on verifie si le parametre contient une clé
        if (parametres.containsKey(id) && parametres.containsKey(nom) && parametres.containsKey(prenom)) {
            //verification si les valeurs ne sont pas null
            if (parametres.get(id) != null && parametres.get(nom) != null && parametres.get(prenom) != null) {
                res = true;
            }
        }

        return res;
    }

    private boolean getClientinfo(HashMap<String, String> parametres) {
        boolean valide = false;

        if (parametres.containsKey(id)) {
            //verification si les valeurs ne sont pas null
            if (parametres.get(id) != null) {
                valide = true;
            }
        }
        return valide;
    }

}
