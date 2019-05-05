package database;

import classesgen.ingredient.Ingredient;
import classesgen.plat.Plat;
import classesgen.plats.Plats;
import classesgen.typedeplat.TypeDePlat;
import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * @author Groupe6 
 * la classe GestionnaireMenu permet de modeliser un menu
 */


//TODO: Deplacer tous les methodes dans XMLAble?
public class GestionnaireMenu  extends XMLAble{
    
    private Plats menu;


    public GestionnaireMenu() {
        menu = new Plats();
    }

    
    public GestionnaireMenu( List<String> idPlats ) throws Exception {
        
        menu = new Plats();
        List<Plat> listPlats = menu.getPlats();
        Plats laCarte = getCarteDB();
        Plat plat;
        for ( String idPlat : idPlats ){
            boolean trouve = false;
            int i = 0;
            while ( !trouve && i <  laCarte.getPlats().size() ){
                if ( idPlat.equals( laCarte.getPlats().get(i) ) ){
                    plat = new Plat();
                    plat.setId( laCarte.getPlats().get(i).getId() );
                    plat.setImage( laCarte.getPlats().get(i).getImage() );
                    plat.setType( laCarte.getPlats().get(i).getType() );
                    plat.setPrix( laCarte.getPlats().get(i).getPrix() );
                    for ( Ingredient ingr : laCarte.getPlats().get(i).getIngredients() ){
                        plat.getIngredients().add( ingr );
                    }
                    menu.getPlats().add ( plat );
                    trouve = true;
                }
                i++;
            }
            if ( !trouve ){
                throw new Exception("le plat avec id " + idPlat + " n'est pas dans la carte !");
            }
        }
    }


    protected boolean existPlatDB(String id) {
        boolean trouve = false;
        int i = 0;
        while (!trouve && i < getCarteDB().getPlats().size()) {
            if ( getCarteDB().getPlats().get(i).getId().equals(id)) {
                trouve = true;
            }
            i++;
        }
        
        return trouve;
    }


    public void ajouterAuMenu(String id) {
        //Ajouter un plat à la liste this.menu.plats
        // ce n'est pas compliquer
    }


    public void enleverDuMenu(String id) {
        //enlever un plat de la liste this.menu.plats
        // ce n'est pas compliquer
    }


    public Plats getMenu() {
        return menu;
    }
    
    
    public static double getPrixPlat(String idPlat) throws Exception{
        boolean trouve = false;
        double prix = 0;
        int i = 0;
        
        while( !trouve  &&  i < getCarteDB().getPlats().size() ){
            if( idPlat.equals( getCarteDB().getPlats().get(i).getId() ) ){
                prix = getCarteDB().getPlats().get(i).getPrix();
                trouve = true;
            }
            i++;
        }
        
        if ( !trouve ){
            throw new Exception( "le plat avec id " + idPlat + " n'est pas dans la carte !");
        }
        
        return prix;
    }

    
    /**
     * Methode qui permet de récuperer la carte de tous les plats
     * @return res
     */
    public static Plats getCarteDB() {
        
        Plats laCarte = new Plats();
        Plat target = new Plat();

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
                            target.setType(TypeDePlat.fromValue(characters.getData()));
                            bType = false;
                        }
                        if (bPrix) {
                            target.setPrix(Double.parseDouble(characters.getData()));
                            bPrix = false;
                        }
                        if (bIngredient) {
                            target.getIngredients().add(Ingredient.fromValue(characters.getData()));
                            bIngredient = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equalsIgnoreCase("plat")) {
                            laCarte.getPlats().add(target);
                        }
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return laCarte;
    }
    
    
    public String menuToJson(){
         String json = new Gson().toJson( this.menu );
         return json;
    }

}
