/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testClass;

import classesgen.client.Client;
import classesgen.commande.Commande;
import classesgen.plat.Plat;
import com.google.gson.Gson;
import database.GestionnaireMenu;
import database.SQLAble;
import database.XMLAble;
import facture.GestionnaireFactures;
import facture.SauvegarderFacture;
import facture.TransformationFacture;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;

/**
 *
 * @author kadidiatou
 */
public class Test extends XMLAble {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException, IOException {
        
        /*GestionnaireMenu m = new GestionnaireMenu();
        List<Plat> p = m.getCartesDB();
        
        
        
        System.out.println( "================" );
        System.out.println(  p );
        System.out.println( "================" );
        System.out.println( new Gson().toJson( p) );
        
        
        
        SauvegarderFacture.saveFacture("adresse", "date", "id", 0, "idClient", new String[]{"foo", "bar"}, new String[]{"foo2", "bar2"});*/
        
        
        
        // TEST CRITERES
        /*
        GestionnaireFactures gf = new GestionnaireFactures();
        Map<String, String> op = new HashMap<String, String>();
        op.put("film", "e");
        for(Commande c : gf.commandesAvecCriteres(op, "test.x")){
            System.out.println(c.getId());
        };*/
        
        GestionnaireFactures gf = new GestionnaireFactures();
        System.out.println(gf.recupererFacture("idClient", "id"));
        

     /*
        GestionnaireMenu m = new GestionnaireMenu();
      m.getCartesDB();
      System.out.println("=============");
      double d =m.getPrixPlat("des2");
      System.out.println("prix: " + d);
        
       
         Client c = new Client();
         c.setAdresse("A");
         c.setId("C");
         c.setNom("D");
         c.setPhoto("E");
         c.setPrenom("F");
         c.setTelephone("G");
         TransformationFacture tf = new TransformationFacture(c, new ArrayList<>(Arrays.asList(
    "des1",
    "de3",
    "plat2"
)), new ArrayList<>(Arrays.asList(
    "Unbreakable",
    "Die Hard",
    "Sixth Sense"
)),
        "Mon Maison", "12:41 31.10.19", 41000, "xk7to1");
         
         tf.saveAsXml("testFacture.xml");
      // System.out.print(conn);
        // TODO code application logic here*/
    }
    
}
