/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;

/**
 *
 * @author Groupe6
 * la classe SQLAble permet gerer la connection a la base oracle et l'excecution des 
 * requete
 */
public abstract class SQLAble {

    /**
     * Methode abstraite permert de se connecter la base de donnee oracle
     */
    public abstract void connectToDataBase();

    /**
     * Methode abstraite permet de se deconnecter a la base de donner oracle
     */
    public abstract void disconnect();

    /**
     * Methode abstraite qui permet d'execute une requete passer en parametre
     */
    public abstract ResultSet request(String requete);
    
}
