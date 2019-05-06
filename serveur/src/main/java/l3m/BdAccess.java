package l3m;

import database.GestionnaireClient;
import classesgen.client.Client;

import database.GestionnaireClient;
import java.sql.SQLException;

// Je suis passé par l'itération 0 de la BD...
public class BdAccess {

    static String authentifyUser(String userId) throws SQLException, Exception {
        String res = "";
        boolean request = false;
        /*GestionnaireClient clt = new GestionnaireClient( userId , "" , "" );
        request = clt.existsClientDB();
        if (!request) {
            if ( clt.enregistreClientDB() ) {
                // enregistrement ok return le json
                //client.toString();
            } else {
                res = "le client existe";
                //client.toString();
            }
        } else {
            res ="champs non complet";
        }*/
        return "OK";
    }

}

