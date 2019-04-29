/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoubelleTmp;

/**
 *
 * @author Groupe6
 * La classe client qui contient les infos sur clients
 */
public class Client {
    private String id;
    private String nom;
    private String prenom;
    private String photo;
    private String tel;
    private String adresse;
    private String email;

    /**
     *Methode permet d'envoyer id 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     *Methode permet de modifier id 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *Methode permet d'envoyer Nom 
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     *Methode permet de modifier le Nom 
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *Methode permet d'envoyer le prenom 
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     *Methode permet de modifier prenom 
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *Methode permet d'envoyer la photo 
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     *Methode permet de modifier la photo  
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     *Methode permet d'envoyer le numero tel 
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     *Methode permet de modifier  le num tel 
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     *Methode permet d'envoyer Adresse 
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Methode permet de modifier  l'adresse 
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
     /**
     * Methode permet d'envoyer   email  
     * @return email
     */
    public String getEmail() {
        return email;
    }
     /**
     * Methode permet de modifier  l'email 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
