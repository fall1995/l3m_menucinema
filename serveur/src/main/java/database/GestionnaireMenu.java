/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;

/**
 *
 * @author Groupe6 la classe GestionnaireMenu permet de modeliser un menu
 */
public class GestionnaireMenu  extends XMLAble{

    private Plats menu;

    /**
     * Constructeur par defaut
     */
    public GestionnaireMenu() {
    }

    /**
     * Constructeur qui initialise les id plats
     *
     * @param idPlats
     */
    public GestionnaireMenu(ArrayList<String> idPlats) {

    }

    /**
     *Methode qui permet de verifier si plat existe dans la base en prenant en
     * id du plat
     * @param id
     * @return res
     */
    protected boolean existPlatDB(String id) {
        boolean res = false;
        return res;
    }

    /**
     *Methode qui permet d'ajouter un menu en prenant en parametre id du menu
     * @param id
     */
    public void ajouterAuMenu(String id) {

    }

    /**
     *Methode qui permet enlever un menu en prenant en paramtre id du menu
     * @param id
     */
    public void enleverDuMenu(String id) {

    }

    /**
     *Methode qui permet de recuperer le menu
     * @return menu
     */
    public Plats getMenu() {
        return menu;
    }

    /**
     *Methode qui permet de recuperer les plats
     * @return res
     */
    public Plats getCartesDB() {
        Plats res = null;
        return res;
    }

}
