package database;

import classesgen.commande.Commande;
import com.google.gson.Gson;
import java.sql.CallableStatement;
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

            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ ? = call getLastIdCommande() }");
            cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
            cstmt.execute();

            commande.setId(cstmt.getString(1));
            cstmt.close();

            System.out.println("id = " + commande.getId());
            int nbInsertions = 1;

            Map<String, Integer> mapIdPlats;
            mapIdPlats = listToMap(commande.getIdPlat());

            for (Map.Entry<String, Integer> mapPlat : mapIdPlats.entrySet()) {
                String idPlat = mapPlat.getKey();
                int quantite = mapPlat.getValue();
                ajouterPlatQtDB(commande.getId(), idPlat, quantite);
                nbInsertions++;
            }

            for (String idFilm : commande.getFilm()) {
                ajouterFilmDB(commande.getId(), idFilm);
                nbInsertions++;
            }

            if (nbInsertions > 1) {
                conn.commit();
            } else {
                conn.rollback();
                System.out.println("Les listes des plats et des films sont vides");
            }

        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Methode permet d'afficher une commande en prenant comme parametre id de
     * de la commande
     *
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
    
    
    public List<String> getPlatsLesPlusCommandes(){
        List<String> listIdPlatsPC = new ArrayList<String>();
        try{
            connectToDatabase();
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call platsLesPlusCommandes() }");
            ocstmt.registerOutParameter( 1 , OracleTypes.CURSOR );
            ocstmt.execute();
            
            ResultSet rset = (ResultSet) (ocstmt.getObject(1));
            while (rset != null && rset.next()) {
                listIdPlatsPC.add( rset.getString("idPlat"));
            }
            rset.close();
            
        }catch ( SQLException e){
            e.printStackTrace();
        }
        return listIdPlatsPC;
    }

    
    public List<String> getFilmsLesPlusVus(){
        List<String> listIdFilmPV = new ArrayList<String>();
        try{
            connectToDatabase();
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call filmslesplusVus() }");
            ocstmt.registerOutParameter( 1 , OracleTypes.CURSOR );
            ocstmt.execute();
            
            ResultSet rset = (ResultSet) (ocstmt.getObject(1));
            while (rset != null && rset.next()) {
                listIdFilmPV.add( rset.getString("idFilm"));
            }
            rset.close();
            
        }catch ( SQLException e){
            e.printStackTrace();
        }
        return listIdFilmPV;
    }
    
    
    public List<String> getPlatsLesPlusCommandesAvec( String idFilm ){
        List<String> listIdPlatsPCA = new ArrayList<String>();
        try{
            connectToDatabase();
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call platslespluscommandesAvec( ? ) }");
            ocstmt.registerOutParameter( 1 , OracleTypes.CURSOR );
            ocstmt.setString( 2 , idFilm );
            ocstmt.execute();
            
            ResultSet rset = (ResultSet) (ocstmt.getObject(1));
            while (rset != null && rset.next()) {
                listIdPlatsPCA.add( rset.getString("idPlat"));
            }
            rset.close();
            
        }catch ( SQLException e){
            e.printStackTrace();
        }
        return listIdPlatsPCA;
    }
    
    
    public List<String> getFilmsLesPlusVusAvec( String idPlat ){
        List<String> listIdFilmPVA = new ArrayList<String>();
        try{
            connectToDatabase();
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call filmslesplusVusAvec( ? ) }");
            ocstmt.registerOutParameter( 1 , OracleTypes.CURSOR );
            ocstmt.setString( 2 , idPlat );
            ocstmt.execute();
            
            ResultSet rset = (ResultSet) (ocstmt.getObject(1));
            while (rset != null && rset.next()) {
                listIdFilmPVA.add( rset.getString("idFilm"));
            }
            rset.close();
            
        }catch ( SQLException e){
            e.printStackTrace();
        }
        return listIdFilmPVA;
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
    
      private void ajouterPlatQtDB( String idCommande , String idPlat , int quantite ) throws SQLException{
        connectToDatabase();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(
                "insert into PlatsCommandes values ( ? , ? , ? )"
        );
        pstmt.setString( 1 , idCommande );
        pstmt.setString( 2 , idPlat );
        pstmt.setInt( 3 , quantite );
        pstmt.executeUpdate();
        pstmt.close();
    }
      
      private void ajouterFilmDB( String idCommande , String idFilm ) throws SQLException{
        connectToDatabase();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(
                "insert into FilmsCommandes values ( ? , ? )"
        );
        pstmt.setString( 1 , idCommande );
        pstmt.setString( 2 , idFilm );
        pstmt.executeUpdate();
        pstmt.close();
    }
      
        private Map<String,Integer> listToMap(List<String> list ){
        Map<String,Integer> hashMap = new HashMap<String,Integer>();
        String elt = "";
        int nbOccur = 0;
        if ( list != null ){
            for ( int i = 0 ; i < list.size() ; i++ ){
                elt = list.get(i);
                nbOccur = 0;
                for ( int j = i ; j < list.size() ; j++ ){
                    if ( elt.equals( list.get(j)) ){
                        nbOccur++;
                    }
                }
                if ( !hashMap.containsKey(elt)){
                    hashMap.put( elt , nbOccur );
                }
            }
        }
        return hashMap;
    }
        
        public String gestionCommandeToJson(){
            String json = new Gson().toJson(this.commande);
            return json;
        } 

 @Override
    public String toString() {
        return " { \n" 
                    + " id : \""   + commande.getId() + "\"\n" 
                    + " idClient : \""   + commande.getIdClient() + "\"\n" 
                    + " idPlats : \""   + commande.getIdPlat().toString() + "\"\n" 
                    + " idFilms : \""   + commande.getFilm().toString() + "\"\n" 
                    + " idPrix : \""   + commande.getPrix() + "\"\n" 
                    + " dateCommande : \""   + commande.getDate() + "\"\n" 
                    + " adresseLivraison : \""   + commande.getAdresseLivraison() + "\"\n" 
                + "}";
    } 

}
