/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facture;

import classesgen.client.Client;
import classesgen.commande.Commande;
import classesgen.plat.Plat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.namespace.QName;


public class TransformationFacture {
    Client client;
    List<String> idPlats;
    List<String> films;
    String addresse;
    String date;
    double totale;
    String id;
    

public TransformationFacture(Client client, List<String> idPlats, List<String> films,
        String addresse, String date, double totale, String id){
    this.client = client;
    this.idPlats = idPlats;
    this.films = films;
    this.addresse = addresse;
    this.date = date;
    this.totale = totale;
    this.id = id;
    
    
}

public void saveAsXml(String nomFichier) throws JAXBException, IOException{

        
    /*
        Commande cmd = new Commande();
        cmd.setClient(client);
        cmd.setPrix(totale);
        cmd.setAdresseLivraison(addresse);
        cmd.setDate(date);
        cmd.getFilm().addAll(films);
        cmd.getIdPlat().addAll(idPlats);
        cmd.setId(id);
        
        StringWriter stringWriter = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Commande.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
            true);

        QName qName = new QName("facture", "facutre");
        JAXBElement<Commande> root = new JAXBElement<>(qName, Commande.class, cmd);

        jaxbMarshaller.marshal(root, stringWriter);

        String result = stringWriter.toString();
        System.out.print(result);
        
          try {
            File newTextFile = new File("factures/"+nomFichier);

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(result);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
*/
}

}
