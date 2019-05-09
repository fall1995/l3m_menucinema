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



/**
 * @author Groupe6 SuggestionFilmsServlet est une classe qui 
 * permet de modifier le client quelconque
 */
public class UpdateClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String id = "id";
    private final String nom = "nom";
    private final String prenom = "prenom";
    private final String email = "email";
    private final String tel = "tel";
    private final String photo = "photo";
    private final String adresse = "adresse";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * authorisation de la connection avec les plateformes exterieur
         */
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();

        /**
         * Parcours les paramètres de la requete et construit une structure
         * clé-valeur
         */
        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter( p ));
        }

        /**
         * try :si toute les valeurs sont ok modification ok et reponse du
         * serveur ok, catch : si non le serveur return le msg d'erreur else :
         * parametre non complets
         */
        if (putValide(parametres)) {
            try {
                //mise à jour
                GestionnaireClient gc = new GestionnaireClient(parametres.get(id), 
                                                                parametres.get(nom),
                                                                parametres.get(prenom)
                                        );

                gc.editEmail( parametres.get(email) );
                gc.editAdresse( parametres.get(adresse) );
                gc.editPhoto(parametres.get(photo));
                gc.editTel(parametres.get(tel));
                gc.editClientDB();
                
                response.setStatus(HttpServletResponse.SC_OK);

                response.getWriter().println( gc.ClientToJson() );

            } catch (SQLException ex) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().println(ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(UpdateClientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //on retourne un message d'erreur
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Paramètres non complet");
        }

    }

    /**
     * renvoie vraie si les clefs valeur sont correct
     *
     * @param parametres
     * @return
     */
    boolean putValide(HashMap<String, String> parametres) {
        boolean res = false;
        //on verifie si le parametre contient une clé
        if (parametres.containsKey(id) && parametres.containsKey(nom) && parametres.containsKey(prenom)
                && parametres.containsKey(email) && parametres.containsKey(tel) && parametres.containsKey(photo)
                && parametres.containsKey(adresse)) {
            //verification si les valeurs ne sont pas null
            if (parametres.get(id) != null && parametres.get(nom) != null && parametres.get(prenom) != null
                    && parametres.get(email) != null && parametres.get(tel) != null && parametres.get(photo) != null
                    && parametres.get(adresse) != null) {
                res = true;
            }
        }

        return res;
    }
    
    
}
