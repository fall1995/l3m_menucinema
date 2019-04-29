package database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.Client;
import database.SQLAble;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * @author Groupe6 Classe GestionnaireClient qui permet de gerer un client
 */
public class GestionnaireClient extends SQLAble {

    private Client client;

    public GestionnaireClient(String id, String nom, String premon) throws SQLException {
        client = new Client();
        client.setId(id);
        client.setNom(nom);
        client.setPrenom(premon);
        connectToDatabase();
        init();

    }

    protected void init() {

        try {
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getClient(?) }");
            ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
            ocstmt.setString(2, client.getId());
            ocstmt.execute();

            ResultSet rset = (ResultSet) (ocstmt.getObject(1));

            if (rset != null && rset.next()) {
                client.setAdresse(rset.getString("adresse"));
                client.setEmail(rset.getString("email"));
                client.setPhoto(rset.getString("photo"));
                client.setTel(rset.getString("tel"));
            }
            rset.close();
            ocstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return client.getNom();
    }

    /**
     * Methode qui permet d'envoyer le prenom
     *
     * @return prenom
     */
    public String getPrenom() {
        return client.getPrenom();
    }

    /**
     * Methode qui permet d'envoyer la photo
     *
     * @return photo
     */
    public String getPhoto() {
        return client.getPhoto();
    }

    /**
     * Methode qui permet d'envoyer l'adresse
     *
     * @return adresse
     */
    public String getAdresse() {
        return client.getAdresse();
    }

    /**
     * Methode qui permet de modifier l'email
     *
     * @param email
     */
    public void editEmail(String email) {
        client.setEmail(email);
    }

    /**
     * Methode qui permet de modifier l'adresse
     *
     * @param adresse
     */
    public void editAdresse(String adresse) {
        client.setAdresse(adresse);
    }

    /**
     * Methode qui permet de modifier le numero de tel
     *
     * @param tel
     */
    public void editTel(String tel) {
        client.setTel(tel);
    }

    /**
     * Methode qui permet de modifier la photo
     *
     * @param photo
     */
    public void editPhoto(String photo) {
        client.setPhoto(photo);
    }

    /**
     * Methode qui permet d'ajouter un client dans la BD
     *
     * @return true si l'enregistrement a eu lieu, sinon il retourne false
     */
    public boolean enregistreClientDB() throws SQLException {
        boolean exist = existsClientDB();
        boolean res = false;
        CallableStatement cstmt = null;
        if (!exist) {
            try {
                connectToDatabase();
                cstmt = conn.prepareCall("{ call enregistrerClient(?,?,?) }");
                cstmt.setString(1, client.getId());
                cstmt.setString(2, client.getNom());
                cstmt.setString(3, client.getPrenom());
                cstmt.execute();
                res = true;
            } catch (SQLException e) {
                System.out.println("Message erreur: " + e.getMessage());
            } finally {
                if (cstmt != null) {
                    cstmt.close();

                }
                if (conn != null) {
                    disconnect();
                }
            }
        }else{
            // si le client existe on le selectionne
            res = true;
        }
        return res;
    }

    /**
     * Methode qui permet de verifier si un client existe Cette méthode fait
     * appel à la procedure PL SQL existsClient qui prend en paramètre idClient
     * et qui retoune 1 si ce client existe, sinon il retoune 0
     *
     * @return true si le client est déja dans la BD, sinon il retourne false
     */
    public boolean existsClientDB() throws SQLException {
        boolean res = false;
        OracleCallableStatement ocstmt = null;
        ResultSet rs = null;
        try {
            connectToDatabase();
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call existsClient(?) }");
            ocstmt.registerOutParameter(1, OracleTypes.NUMBER);
            ocstmt.setString(2, client.getId());
            ocstmt.execute();

            int ret = ocstmt.getInt(1);
            if (ret > 0) {
                res = true;
            }

        } catch (SQLException e) {
            System.out.println("client exists dans la base");
            System.out.println(e.getMessage());
        } finally {
            if (ocstmt != null) {
                ocstmt.close();

            }
            if (conn != null) {
                disconnect();
            }
        }

        return res;
    }

    public void editClientDB() {
        //boolean exist = existsClientDB();
        //if (exist) {
        try {
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ = call editclientDB(?,?,?,?,?) }");
            cstmt.setString(1, client.getId());
            cstmt.setString(2, client.getPhoto());
            cstmt.setString(3, client.getEmail());
            cstmt.setString(4, client.getTel());
            cstmt.setString(5, client.getAdresse());
            cstmt.execute();
            cstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //}
    }

    /**
     * Methode qui permet d'optenir l'id du client avec le nom et le prenom
     * passés en paramètres
     *
     * @param nom
     * @param prenom
     * @return id
     */
    public static String getClientIdDB(String nom, String prenom) {
        String idClient = "";
        try {
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ ? = call getClientId(?,?) }");
            cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
            cstmt.setString(2, nom);
            cstmt.setString(3, prenom);
            cstmt.execute();

            idClient = cstmt.getString(1);
            cstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idClient;
    }

    /**
     * Methode qui permet de suppimer un client dont id est passe en paramètre
     * Cette méthode utilise la procedure PL SQL deleteClient, qui lève une
     * exception si aucune suppression n'a été effectuée.
     *
     * @param id
     * @return
     */
    public static boolean deleteClientDB(String id) {
        boolean res = false;
        try {
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ = call deleteClient(?) }");
            cstmt.setString(1, id);
            cstmt.execute();
            res = true;
            cstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Methode qui permet d'optenir la liste des commandes du client encours
     *
     * @return list
     */
    public List<String> getListeCommandes() throws SQLException {
        List<String> list = new ArrayList<String>();
        try {
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getListeCommandes(?) }");
            ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
            ocstmt.setString(2, client.getId());
            ocstmt.execute();

            ResultSet rset = (ResultSet) (ocstmt.getObject(1));
            while (rset != null && rset.next()) {
                list.add(rset.getString("idCommande"));
            }
            rset.close();
            ocstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
