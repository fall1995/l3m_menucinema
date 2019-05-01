package database;

import classesgen.commande.Commande;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;

/**
 * @author Groupe6 La clase GestionnaireCommande pour la gestion des commandes
 * d'un client
 */
public class GestionnaireCommande extends SQLAble {

    private Commande commande;
    private double prix;

    public GestionnaireCommande(String idClient, List<String> idPlats, List<String> idFilms, String adresseLivraison) {
        commande = new Commande();
        commande.setIdClient(idClient);
        commande.setIdPlat(idPlats);
        commande.setIdFilm(idFilms);
        commande.setAdresseLivraison(adresseLivraison);
        // calculer du prix de la commande
        double prixFilms = sommeFilm(idFilms);
        double prixPlats = sommePlat(idPlats);
        prix = prixFilms + prixPlats;
        commande.setPrix(prix);

    }

    public GestionnaireCommande(String id) {
        commande = new Commande();
        commande.setId(id);
    }

    /**
     * Methode permet d'enregistrer la commande en cours l'objet this" dans la
     * BD
     *
     * @throws java.lang.Exception
     */
    public void enregistrerCommandeDB() throws Exception {
        try {
            System.out.println("prix dans la methode enregistre seclionner est : " + commande.getPrix());
            connectToDatabase();
            String adrLivr = commande.getAdresseLivraison();

            ARRAY arrayPlats
                    = CreateArray.toARRAY(commande.getIdPlat(), conn);
            ARRAY arrayFilms = CreateArray.toARRAY(commande.getFilm(), conn);
            CallableStatement cstmt;
            if ((arrayPlats != null && arrayPlats.length() > 0 && adrLivr != null && !adrLivr.isEmpty())
                    || (arrayFilms != null && arrayFilms.length() > 0 && adrLivr != null && !adrLivr.isEmpty())) {

                cstmt = conn.prepareCall("{ = call enregistrerCommande(?,?,?,?,?) }");
                cstmt.setString(1, commande.getIdClient());
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
     * Methode permet d'afficher  une  commande en prenant comme parametre id de
     * de la commande
     * @param id
     * @return commande
     */
    public Commande getCommande(String id) {

        Commande commande = new Commande();
        commande.setId(id);
        List<String> platsCommandes = commande.getIdPlat();
        List<String> filmsCommandes = commande.getFilm();

        try {
            connectToDatabase();
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ = call getcommande(?,?,?,?) }");
            ocstmt.setString(1, id);
            ocstmt.registerOutParameter(2, OracleTypes.CURSOR);
            ocstmt.registerOutParameter(3, OracleTypes.CURSOR);
            ocstmt.registerOutParameter(4, OracleTypes.CURSOR);
            ocstmt.execute();

            ResultSet rset = (ResultSet) (ocstmt.getObject(2));
            if (rset != null && rset.next()) {
                commande.setIdClient(rset.getString("idClient"));
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
     * Methode qui permet de calcule le prix total des films choisis par un
     * client client et qui prend en parametre la liste des films
     *
     * @param idFilms
     * @return res
     */
    public double sommeFilm(List<String> idFilms) {
        double res;
        int nbreFilm = idFilms.size();
        res = 3.79 * nbreFilm;
        return res;
    }

    /**
     * Methode qui permet de calcule le prix total des plats choisis par un
     * client client et qui prend en parametre la liste des des plats
     *
     * @param idplats
     * @return res
     */
    public double sommePlat(List<String> idplats) {
        double res;
        res = 0.0;
        double prixplat;
        for (int i = 0; i < idplats.size(); i++) {
            String idp = idplats.get(i);
            GestionnaireMenu gestionmenu = new GestionnaireMenu();
            prixplat = gestionmenu.getPrixPlat(idp);
            res += prixplat;
        }
        return res;
    }

    @Override
    public String toString() {
        return " { \n"
                + " idCommande : \"" + commande.getId() + "\"\n"
                + " idClient : \"" + commande.getIdClient() + " \n"
                + " idFilm : \"" + commande.getFilm().toString() + " \n"
                + " idPlat : \"" + commande.getIdPlat().toString() + " \n"
                + " Prix : \"" + commande.getPrix() + " \n"
                + " Date : \"" + commande.getDate() + " \n"
                + " Adresse de location : \"" + commande.getAdresseLivraison() + " \n"
                + '}';
    }

}
