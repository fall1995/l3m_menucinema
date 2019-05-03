package l3m;

import database.GestionnaireClient;
import classesgen.client.Client;

import database.GestionnaireClient;
import java.sql.SQLException;

// Je suis passé par l'itération 0 de la BD...
public class BdAccess {

    static String authentifyUser(Client client) throws SQLException, Exception {
        String res = "";
        boolean request = false;
        GestionnaireClient gc;
        gc = new GestionnaireClient(client.getId(), client.getNom(),client.getPrenom());
        request = gc.existsClientDB();
        if (!request) {
            if (gc.enregistreClientDB()) {
                // enregistrement ok return le json
                client.toString();
            } else {
                res = "le client existe";
            }
        } else {
            res ="champs non complet";
        }
        return res;
    }

}
