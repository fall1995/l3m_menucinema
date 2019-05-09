package facture;

import classesgen.commande.Commande;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.namespace.QName;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class GestionnaireFactures {

    public GestionnaireFactures() {

    }

    public String suggestionFilmToJson(String idPlat) {
        String json = new Gson().toJson((PreferencePourPlat(idPlat)));
        return json;
    }

    public String suggestionPlatToJson(String idFilm) {
        String json = new Gson().toJson(platsPreferencePourFilm(idFilm));
        return json;
    }

    // Permet de trouver tous les commandes qui verifie au moins une critere
    public List<Commande> commandesAvecCriteres(Map<String, String> criteres, String idClient) {
        List<Commande> targetCommandes = new ArrayList<Commande>();
        //Trouver tous les factures correspondants
        List<String> targetFactures = new ArrayList<String>();
        for (String f : nomsFactures()) {
            if (f.contains(idClient)) {
                targetFactures.add(f);
            }
        };

        int targetCritere = 0;
        for (String filepath : targetFactures) {

            List<String> plats = new ArrayList<String>();
            List<String> films = new ArrayList<String>();
            String prix = "";
            String id = "";
            String date = "";
            String adresseLivraison = "";
            String idClientStr = "";

            boolean matchingCommand = false;
            boolean bCommande = false;
            boolean bIdPlat = false;
            boolean bIdClient = false;
            boolean bFilm = false;
            try {
                XMLInputFactory factory = XMLInputFactory.newInstance();
                XMLEventReader eventReader
                        = factory.createXMLEventReader(new FileReader("factures/" + filepath));

                while (eventReader.hasNext()) {
                    XMLEvent event = eventReader.nextEvent();

                    for (Map.Entry<String, String> o : criteres.entrySet()) {
                        switch (event.getEventType()) {

                            case XMLStreamConstants.START_ELEMENT:
                                StartElement startElement = event.asStartElement();
                                String qName = startElement.getName().getLocalPart();

                                if (qName.equalsIgnoreCase("facture")) {

                                    id = startElement.getAttributeByName(QName.valueOf("id")).getValue();
                                    prix = startElement.getAttributeByName(QName.valueOf("prix")).getValue();
                                    adresseLivraison = startElement.getAttributeByName(QName.valueOf("adresseLivraison")).getValue();
                                    date = startElement.getAttributeByName(QName.valueOf("date")).getValue();
                                    bCommande = true;
                                } else if (qName.equalsIgnoreCase("idClient")) {

                                    bIdClient = true;
                                } else if (qName.equalsIgnoreCase("idPlat")) {
                                    bIdPlat = true;
                                } else if (qName.equalsIgnoreCase("film")) {
                                    bFilm = true;
                                }

                                break;

                            case XMLStreamConstants.CHARACTERS:
                                Characters characters = event.asCharacters();

                                if (bIdPlat) {
                                    plats.add(characters.getData());
                                    bIdPlat = false;
                                } else if (bFilm) {
                                    films.add(characters.getData());
                                    bFilm = false;
                                } else if (bIdClient) {
                                    idClientStr = characters.getData();
                                    bIdClient = false;
                                }

                                break;

                            case XMLStreamConstants.END_ELEMENT:
                                EndElement endElement = event.asEndElement();

                                if (endElement.getName().getLocalPart().equalsIgnoreCase("facture")) {

                                    Commande commande = new Commande();
                                    commande.setAdresseLivraison(adresseLivraison);
                                    commande.setDate(date);
                                    commande.setId(id);
                                    commande.setPrix(Double.parseDouble(prix));
                                    commande.setIdClient(idClientStr);
                                    commande.getIdFilms().addAll(films);
                                    commande.getIdPlats().addAll(plats);
                                    targetCommandes.add(commande);
                                }
                                break;
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        return targetCommandes;
    }
    
    
    /************RECUPERER FACTURE**************/
    public String recupererFacture(String idClient, String id){
        
        

            List<String> plats = new ArrayList<String>();
            List<String> films = new ArrayList<String>();
            String prix = "";
            String adresseLivraison = "";
            String date = "";

            boolean matchingCommand = false;
            boolean bCommande = false;
            boolean bIdPlat = false;
            boolean bIdClient = false;
            boolean bFilm = false;
                                    Commande commande = new Commande();
                                    
                                            List<String> targetFactures = new ArrayList<String>();
        for (String f : nomsFactures()) {
            if (f.contains(idClient) && f.contains("-"+id)) {
                targetFactures.add(f);
            }
        };
        
        try {
                XMLInputFactory factory = XMLInputFactory.newInstance();
                XMLEventReader eventReader
                        = factory.createXMLEventReader(new FileReader("factures/"+targetFactures.get(0)));

                while (eventReader.hasNext()) {
                    XMLEvent event = eventReader.nextEvent();

                        switch (event.getEventType()) {

                            case XMLStreamConstants.START_ELEMENT:
                                StartElement startElement = event.asStartElement();
                                String qName = startElement.getName().getLocalPart();

                                if (qName.equalsIgnoreCase("facture")) {

                                    prix = startElement.getAttributeByName(QName.valueOf("prix")).getValue();
                                    adresseLivraison = startElement.getAttributeByName(QName.valueOf("adresseLivraison")).getValue();
                                    date = startElement.getAttributeByName(QName.valueOf("date")).getValue();
                                    
                                    bCommande = true;
                                } else if (qName.equalsIgnoreCase("idClient")) {

                                    bIdClient = true;
                                } else if (qName.equalsIgnoreCase("idPlat")) {
                                    bIdPlat = true;
                                } else if (qName.equalsIgnoreCase("film")) {
                                    bFilm = true;
                                }

                                break;

                            case XMLStreamConstants.CHARACTERS:
                                Characters characters = event.asCharacters();

                                if (bIdPlat) {
                                    plats.add(characters.getData());
                                    bIdPlat = false;
                                } else if (bFilm) {
                                    films.add(characters.getData());
                                    bFilm = false;
                                } else if (bIdClient) {
                                    bIdClient = false;
                                }

                                break;

                            case XMLStreamConstants.END_ELEMENT:
                                EndElement endElement = event.asEndElement();

                                if (endElement.getName().getLocalPart().equalsIgnoreCase("facture")) {

                                    commande.setAdresseLivraison(adresseLivraison);
                                    commande.setDate(date);
                                    commande.setId(id);
                                    commande.setPrix(Double.parseDouble(prix));
                                    commande.setIdClient(idClient);
                                    commande.getIdFilms().addAll(films);
                                    commande.getIdPlats().addAll(plats);
                                }
                                break;
                        }
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        return new Gson().toJson(commande);
    }

    /**
     * ***************** FILMS POUR PLAT
     *
     * @param plat
     * @return *******************
     */
    // Afficher couples (film, preference) pour plat
    public Map<String, Integer> PreferencePourPlat(String plat) {
        Map<String, Integer> filmsPourPlat = new HashMap<String, Integer>();

        for (String film : filmsAvecPlat(plat)) {
            boolean added = false;

            for (Map.Entry<String, Integer> op : filmsPourPlat.entrySet()) {
                String nomFilm = (String) op.getKey();
                if (nomFilm.equals(film)) {
                    added = true;
                    op.setValue((Integer) op.getValue() + 1);
                }
            }
            if (!added) {
                filmsPourPlat.put(film, 1);
            }
        }

        // Affichage

        ValueComparator bvc = new ValueComparator(filmsPourPlat);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        TreeMap<String, Integer> map5 = new TreeMap<String, Integer>(bvc);

        // Affichage
        sorted_map.putAll(filmsPourPlat);
        for (Map.Entry<String, Integer> op : sorted_map.entrySet()) {
        }

        if (sorted_map.entrySet().size() > 5) {
            for (int i = 0; i < 5; i++) {
                map5.put(sorted_map.firstEntry().getKey(), sorted_map.firstEntry().getValue());
                sorted_map.remove(sorted_map.firstKey());
            }

            return map5;
        } else {
            return sorted_map;
        }
    }

    // Afficher tous les films achetes avec plat
    public List<String> filmsAvecPlat(String plat) {

        List<String> factures = nomsFactures();
        List<String> films = new ArrayList<String>();

        for (String facture : factures) {
            List<String> filmsFacture = getFilmsFromFacture(plat, facture);
            for (String film : filmsFacture) {
                films.add(film);
            }

        }

        return films;
    }

    // Trouver tous les films de facture
    public List<String> getFilmsFromFacture(String plat, String filepath) {

        List<String> res = new ArrayList<String>();
        List<String> films = new ArrayList<String>();

        boolean bCommande = false;
        boolean bIdPlat = false;
        boolean bClient = false;
        boolean bFilm = false;
        boolean targetAudience = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader
                    = factory.createXMLEventReader(new FileReader("factures/" + filepath));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("facture")) {
                        } else if (qName.equalsIgnoreCase("client")) {
                            bClient = true;
                        } else if (qName.equalsIgnoreCase("idPlat")) {
                            bIdPlat = true;
                        } else if (qName.equalsIgnoreCase("film")) {
                            bFilm = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (bIdPlat) {
                            if (plat.equals(characters.getData())) {
                                targetAudience = true;
                            }
                            bIdPlat = false;
                        } else if (bFilm) {
                            films.add(characters.getData());
                            bFilm = false;
                        } else if (bClient) {
                            bClient = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("facture")) {
                            if (targetAudience) {
                                for (String p : films) {
                                    res.add(p);
                                    System.out.println(p);
                                }
                            }
                        }
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return res;
    }

    /**
     * ***************** PL
     *
     * @param film
     * @return ********************
     */
    // Afficher couples (plats, preference) pour film
    public Map<String, Integer> platsPreferencePourFilm(String film) {
        Map<String, Integer> platsPourFilm = new HashMap<String, Integer>();

        for (String plat : platsAvecFilm(film)) {
            boolean added = false;

            for (Map.Entry<String, Integer> op : platsPourFilm.entrySet()) {
                String nomPlat = (String) op.getKey();
                if (nomPlat.equals(plat)) {
                    added = true;
                    op.setValue((Integer) op.getValue() + 1);
                }
            }
            if (!added) {
                platsPourFilm.put(plat, 1);
            }
        }

        // Affichage
        for (Map.Entry<String, Integer> op : platsPourFilm.entrySet()) {
            System.out.println(op.getKey() + " " + op.getValue());
        }

        System.out.println(platsPourFilm.entrySet().stream().sorted(Map.Entry.comparingByValue()));

        ValueComparator bvc = new ValueComparator(platsPourFilm);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        TreeMap<String, Integer> map5 = new TreeMap<String, Integer>(bvc);

        // Affichage
        sorted_map.putAll(platsPourFilm);

        if (sorted_map.entrySet().size() > 5) {
            for (int i = 0; i < 5; i++) {
                map5.put(sorted_map.firstEntry().getKey(), sorted_map.firstEntry().getValue());
                sorted_map.remove(sorted_map.firstKey());
            }

            return map5;
        } else {
            return sorted_map;
        }

    }

    // Afficher tous les plats achetes avec film
    public List<String> platsAvecFilm(String film) {

        List<String> factures = nomsFactures();
        List<String> plats = new ArrayList<String>();

        for (String facture : factures) {
            List<String> platsFacture = getPlatsFromFacture(film, facture);
            for (String plat : platsFacture) {
                plats.add(plat);
            }

        }

        return plats;
    }

    // Trouver tous les plats de facture
    public List<String> getPlatsFromFacture(String film, String filepath) {

        List<String> res = new ArrayList<String>();
        List<String> plats = new ArrayList<String>();

        boolean bCommande = false;
        boolean bIdPlat = false;
        boolean bClient = false;
        boolean bFilm = false;
        boolean targetAudience = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader
                    = factory.createXMLEventReader(new FileReader("factures/" + filepath));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("facture")) {
                        } else if (qName.equalsIgnoreCase("client")) {
                            bClient = true;
                        } else if (qName.equalsIgnoreCase("idPlat")) {
                            bIdPlat = true;
                        } else if (qName.equalsIgnoreCase("film")) {
                            bFilm = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (bIdPlat) {
                            plats.add(characters.getData());
                            bIdPlat = false;
                        } else if (bFilm) {
                            if (film.equals(characters.getData())) {
                                targetAudience = true;
                                bFilm = false;
                            }
                        } else if (bClient) {
                            bClient = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("facture")) {
                            if (targetAudience) {
                                for (String p : plats) {
                                    res.add(p);
                                }
                            }
                        }
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return res;
    }

    // Trouver tous les factures
    public List<String> nomsFactures() {
        File folder = new File("factures/");
        File[] listOfFiles = folder.listFiles();
        List<String> noms = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                noms.add(listOfFiles[i].getName());
            }
        }
        return noms;
    }
}

class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
