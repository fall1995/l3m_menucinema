/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;

/**
 *
 * @author Groupe6 Classe GestionnaireClient qui permet de gerer un client
 */
public class GestionnaireClient extends SQLAble{

    private Client client;

    /**
     * Constructeur qui prend en parametre en l'id, le nom et prenom et il
     * modifie les id, nom et prenom du client courrant
     * @param id
     * @param nom
     * @param premon
     */
    public GestionnaireClient(String id, String nom, String premon) {
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
        boolean res = false;

        return res;
    }

    /**
     * Methode qui permet de verifier si un le client existe
     *
     * @return true si oui si non il retourne false
     */
    protected boolean existsClientDB() {
        boolean res = false;

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
        String resId = " ";

        return resId;
    }

    /**
     * Methode qui permet de suppimer un client dont id est passe en parametre
     *
     * @param id
     * @return true si oui si non il retourne false
     */
    public boolean deleteClientId(String id) {
        boolean res = false;

        return res;
    }

    /**
     * Methode qui permet d'optenir la liste des commande
     *
     * @return list
     */
    public ArrayList<String> getListeCommandes() {
        ArrayList<String> list;
        //list = new ArrayList<String>;
        list = null;

        return list;
    }
}
