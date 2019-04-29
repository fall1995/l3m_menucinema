/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Groupe6
 * la classe XMLAble permet gerer la connection a la base de donnee XML et
 * l'excecution des requete
 */
public abstract class XMLAble {
    
      /**
     *Methode permert de se connecter la base de donnee XML
     */
    public void connectToDataBase() throws FileNotFoundException{
        
        
     
    }

    /**
     *Methode permet de se deconnecter a la base de donner XML
     */
    public void disconnect(){
        
    }

    /**
     *Methode qui permet d'execute une requete passer en parametre
     * @param requete
     * @return res qui contient le resultat du requete excecute
     */
    public String request(String requete){
        String res=null;
        return res;
    }
}
