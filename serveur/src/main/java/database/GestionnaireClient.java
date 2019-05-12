package database;

import classesgen.client.Client;
import com.google.gson.Gson;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * @author Groupe6 Classe GestionnaireClient qui permet de gerer un client
 */
public class GestionnaireClient extends SQLAble {

    private Client client;

    /**
     *
     * @param id
     * @param nom
     * @param premon
     */
    public GestionnaireClient(String id, String nom, String premon) {
        this.client = new Client();
        client.setId(id);
        client.setNom(nom);
        client.setPrenom(premon);
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return client.getNom();
    }

    /**
     *
     * @return
     */
    public String getPrenom() {
        return client.getPrenom();
    }

    /**
     *
     * @return
     */
    public String getPhoto() {
        return client.getPhoto();
    }

    /**
     *
     * @return
     */
    public String getAdresse() {
        return client.getAdresse();
    }

    /**
     *
     * @param email
     */
    public void editEmail(String email) {
        client.setEmail(email);
    }

    /**
     *
     * @param adresse
     */
    public void editAdresse(String adresse) {
        client.setAdresse(adresse);
    }

    /**
     *
     * @param tel
     */
    public void editTel(String tel) {
        client.setTel(tel);
    }

    /**
     *
     * @param photo
     */
    public void editPhoto(String photo) {
        client.setPhoto(photo);
    }

    /**
     * Methode qui permet d'enregistrer un client dans la base
     *
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public boolean enregistreClientDB() throws SQLException, Exception {
        boolean exist = existsClientDB();
        boolean res = false;
        if (!exist) {
            connectToDatabase();
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ = call enregistrerClient(?,?,?) }");
            cstmt.setString(1, client.getId());
            cstmt.setString(2, client.getNom());
            cstmt.setString(3, client.getPrenom());
            cstmt.execute();
            cstmt.close();
            res = true;
        }
        return res;
    }

    /**
     * Methode qui permet de verifie si un client existe dans la base
     *
     * @return
     * @throws SQLException
     */
    protected boolean existsClientDB() throws SQLException, Exception {
        boolean res = false;

        connectToDatabase();
        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call existsClient(?) }");
        ocstmt.registerOutParameter(1, OracleTypes.NUMBER);
        ocstmt.setString(2, client.getId());
        ocstmt.execute();

        int ret = ocstmt.getInt(1);
        if (ret == 1) {
            res = true;
        }

        return res;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static Client getClientDB(String id) throws SQLException, Exception {
        GestionnaireClient gc = new GestionnaireClient(id, "", "");
        gc.connectToDatabase();

        Client client = new Client();

        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getClient(?) }");
        ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
        ocstmt.setString(2, id);
        ocstmt.execute();
        ResultSet rset = (ResultSet) (ocstmt.getObject(1));

        if (rset != null && rset.next()) {
            client.setId(rset.getString("idClient"));
            client.setNom(rset.getString("nom"));
            client.setPrenom(rset.getString("prenom"));
            client.setAdresse(rset.getString("adresse"));
            client.setEmail(rset.getString("email"));
            client.setPhoto(rset.getString("photo"));
            client.setTel(rset.getString("tel"));
        }
        if (rset != null) {
            rset.close();
        }
        ocstmt.close();

        return client;
    }

    /**
     * Methode qui permet de modifier un client
     *
     * @throws SQLException
     * @throws Exception
     */
    public void editClientDB() throws SQLException, Exception {
        boolean exist = existsClientDB();
        if (exist) {
            connectToDatabase();
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ = call editClient(?,?,?,?,?) }");
            cstmt.setString(1, client.getId());
            cstmt.setString(2, client.getPhoto());
            cstmt.setString(3, client.getEmail());
            cstmt.setString(4, client.getTel());
            cstmt.setString(5, client.getAdresse());
            cstmt.execute();
            cstmt.close();
        }
    }

    /**
     * Methode qui permet de retoune id du client
     *
     * @param nom
     * @param prenom
     * @return
     * @throws SQLException
     */
    public static String getClientIdDB(String nom, String prenom)
            throws SQLException, Exception {
        GestionnaireClient gc = new GestionnaireClient("", "", "");
        gc.connectToDatabase();
        String idClient = "";

        CallableStatement cstmt;
        cstmt = conn.prepareCall("{ ? = call getClientIdDB(?,?) }");
        cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
        cstmt.setString(2, nom);
        cstmt.setString(3, prenom);
        cstmt.execute();

        idClient = cstmt.getString(1);
        cstmt.close();

        return idClient;
    }

    /**
     * Methode qui permet de supprimer un client
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean deleteClientId(String id)
            throws SQLException, Exception {

        GestionnaireClient gc = new GestionnaireClient(id, "", "");
        boolean exist = false;
        boolean res = false;

        gc.connectToDatabase();
        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call existsClient(?) }");
        ocstmt.registerOutParameter(1, OracleTypes.NUMBER);
        ocstmt.setString(2, id);
        ocstmt.execute();

        int ret = ocstmt.getInt(1);

        if (ret == 1) {
            exist = true;
        }

        ocstmt.close();

        if (exist) {
            CallableStatement cstmt = conn.prepareCall("{ = call deleteClient(?) }");
            cstmt.setString(1, id);
            cstmt.execute();
            res = true;
            cstmt.close();
        } else {
            throw new Exception("Client inexistant !");
        }

        return res;
    }

    /**
     * Methode qui affiche les commandes d'un client
     *
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<String> getListeCommandes() throws SQLException, Exception {
        boolean exist = existsClientDB();
        List<String> list = new ArrayList<>();

        if (exist) {
            connectToDatabase();
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getListeCommandes(?) }");
            ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
            ocstmt.setString(2, client.getId());
            ocstmt.execute();

            ResultSet rset = (ResultSet) (ocstmt.getObject(1));
            while (rset.next()) {
                list.add(rset.getString(1));
            }
            rset.close();
            ocstmt.close();
        } else {
            throw new Exception("Client inexistant dans la BD !");
        }

        return list;
    }

    /**
     * Methode qui convervit les donnees d'un client en json
     *
     * @return
     */
    public String ClientToJson() {
        String json = new Gson().toJson(this.client);
        return json;
    }

}
