/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;

/**
 *
 * @author Groupe6 La classe commande contient toutes les informations sur une
 * commander client
 */
public class Commande {

    private String date;
    private String id;
    private String idClient;
    private ArrayList<String> idplats;
    private ArrayList<String> idFilms;
    private double prix;
    private String adresseLivraison;

    /**
     * Methode qui permet d'envoyer la date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Methode qui permet de modifier la date
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Methode qui permet d'envoyer l'Id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Methode qui permet de modifier l'id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Methode qui permet d'envoyer l'id du client
     *
     * @return idClient
     */
    public String getIdClient() {
        return idClient;
    }

    /**
     * Methode qui permet de modifier l id du client
     *
     * @param idClient
     */
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    /**
     * Methode qui permet d'envoyer l'id des plats
     *
     * @return idplats
     */
    public ArrayList<String> getIdplats() {
        return idplats;
    }

    /**
     * Methode qui permet de modifier la liste des id des plats
     *
     * @param idplats
     */
    public void setIdplats(ArrayList<String> idplats) {
        this.idplats = idplats;
    }

    /**
     * Methode qui permet d'envoyer la liste des id des films
     * @return idFilms
     */
    public ArrayList<String> getIdFilms() {
        return idFilms;
    }

    /**
     * Methode qui permet de modifier le prix
     * @param idFilms
     */
    public void setIdFilms(ArrayList<String> idFilms) {
        this.idFilms = idFilms;
    }

    /**
     * Methode qui permet d'envoyer le prix
     * @return prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Methode qui permet de modifier le prix
     * @param prix
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Methode qui permet d'envoyer l'adresse de livraison
     * @return adresseLivraison
     */
    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    /**
     * Methode qui permet de modifier l'adresse de livraision du commande
     * @param adresseLivraison
     */
    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

}
