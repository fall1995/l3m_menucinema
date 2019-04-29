package database;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Groupe6 La clase GestionnaireCommande pour la gestion des commandes
 */
public class GestionnaireCommande extends SQLAble {

    private Commande commande;

    /**
     * Constructeur qui prend en parametre idclient, liste des IdPlats, liste
     * des idFilms,et adresse de livraisons et les modifie
     *
     * @param idClient
     * @param idPlat
     * @param idFilms
     * @param adresseLivraison
     */
    public GestionnaireCommande(String idClient, ArrayList<String> idPlat,
            ArrayList<String> idFilms, String adresseLivraison) {
        commande.setId(idClient);
        commande.setIdplats(idPlat);
        commande.setIdFilms(idFilms);
        commande.setAdresseLivraison(adresseLivraison);
    }

    /**
     * Methode qui permet d'initialiser le id et prend en parametre le nouveau
     *
     * @param id
     */
    public GestionnaireCommande(String id) {
        commande.setId(id);
    }

    /**
     * Methode qui permet d'enregistre une commander
     */
    public void enregistreCommanderDB() throws SQLException {
        CallableStatement cstmt;
        cstmt = conn.prepareCall("{ = call enregistrerClient(?,?,?) }");
        cstmt.setString(1, commande.getId());
        cstmt.setString(2, commande.getIdClient());
        cstmt.setString(3, " ");
        cstmt.setString(4," " );
        cstmt.setString(5, commande.getDate());
        cstmt.setDouble(6, commande.getPrix());
        cstmt.setString(7, commande.getAdresseLivraison());

        cstmt.execute();
        cstmt.close();

    }

    /**
     * Methode qui renvoye une commande grace à id passe en parametre
     * @param id
     * @return commande
     * @throws java.sql.SQLException
     */
    public Commande getCommande(String id) throws SQLException {
        Commande res = new Commande();
        String requete = "{ ? = call getClientId(?,?) }";
        ResultSet reslreq = request(requete);
        while (reslreq.next()) {
            String idCommande = reslreq.getString("id");
            String idClient = reslreq.getString("idClient");
            String idplats = reslreq.getString("idplats");
            String idFilms = reslreq.getString("idFilms");
            double price = reslreq.getDouble("price");
            Date dateCommande = reslreq.getDate("dateCommande");
            String adresseLivraison = reslreq.getString("adresseLivraison");

            res.setId(id);
            res.setIdClient(idClient);
            res.setIdFilms(idFilms);
            res.setIdplats(idplats);
            res.setAdresseLivraison(adresseLivraison);
            res.setPrix(price);
        }

        return res;
    }

    
    /**
     * Methode qui modifier une commande  en prenant en parametre la nouvelle 
     * commande
     * @param commande
     */
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

}
