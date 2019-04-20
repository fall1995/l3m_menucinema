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
     *Methode permert de se connecter la base de donnee oracle
     */
    public void connectToDataBase(){
        
    }

    /**
     *Methode permet de se deconnecter a la base de donner oracle
     */
    public void disconnect(){
        
    }

    /**
     *Methode qui permet d'execute une requete passer en parametre
     * @param requete
     * @return res qui contient le resultat du requete excecute
     */
    public ResultSet request(String requete){
        ResultSet res=null;
        return res;
    }
    
}
