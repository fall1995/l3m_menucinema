/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import l3m.BD_Connection;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Groupe6 Classe GestionnaireClient qui permet de gerer un client
 */
public class GestionnaireClient extends BD_Connection {

    private Client client;

    /**
     * Constructeur qui prend en parametre en l'id, le nom et prenom et il
     * modifie les id, nom et prenom du client courrant
     * @param id
     * @param nom
     * @param premon
     */
    public GestionnaireClient(String id, String nom, String premon) throws SQLException {
        client.setId(id);
        client.setNom(nom);
        client.setPrenom(premon);

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
     * Methode qui permet d'ajouter un client
     *
     * @return true si oui si non il retourne false
     */
    public boolean enregistreClientDB() {
        connectToDataBase();
        boolean exist = existsClientDB();
        boolean res = false;
        if (!exist) {
            try {
                CallableStatement cstmt;
                cstmt = conn.prepareCall("{ = call enregistrerClient(?,?,?) }");
                cstmt.setString(1, client.getId());
                cstmt.setString(2, client.getNom());
                cstmt.setString(3, client.getPrenom());
                cstmt.execute();
                cstmt.close();
                res = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * Methode qui permet de verifier si un le client existe
     *
     * @return true si oui si non il retourne false
     */
    protected boolean existsClientDB() {
        boolean res = false;
        try {
            OracleCallableStatement ocstmt;
            ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call existsClient(?) }");
            ocstmt.registerOutParameter(1, OracleTypes.NUMBER);
            ocstmt.setString(2, client.getId());
            ocstmt.execute();

            int ret = ocstmt.getInt(1);
            if (ret == 1) {
                res = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Methode qui permet d'optenir l'id du client avec le nom et prenom passé
     * en parametre
     *
     * @param nom
     * @param prenom
     * @return id
     */
    public String getClientIdBD(String nom, String prenom) {
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
     * Methode qui permet de suppimer un client dont id est passe en parametre
     *
     * @param id
     * @return true si oui si non il retourne false
     */
    public boolean deleteClientId(String id) {
        boolean exist = false;
        boolean res = false;
        try {
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Methode qui permet d'optenir la liste des commande
     *
     * @return list
     */
    public List<String> getListeCommandes() {
        boolean exist = existsClientDB();
        List<String> list = new ArrayList<String>();

        if (exist) {
            try {
                OracleCallableStatement ocstmt = (OracleCallableStatement) conn.prepareCall("{ ? = call getListeCommandes(?) }");
                ocstmt.registerOutParameter(1, OracleTypes.CURSOR);
                ocstmt.setString(2, "18");
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
}
