/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;

/**
 *
 * @author Groupe6
 * La clase GestionnaireCommande  pour la gestion des commandes
 */
public class GestionnaireCommande  {
    private Commande commande;

    /**
     *Constructeur qui prend en parametre idclient, liste des IdPlats, liste des
     * idFilms,et adresse de livraisons et les modifie
     * @param idClient
     * @param idPlat
     * @param idFilms
     * @param adresseLivraison
     */
    public GestionnaireCommande(String idClient,ArrayList<String> idPlat,
            ArrayList<String> idFilms,String adresseLivraison) {
        commande.setId(idClient);
        commande.setIdplats(idPlat);
        commande.setIdFilms(idFilms);
        commande.setAdresseLivraison(adresseLivraison);
    }

    /**
     *Methode qui permet d'initialiser le id  et prend en parametre le nouveau
     * id
     * @param id
     */
    public GestionnaireCommande(String id) {
        commande.setId(id);
    }

    /**
     *Methode qui permet d'enregistre une commander
     */
    public void enregistreCommanderDB(){
        
    }

    /**
     *Methode qui renvoye une commande grace à id passe en parametre
     * @param id
     * @return commande
     */
    public Commande getCommande(String id) {
        return commande;
    }
    
    
    
    
}
