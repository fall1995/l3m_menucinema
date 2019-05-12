package database;

import classesgen.ingredient.Ingredient;
import classesgen.plat.Plat;
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
    
    List<Plat> menu;


    public GestionnaireMenu() {
        // PAR DEFAUT MENU EST VIDE
        this.menu = new ArrayList<Plat>();
    }


    public GestionnaireMenu(List<String> idPlats) throws Exception {
        // RECUPERER TOUS LES PLATS AVEC id DANS idPlats
        List<Plat> laCarte = getCartesDB();
        this.menu = new ArrayList<Plat>();
        int i = 0;
        for(String id : idPlats){
            if (existPlatDB(id)) {
                i = 0;
                while ( !id.equals(laCarte.get(i).getId()) ) {
                    i++;
                }

                menu.add(laCarte.get(i));

            }else{
                throw new Exception("id "+ id + " n'est pas dans la carte des Plats");
            }
        }
    }
    


    protected boolean existPlatDB(String id) {
        for ( Plat p : getCartesDB() ) {
            if (p.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    public void ajouterAuMenu(String id) throws Exception {
        
        List<Plat> laCarte = getCartesDB();
        int i = 0;
        if (existPlatDB(id)) {
            while (  !id.equals(laCarte.get(i).getId())  ) {
                i++;
            }

            menu.add(laCarte.get(i));

        } else {
            throw new Exception("id " + id + " n'est pas dans la carte des Plats");
        }
    }
    

    /**
     * Methode qui permet enlever un plat du menu
     * @param id
     */
    public void enleverDuMenu(String id) throws Exception {
        int i = 0;
        while (    !id.equals(menu.get(i).getId())     &&     i < menu.size()    ){
            i++;
        }
        if ( i < menu.size() ){
            menu.remove(i);
        }else{
            throw new Exception("Le plat avec id " + id + " n'est pas dans la liste des plats choisis !)");
        }
    }
    
    
    public String platsToJson(){
         String json = new Gson().toJson(getCartesDB());
         return json;
    }

    /**
     * Methode qui permet de recuperer le menu
     *
     * @return menu
     */

    public List<Plat> getMenu() {
        return menu;
    }
    
    public static double getPrixPlat(String idPlat) throws Exception{
        List<Plat> laCarte = getCartesDB();
        boolean trouve = false;
        double prixPlat = 0;
        int i = 0;
        while( !trouve  &&  i < laCarte.size() ){
            if( laCarte.get(i).getId().equals(idPlat) ){
                prixPlat = laCarte.get(i).getPrix(); 
                trouve = true; 
            }
            i++;
        }
        if ( !trouve ){
            throw new Exception("Le plat avec id " + idPlat + " n'est pas dans la carte !");
        }
        
        return prixPlat;
    }

    /**
     * Methode qui permet de recuperer les plats
     *
     * @return res
     */
    public static List<Plat> getCartesDB() {
        List<Plat> res = new ArrayList<Plat>();
        Plat target = new Plat();
        boolean bId = false;
        boolean bImage = false;
        boolean bType = false;
        boolean bPrix = false;
        boolean bIngredient = false;   
        try{
         XMLInputFactory factory = XMLInputFactory.newInstance();
         XMLEventReader eventReader =
         factory.createXMLEventReader(new FileReader("src/main/java/schema/Plats.xml"));

         while(eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
               
            switch(event.getEventType()) {
               
               case XMLStreamConstants.START_ELEMENT:
                  StartElement startElement = event.asStartElement();
                  String qName = startElement.getName().getLocalPart();
                  

               if (qName.equalsIgnoreCase("plat")) {
                  target = new Plat();
               } else if(qName.equalsIgnoreCase("id")){
                   bId = true;
               } else if(qName.equalsIgnoreCase("image")){
                   bImage = true;
               } else if(qName.equalsIgnoreCase("type")){
                   bType = true;
               } else if(qName.equalsIgnoreCase("prix")){
                   bPrix = true;
               } else if(qName.equalsIgnoreCase("ingredients")){
                   bIngredient = true;
               }
               break;
               
                case XMLStreamConstants.CHARACTERS:
                  Characters characters = event.asCharacters();
               if(bId) {
                   target.setId(characters.getData());
                  bId = false;
               }
               if(bImage) {
                   target.setImage(characters.getData());
                  bImage = false;
               }
               if(bType) {
                   target.setType(TypeDePlat.fromValue(characters.getData()));
                  bType = false;
               }
               if(bPrix) {
                   target.setPrix(Double.parseDouble(characters.getData()));
                  bPrix = false;
               }
               if(bIngredient) {
                   target.getIngredients().add(Ingredient.fromValue(characters.getData()));
                  bIngredient = false;
               }
               break;


               case XMLStreamConstants.END_ELEMENT:
                  EndElement endElement = event.asEndElement();
                  
               if(endElement.getName().getLocalPart().equalsIgnoreCase("plat")) {
                  res.add(target);
               }
               break;
            } 
         }
         
         
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }

        return res;
    }

}
