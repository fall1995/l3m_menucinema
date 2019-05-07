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
        boolean request = false;
        GestionnaireClient clt = new GestionnaireClient(client.getId(), client.getNom(),client.getPrenom());
        System.out.println("idClient"+client.getId());
        System.out.println("idClient"+client.getNom());
        System.out.println("idClient"+client.getPrenom());
        request = clt.existsClientDB();
        if (!request) {
            if (clt.enregistreClientDB()) {
                // enregistrement ok return le json
                client.toString();
            } else {
                res = "le client existe";
                client.toString();
            }
        } else {
            res ="champs non complet";
        }
        return res;
    }

}
