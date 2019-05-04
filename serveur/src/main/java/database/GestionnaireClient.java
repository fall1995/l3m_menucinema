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

    public GestionnaireClient(String id, String nom, String premon) throws SQLException {
        this.client = new Client();
        client.setId(id);
        client.setNom(nom);
        client.setPrenom(premon);
    }
    
    
    public GestionnaireClient( Client client ){
        this.client = client;
    }

    
    public String getNom() {
        return client.getNom();
    }


    public String getPrenom() {
        return client.getPrenom();
    }


    public String getPhoto() {
        return client.getPhoto();
    }

 
    public String getAdresse() {
        return client.getAdresse();
    }

  
    public void editEmail(String email) {
        client.setEmail(email);
    }

 
    public void editAdresse(String adresse) {
        client.setAdresse(adresse);
    }


    public void editTel(String tel) {
        client.setTelephone(tel);
    }


    public void editPhoto(String photo) {
        client.setPhoto(photo);
    }

 
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
            throw new Exception("il existe, pour cela on ne peut pas le réinsérer !");
        }
        return res;
    }


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
    
        
    public static Client getClient(String id) throws SQLException, Exception {
        GestionnaireClient gc = new GestionnaireClient( id , "" , "" );
        gc.client = new Client();
        gc.client.setId(id);
        
        gc.connectToDatabase();
        OracleCallableStatement ocstmt;
        ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getClient(?) }");
        ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
        ocstmt.setString(2, id);
        ocstmt.execute();

        ResultSet rset = (ResultSet) (ocstmt.getObject(1));

        if (rset != null && rset.next()) {
            gc.client.setNom(rset.getString("nom"));
            gc.client.setPrenom(rset.getString("prenom"));
            gc.client.setAdresse(rset.getString("adresse"));
            gc.client.setEmail(rset.getString("email"));
            gc.client.setPhoto(rset.getString("photo"));
            gc.client.setTelephone(rset.getString("tel"));
            rset.close();
        }else{
            throw new Exception("Client inexistant dans la BD!");
        }
        
        ocstmt.close();
        return gc.client;
    }


    public void editClientDB() throws SQLException, Exception {
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
        }else{
                throw new Exception("Client inexistant !");
        }
    }


    public static String getClientIdDB(String nom, String prenom) throws SQLException {
        GestionnaireClient gc = new GestionnaireClient("","","");
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


    public static boolean deleteClientId(String id) throws SQLException, Exception {
        GestionnaireClient gc = new GestionnaireClient( id , "" , "" );
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
        }else{
            throw new Exception("Client inexistant !");
        }

        return res;
    }


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
        }else{
             throw new Exception("Client inexistant dans la BD !");
        }

        return list;
    }
    
    
    public String ClientToJson(){
        String json = new Gson().toJson(this.client);
        return json;
    }

}
