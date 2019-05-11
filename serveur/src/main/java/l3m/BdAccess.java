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
        
        System.out.println("idClient "+client.getId());
        System.out.println("nom "+client.getNom());
        System.out.println("prenom "+client.getPrenom());
        

        nouveau = gc.enregistreClientDB();
        if ( nouveau ){
            res = "nouveau";
            System.out.println("Creation d'un nouveau client avec succès !");//print pour le scénario de la démo
        }else{
            res = "client";
            System.out.println("Le client " + client.getId() + " existe déja dans la DB");//print pour le scénario de la démo
        }
        
        return res;
    }

}
