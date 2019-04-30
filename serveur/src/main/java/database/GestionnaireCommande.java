package database;

import classesgen.commande.Commande;
import classesgen.plat.Plat;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;

/**
 * @author Groupe6 La clase GestionnaireCommande pour la gestion des commandes
 */
public class GestionnaireCommande extends SQLAble {

    private Commande commande;

    /**
     * Constructeur qui prend en parametre idclient, liste des IdPlats, liste
     * des idFilms,et adresse de livraisons et les modifie *
     * @param idClient
     * @param idPlats
     * @param idFilms
     * @param adresseLivraison
     * @throws java.sql.SQLException
     */
    public GestionnaireCommande(String idClient, List<String> idPlats, List<String> idFilms, String adresseLivraison) throws SQLException {
        commande = new Commande();
        commande.setId(idClient);
        commande.getIdPlat().addAll(idPlats);
        commande.getFilm().addAll(idFilms);
        commande.setAdresseLivraison(adresseLivraison);
        connectToDatabase();
    }

    /**
     * Methode qui permet d'initialiser le id et prend en parametre le nouveau
     *
     * @param id
     * @throws java.sql.SQLException
     */
    public GestionnaireCommande(String id) throws SQLException {
        commande = new Commande();
        commande.setId(id);
        connectToDatabase();
    }

    /**
     * Methode qui permet d'enregistre une commander
     * @throws java.sql.SQLException
     */
    public void enregistrerCommandeDB() throws Exception {
        connectToDatabase();
        try {
            
            List<String> listIdPlats = commande.getIdPlat();
        
            List<String> listIdFilms = commande.getFilm();
            String adrLivr = commande.getAdresseLivraison();

            ARRAY arrayPlats = CreateArray.toARRAY(listIdPlats, conn);
            ARRAY arrayFilms = CreateArray.toARRAY(listIdFilms, conn);
            CallableStatement cstmt;
            if ((!arrayPlats.isNull() && listIdPlats.size() > 0 && adrLivr != null && !adrLivr.isEmpty())
                    || (!arrayFilms.isNull() && listIdFilms.size() > 0 && adrLivr != null && !adrLivr.isEmpty())) {

                cstmt = conn.prepareCall("{ = call enregistrerCommande(?,?,?,?,?) }");
                cstmt.setString(1, commande.getId());
                cstmt.setObject(2, arrayPlats);
                cstmt.setObject(3, arrayFilms);
                cstmt.setDouble(4, commande.getPrix());
                cstmt.setString(5, commande.getAdresseLivraison());
                cstmt.executeUpdate();
                cstmt.close();

                OracleCallableStatement ocstmt;
                ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getLastIdCommande() }");
                ocstmt.registerOutParameter(1, OracleTypes.VARCHAR);
                ocstmt.execute();

                commande.setId(ocstmt.getString(1));
                ocstmt.close();

            } else {
                throw new Exception("Parametre non valide ( null ou vide ) pour l'appel de la procedure enregistrerCommande");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode qui renvoye une commande grace à id passe en parametre
     *
     * @param id
     * @return commande
     */
    public static Commande getCommande(String id) {
        Commande commande = new Commande();
        commande.setId(id);
        
        List<String> platsCommandes = commande.getIdPlat();
      
        List<String> filmsCommandes = commande.getFilm();

        try {
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ = call getcommande(?,?,?,?) }");
            ocstmt.setString(1, id);
            ocstmt.registerOutParameter(2, OracleTypes.CURSOR);
            ocstmt.registerOutParameter(3, OracleTypes.CURSOR);
            ocstmt.registerOutParameter(4, OracleTypes.CURSOR);
            ocstmt.execute();

            ResultSet rset = (ResultSet) (ocstmt.getObject(2));
            if (rset != null && rset.next()) {
                commande.setId(rset.getString("idClient"));
                commande.setDate((rset.getDate("dateCommande")).toString());
                commande.setPrix(rset.getDouble("prix"));
                commande.setAdresseLivraison(rset.getString("adresseLivraison"));
            }
            rset.close();

            rset = (ResultSet) (ocstmt.getObject(3));
            while (rset != null && rset.next()) {
                int quantite = rset.getInt("quantite");
                for (int i = 0; i < quantite; i++) {
                    platsCommandes.add(rset.getString("idPlat"));
                }
            }
            rset.close();

            rset = (ResultSet) (ocstmt.getObject(4));
            while (rset != null && rset.next()) {
                filmsCommandes.add(rset.getString("idFilm"));
            }
            rset.close();

            ocstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            //System.err.println(e);    
        }

        return commande;
    }

    /**
     * Methode qui modifier une commande en prenant en parametre la nouvelle
     * commande
     *
     * @param commande
     */
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Commande getCommande() {
        return commande;
    }

}
