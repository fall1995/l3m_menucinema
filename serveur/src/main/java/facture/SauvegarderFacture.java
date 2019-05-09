package facture;


import classesgen.client.Client;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SauvegarderFacture {

	public static void saveFacture(String addresseLivraison, String date, String id, double prix, String idClient, String[] films, String[] plats) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root element
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("facture");
		doc.appendChild(rootElement);
                
	// attributs de facture
		Attr attr = doc.createAttribute("adresseLivraison");
		attr.setValue(addresseLivraison);
		rootElement.setAttributeNode(attr);
                
		Attr attr2 = doc.createAttribute("date");
		attr2.setValue(date);
		rootElement.setAttributeNode(attr2);
                
		Attr attr3 = doc.createAttribute("id");
		attr3.setValue(id);
		rootElement.setAttributeNode(attr3);
                
		Attr attr4 = doc.createAttribute("prix");
		attr4.setValue(""+prix);
		rootElement.setAttributeNode(attr4);
                

		// Client element
		Element clientE = doc.createElement("idClient");
                clientE.appendChild(doc.createTextNode(idClient));
		rootElement.appendChild(clientE);
                
               /* Element clientId = doc.createElement("id");
                clientId.setNodeValue(client.getId());
                Element clientNom = doc.createElement("nom");
                clientNom.setNodeValue(client.getNom());
                Element clientPrenom = doc.createElement("prenom");
                clientPrenom.setNodeValue(client.getPrenom());
                Element clientTel = doc.createElement("telephone");
                clientTel.setNodeValue(client.getTel());
                Element clientAdr = doc.createElement("adresse");
                clientAdr.setNodeValue(client.getAdresse());
                Element clientPhoto = doc.createElement("photo");
                clientPhoto.setNodeValue(client.getPhoto());
                
                
		clientE.appendChild(clientId);
		clientE.appendChild(clientNom);
		clientE.appendChild(clientPrenom);
		clientE.appendChild(clientTel);
		clientE.appendChild(clientAdr);
		clientE.appendChild(clientPhoto);*/
                
                // Plats
                for (String plat : plats ){
                    Element platE = doc.createElement("idPlat");
                    platE.appendChild(doc.createTextNode(plat));
                    rootElement.appendChild(platE);
                }
                // Films
                for (String film : films ){
                    Element filmE = doc.createElement("film");
                    filmE.appendChild(doc.createTextNode(film));
                    rootElement.appendChild(filmE);
                }
                


		

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("factures/"+idClient+"-"+date+"-"+id+".xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}