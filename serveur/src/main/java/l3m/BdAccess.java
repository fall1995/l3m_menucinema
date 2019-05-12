package l3m;

import database.GestionnaireClient;
import classesgen.client.Client;

import database.GestionnaireClient;
import java.sql.SQLException;


/**
 * @author Groupe6 BdAccess est une classe qui
 * gere l'authentification de utilisateur
 */
public class BdAccess {

    static String authentifyUser(Client client) throws SQLException, Exception {
        String res = "";
        boolean nouveau = false;
        GestionnaireClient gc = new GestionnaireClient( client.getId(), 
                                                        client.getNom(),
                                                        client.getPrenom()
                                );     

        nouveau = gc.enregistreClientDB();
        if ( nouveau ){
            res = "nouveau";
        }else{
            res = "client";
        }
        
        return res;
    }

}
