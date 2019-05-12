package l3m;

import classesgen.client.Client;
import com.google.gson.Gson;
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

/**
 * @author Groupe6 ClientAuthentificationServlet est une classe qui permet d'
 * assurre la connexion d'un client
 */
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
     * doGet va nous permettre de retourné les informations d'un client dont
     * l'id est passé en parametre
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println( "================================================== ClientAuthentificationServlet [doGet] ==================================================" );
        System.out.print( "Récupération des informations du client ...");
        String idClient = request.getParameter("idClient");
        Client client;
        try {
            client = GestionnaireClient.getClientDB(idClient);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(new Gson().toJson(client));
            System.out.println( "la récupération des informations est éffectuée avec succès !");
        
        } catch (SQLException ex) {
            Logger.getLogger(ClientAuthentificationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientAuthentificationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*____________________________________________________________________________________________________________________
	 * doPost is expecting a HTTP parameter userId
	 * It sends back a XML serialization of the previous command with HTTP code 200 if a userId is specifyed
	 * It sends back a HTTP code 401 error if the userId is not specified or empty
	 * It sends back a HTTP code 500 error if a problem occured when accessing to the database
     */
    /**
     * Methode qui permet d'assurer la connaissance
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println( "================================================== ClientAuthentificationServlet [doPost] ==================================================" );
        System.out.print("L'identification du client ...");
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

        // Extract userId from HTTP parameters
        String res = " ";
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
                
                if ( res.equals("client") ){
                    System.out.println("le client est identifié avec succès !");
                }else{
                    System.out.println("le client est logé avec succès !");
                }
                
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(new Gson().toJson(client));
            } // CAS HTTP code 500
            catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println(e.toString());
            } catch (Exception ex) {
                Logger.getLogger(ClientAuthentificationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // CAS HTTP code 401
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Parametre non complet");
        }
    }
    
    
    
     protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println( "================================================== ClientAuthentificationServlet [doPut] ==================================================" );
        System.out.print("Modification des données du client ..." );
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
        
        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter( p ));
        }
        
        try {
            //mise à jour
            GestionnaireClient gc = new GestionnaireClient( parametres.get("id"),
                                                            parametres.get("nom"),
                                                            parametres.get("prenom")
                                        );

            gc.editEmail(parametres.get("email"));
            gc.editAdresse(parametres.get("adresse"));
            gc.editPhoto(parametres.get("photo"));
            gc.editTel(parametres.get("tel"));
            gc.editClientDB();

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(gc.ClientToJson());
            System.out.println("la modification des informations est éffectuée avec succès !");
            
        } catch (SQLException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ClientAuthentificationServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
