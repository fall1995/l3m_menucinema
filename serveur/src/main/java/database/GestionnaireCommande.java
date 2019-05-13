package database;

import classesgen.commande.Commande;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * @author Groupe6 La clase GestionnaireCommande pour la gestion des commandes
 * d'un client
 */
public class GestionnaireCommande extends SQLAble {

    private Commande commande;

    /**
     *
     * @param idClient
     * @param idPlats
     * @param idFilms
     * @param adresseLivraison
     * @throws Exception
     */
    public GestionnaireCommande(String idClient, List<String> idPlats, List<String> idFilms, String adresseLivraison)
            throws Exception {

        commande = new Commande();
        commande.setIdClient(idClient);
        commande.getIdPlats().addAll(idPlats);
        commande.getIdFilms().addAll(idFilms);
        commande.setAdresseLivraison(adresseLivraison);
        computePrix();
    }

    /**
     *
     * @param id
     */
    public GestionnaireCommande(String id) {
        commande = new Commande();
        commande.setId(id);
    }

    // calcul du prix de la commande
    private void computePrix() throws Exception {

        double prixPlats = 0;
        double prixFilms = 0;
        double prixPlat = 0;
        int nbEffectFilms = 0;

        for ( String idFilm : commande.getIdFilms() ){
            if ( idFilm.length() > 1  &&  !idFilm.equals("null") ){
                nbEffectFilms++;
            }
        }
        
        prixFilms = 3.79 * nbEffectFilms;
         
        for (String idPlat : commande.getIdPlats()) {
            if (  idPlat.length() > 1  &&  !idPlat.equals("null") ){
                prixPlat = GestionnaireMenu.getPrixPlat(idPlat);
                if (prixPlat != -1 ){
                    prixPlats += prixPlat;
                }else{
                    throw new Exception("Le plat avec id " + idPlat + " n'est pas dans la carte !");
                }
            }
        }

        commande.setPrix(prixFilms + prixPlats);
    }

    /**
     *
     * @throws SQLException
     * @throws Exception
     */
    public void enregistrerCommandeDB() throws SQLException, Exception {
        try {
            connectToDatabase();

            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(
                    "insert into Commande (idClient, prix, adresseLivraison)"
                    + " values ( ? , ? , ? ) "
            );
            pstmt.setString(1, commande.getIdClient());
            pstmt.setDouble(2, commande.getPrix());
            pstmt.setString(3, commande.getAdresseLivraison());
            pstmt.executeUpdate();
            pstmt.close();

            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getLastIdCommandeDate( ? ) }");
            ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
            ocstmt.setString(2, commande.getIdClient());
            ocstmt.execute();

            ResultSet rset = (ResultSet) (ocstmt.getObject(1));
            if (rset != null && rset.next()) {
                commande.setId(rset.getString("idCommande"));
                commande.setDate( rset.getString("dateCommande"));
            }
            if (rset != null) {
                rset.close();
            }
            ocstmt.close();

            int nbInsertions = 1;

            Map<String, Integer> mapIdPlats;
            mapIdPlats = listToMap(commande.getIdPlats());

            for (Map.Entry<String, Integer> mapPlat : mapIdPlats.entrySet()) {
                String idPlat = mapPlat.getKey();
                if ( idPlat.length() > 1 && !idPlat.equals("null") ){
                    int quantite = mapPlat.getValue();
                    ajouterPlatQtDB(commande.getId(), idPlat, quantite);
                    nbInsertions++;
                }
            }

            for (String idFilm : commande.getIdFilms()) {
                if ( idFilm.length() > 1 && !idFilm.equals("null") ){
                    ajouterFilmDB(commande.getId(), idFilm);
                    nbInsertions++;
                }
            }

            if (nbInsertions > 1) {
                conn.commit();
            } else {
                if ( conn.isClosed() || conn == null ){
                    throw new Exception("Echec rollback, il n'y a pas de connection ");
                }
                conn.rollback();
                throw new Exception("Les listes des plats et des films sont vides");
            }

        } catch (SQLException e) {
            if ( conn.isClosed() || conn == null ){
                throw new Exception("Echec rollback, il n'y a pas de connection ");
            }
            conn.rollback();
            throw new SQLException(e);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public static Commande getCommande(String id) throws SQLException, Exception {
        GestionnaireCommande gc = new GestionnaireCommande(id);
        gc.connectToDatabase();
        Commande commande = new Commande();

        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ = call getcommande(?,?,?,?) }");
        ocstmt.setString(1, id);
        ocstmt.registerOutParameter(2, OracleTypes.CURSOR);
        ocstmt.registerOutParameter(3, OracleTypes.CURSOR);
        ocstmt.registerOutParameter(4, OracleTypes.CURSOR);
        ocstmt.execute();

        ResultSet rset = (ResultSet) (ocstmt.getObject(2));
        if (rset != null && rset.next()) {
            commande.setId(rset.getString("idCommande"));
            commande.setDate( rset.getString("dateCommande") );
            commande.setPrix(rset.getDouble("prix"));
            commande.setAdresseLivraison(rset.getString("adresseLivraison"));
        }
        if (rset != null) {
            rset.close();
        }

        rset = (ResultSet) (ocstmt.getObject(3));
        while (rset != null && rset.next()) {
            int quantite = rset.getInt("quantite");
            for (int i = 0; i < quantite; i++) {
                commande.getIdPlats().add(rset.getString("idPlat"));
            }
        }
        if (rset != null) {
            rset.close();
        }

        rset = (ResultSet) (ocstmt.getObject(4));
        while (rset != null && rset.next()) {
            commande.getIdFilms().add(rset.getString("idFilm"));
        }
        if (rset != null) {
            rset.close();
        }
        ocstmt.close();

        return commande;
    }

    /**
     *
     * @param idClient
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static Commande getLastCommande(String idClient) throws SQLException, Exception {
        GestionnaireCommande gc = new GestionnaireCommande("0");
        gc.connectToDatabase();

        Commande commande = new Commande();
        String idCommande = "";
        boolean trouve = false;

        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getLastIdCommandeDate( ? ) }");
        ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
        ocstmt.setString(2, idClient);
        ocstmt.execute();

        ResultSet rset = (ResultSet) (ocstmt.getObject(1));
        if (rset != null && rset.next()) {
            idCommande = rset.getString("idCommande");
            trouve = true;
        }
        if (rset != null) {
            rset.close();
        }
        ocstmt.close();

        if (trouve) {
            commande = getCommande(idCommande);
        }

        return commande;
    }

    /**
     *
     * @return @throws SQLException
     */
    public static List<String> getPlatsLesPlusCommandes() throws SQLException, Exception {
        GestionnaireCommande gc = new GestionnaireCommande("0");
        List<String> listIdPlatsPC = new ArrayList<String>();

        gc.connectToDatabase();
        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call platsLesPlusCommandes() }");
        ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
        ocstmt.execute();

        ResultSet rset = (ResultSet) (ocstmt.getObject(1));
        while (rset != null && rset.next()) {
            listIdPlatsPC.add(rset.getString("idPlat"));
        }
        if (rset != null) {
            rset.close();
        }
        ocstmt.close();
        
        return listIdPlatsPC;
    }

    /**
     *
     * @return @throws SQLException
     */
    public static List<String> getFilmsLesPlusVus() throws SQLException, Exception {
        GestionnaireCommande gc = new GestionnaireCommande("0");
        List<String> listIdFilmPV = new ArrayList<String>();

        gc.connectToDatabase();
        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call filmslesplusVus() }");
        ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
        ocstmt.execute();

        ResultSet rset = (ResultSet) (ocstmt.getObject(1));
        while (rset != null && rset.next()) {
            listIdFilmPV.add(rset.getString("idFilm"));
        }
        if (rset != null) {
            rset.close();
        }
        ocstmt.close();
        
        return listIdFilmPV;
    }

    /**
     *
     * @param idFilm
     * @return
     * @throws SQLException
     */
    public static List<String> getPlatsLesPlusCommandesAvec(String idFilm) throws SQLException, Exception {
        GestionnaireCommande gc = new GestionnaireCommande("0");
        List<String> listIdPlatsPCA = new ArrayList<String>();

        gc.connectToDatabase();
        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call platslespluscommandesAvec( ? ) }");
        ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
        ocstmt.setString(2, idFilm);
        ocstmt.execute();

        ResultSet rset = (ResultSet) (ocstmt.getObject(1));
        while (rset != null && rset.next()) {
            listIdPlatsPCA.add(rset.getString("idPlat"));
        }
        if (rset != null) {
            rset.close();
        }
        ocstmt.close();

        return listIdPlatsPCA;
    }

    /**
     *
     * @param idPlat
     * @return
     */
    public static List<String> getFilmsLesPlusVusAvec(String idPlat) throws SQLException, Exception {
        GestionnaireCommande gc = new GestionnaireCommande("0");
        List<String> listIdFilmPVA = new ArrayList<String>();

        gc.connectToDatabase();
        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call filmslesplusVusAvec( ? ) }");
        ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
        ocstmt.setString(2, idPlat);
        ocstmt.execute();

        ResultSet rset = (ResultSet) (ocstmt.getObject(1));
        while (rset != null && rset.next()) {
            listIdFilmPVA.add(rset.getString("idFilm"));
        }
        if (rset != null) {
            rset.close();
        }
        ocstmt.close();

        return listIdFilmPVA;
    }

    /**
     *
     * @param idPlat
     * @return
     */
    private void ajouterPlatQtDB(String idCommande, String idPlat, int quantite) 
            throws SQLException, Exception {
        
        connectToDatabase();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(
                "insert into PlatsCommandes values ( ? , ? , ? )"
        );
        pstmt.setString(1, idCommande);
        pstmt.setString(2, idPlat);
        pstmt.setInt(3, quantite);
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     *
     * @param idPlat
     * @return
     */
    private void ajouterFilmDB(String idCommande, String idFilm) 
            throws SQLException, Exception {
        
        connectToDatabase();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(
                "insert into FilmsCommandes values ( ? , ? )"
        );
        pstmt.setString(1, idCommande);
        pstmt.setString(2, idFilm);
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     *
     * @param idPlat
     * @return
     */
    private Map<String, Integer> listToMap(List<String> list) {
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        String elt = "";
        int nbOccur = 0;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                elt = list.get(i);
                nbOccur = 0;
                for (int j = i; j < list.size(); j++) {
                    if (elt.equals(list.get(j))) {
                        nbOccur++;
                    }
                }
                if (!hashMap.containsKey(elt)) {
                    hashMap.put(elt, nbOccur);
                }
            }
        }
        return hashMap;
    }

    /**
     *
     * @return
     */
    public String CommandeToJson() {
        String json = new Gson().toJson(this.commande);
        return json;
    }

}
