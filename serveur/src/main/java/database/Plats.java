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
 * la classe Plats permet de modeliser les plats
 */
public class Plats {
    private ArrayList<Plat> plats;

    /**
     *Methode qui permet de recuperer la liste des plat
     * @return
     */
    public ArrayList<Plat> getPlats() {
        return plats;
    }

    /**
     *Methode qui permet de modifier la  du plat
     * @param plats
     */
    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
    }
    
    
    
}
