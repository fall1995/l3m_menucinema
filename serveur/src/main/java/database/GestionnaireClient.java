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
 * @author Groupe6 
 * Classe GestionnaireClient qui permet de gerer un client
 */
public class GestionnaireClient extends SQLAble {

    private Client client;

    /**
     * Constructeur qui prend en parametre en l'id, le nom et prenom et il
     * modifie les id, nom et prenom du client courran
     *
     * @param id
     * @param nom
     * @param premon
     * @throws java.sql.SQLException
     */
    public GestionnaireClient(String id, String nom, String premon) throws SQLException {
        this.client = new Client();
        client.setId(id);
        client.setNom(nom);
        client.setPrenom(premon);
    }

    public GestionnaireClient(String id) throws SQLException {
        this.client = new Client();
        client.setId(id);
    }

    /**
     * Methode qui permet de re-envoyer le nom
     *
     * @return nom
     */
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
        return client.getPrenom();
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
        client.setTelephone(email);
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
        client.setTelephone(tel);
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
     * Methode qui permet d'ajouter un client
     *
     * @return true si oui si non il retourne false
     * @throws java.sql.SQLException
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
        }else{
            throw new Exception("il existe pour cela, on ne peut pas le rajouter !");
        }
        return res;
    }

    /**
     * Methode qui permet de verifier si un le client existe
     *
     * @return true si oui si non il retourne false
     * @throws java.sql.SQLException
     */
        public boolean existsClientDB() throws SQLException {
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
        
    public Client getClient(String id) throws SQLException {
        Client client = new Client();

        connectToDatabase();
        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getClient(?) }");
        ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
        ocstmt.setString(2, id);
        ocstmt.execute();

        ResultSet rset = (ResultSet) (ocstmt.getObject(1));

        if (rset != null && rset.next()) {
            //client.setId(rset.getString("idClient"));
            client.setNom(rset.getString("nom"));
            client.setPrenom(rset.getString("prenom"));
            client.setAdresse(rset.getString("adresse"));
            client.setEmail(rset.getString("email"));
            client.setPhoto(rset.getString("photo"));
            client.setTelephone(rset.getString("tel"));
        }
        ocstmt.close();

        return client;
    }

    /**
     * Methode permet de mettre à jour les info sur un client encours s'il 
     * existe, sinon elle fait rien Cette méthode catch la SQLException si cette
     * dernière est lévée par la procédure PL SQL editClient
     * @throws java.sql.SQLException
     */
    public void editClientDB() throws SQLException {
        boolean exist = existsClientDB();
        if (exist) {
            connectToDatabase();
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ = call editClient(?,?,?,?,?) }");
            cstmt.setString(1, client.getId());
            cstmt.setString(2, client.getPhoto());
            cstmt.setString(3, client.getEmail());
            cstmt.setString(4, client.getTelephone());
            cstmt.setString(5, client.getAdresse());
            cstmt.execute();
            cstmt.close();
        }
    }

    /**
     * Methode qui permet d'optenir l'id du client avec le nom et prenom passé
     *
     * @param nom
     * @param prenom
     * @return id
     */
    public static String getClientIdDB(String nom, String prenom) throws SQLException {
        GestionnaireClient gc = new GestionnaireClient("0");
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
     * Methode qui permet de suppimer un client dont id est passe en parametre
     *
     * @param id
     * @return true si oui si non il retourne false
     */
    public static boolean deleteClientId(String id) throws SQLException, Exception {
        GestionnaireClient gc = new GestionnaireClient("0");
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
            gc.connectToDatabase();
            CallableStatement cstmt = conn.prepareCall("{ = call deleteClient(?) }");
            cstmt.setString(1, id);
            cstmt.execute();
            res = true;
            cstmt.close();
        }else{
            throw new Exception("Client inexistant !");
        }

        return res;
    }

    /**
     * Methode qui permet d'optenir la liste des commande
     *
     * @return list
     * @throws java.sql.SQLException
     */
    public List<String> getListeCommandes() throws SQLException {
        boolean exist = existsClientDB();
        List<String> list = new ArrayList<String>();

        if (exist) {
            try {
                connectToDatabase();
                OracleCallableStatement ocstmt;
                ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getListeCommandes(?) }");
                ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
                ocstmt.setString(2, client.getId() );
                ocstmt.execute();

                ResultSet rset = (ResultSet) (ocstmt.getObject(1));
                while (rset.next()) {
                    list.add(rset.getString(1));
                }
                rset.close();
                ocstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
    
    
    public String ClientToJson(){
        String json = new Gson().toJson(this.client);
        return json;
    }
}
