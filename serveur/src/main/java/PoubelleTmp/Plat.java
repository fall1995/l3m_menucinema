/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoubelleTmp;

import PoubelleTmp.Ingredient;
import database.TypeDePlat;
import java.util.ArrayList;

/**
 * @author Groupe6
 * la classe Plat permet de modeliser un plat
 */
public class Plat {

    private String id;
    private TypeDePlat type;
    private double prix;
    private ArrayList<Ingredient> ingredients;

    /**
     * Methode qui permet de recuperer Id du plat
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Methode qui permet de modifier id du plat 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Methode qui permet de recuperer le typeDeplat 
     * @return type
     */
    public TypeDePlat getType() {
        return type;
    }

    /**
     * Methode qui permet de modifier le typeDePlat
     * @param type
     */
    public void setType(TypeDePlat type) {
        this.type = type;
    }

    /**
     * Methode qui permet de recuperer le prix du plat
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
     * Methode qui permet de recuperer les ingredients
     * @return ingredients
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Methode qui permet de modifier la liste des ingredients
     * @param ingredients
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
