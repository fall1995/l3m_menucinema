package database;

import classesgen.ingredient.Ingredient;
import classesgen.plat.Plat;
import classesgen.typedeplat.TypeDePlat;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * @author Groupe6 
 * la classe GestionnaireMenu permet de modeliser un menu
 */
public class GestionnaireMenu extends XMLAble {

    List<Plat> menu;

    /**
     * Constructeur par defaut
     */
    public GestionnaireMenu() {
        // PAR DEFAUT MENU EST VIDE
        this.menu = new ArrayList<Plat>();
    }

    /**
     * Constructeur qui initialise les id plats
     * @param idPlats
     */
    public GestionnaireMenu(List<String> idPlats) {
        // RECUPERER TOUS LES PLATS AVEC id DANS idPlats

    }

    /**
     * Methode qui permet de verifier si plat existe dans la base en prenant en
     * id du plat
     * @param id
     * @return res
     */
    protected boolean existPlatDB(String id) {
        for (Plat p : getCartesDB()) {
            if (p.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode qui permet d'ajouter un menu en prenant en parametre id du menu
     * @param id
     */
    public void ajouterAuMenu(String id) {

    }

    /**
     * Methode qui permet enlever un menu en prenant en paramtre id du menu
     *
     * @param id
     */
    public void enleverDuMenu(String id) {

    }

    /**
     * Methode qui permet de recuperer le menu
     *
     * @return menu
     */
    public ArrayList<Plat> getMenu() {
        return (ArrayList<Plat>) menu;
    }

    /**
     * Methode qui permet de recuperer les plats
     *
     * @return res
     */
    public List<Plat> getCartesDB() {
        List<Plat> res = new ArrayList<Plat>();
        Plat target = new Plat();
        List<Ingredient> ingre = new ArrayList<Ingredient>();
        boolean bId = false;
        boolean bImage = false;
        boolean bType = false;
        boolean bPrix = false;
        boolean bIngredient = false;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader
                    = factory.createXMLEventReader(new FileReader("src/main/java/schema/Plats.xml"));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("plat")) {
                            System.out.println("Start Element : plat");
                            target = new Plat();
                        } else if (qName.equalsIgnoreCase("id")) {
                            bId = true;
                        } else if (qName.equalsIgnoreCase("image")) {
                            bImage = true;
                        } else if (qName.equalsIgnoreCase("type")) {
                            bType = true;
                        } else if (qName.equalsIgnoreCase("prix")) {
                            bPrix = true;
                        } else if (qName.equalsIgnoreCase("ingredients")) {
                            bIngredient = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (bId) {
                            target.setId(characters.getData());
                            bId = false;
                        }
                        if (bImage) {
                            target.setImage(characters.getData());
                            bImage = false;
                        }
                        if (bType) {
                            target.setType(TypeDePlat.valueOf(characters.getData()));
                            bType = false;
                        }
                        if (bPrix) {
                            target.setPrix(Double.parseDouble(characters.getData()));
                            bPrix = false;
                        }
                        if (bIngredient) {
                            ingre.add(Ingredient.valueOf(characters.getData()));
                            bIngredient = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equalsIgnoreCase("plat")) {
                            System.out.println("End Element : plat");
                            System.out.println();
                            res.add(target);
                        } else if (endElement.getName().getLocalPart().equalsIgnoreCase("ingredients")) {
                            target.getIngredients().clear();
                            target.getIngredients().addAll(ingre);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | NumberFormatException | XMLStreamException e) {
            System.out.println("Exception: " + e);
        }

        for (Plat p : res) {
            System.out.print(p.getId());
        }
        return res;
    }

}
