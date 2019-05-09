package facture;

import classesgen.commande.Commande;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
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
        System.out.print("json " + json);
        return json;
    }

    public String suggestionPlatToJson(String idFilm) {
        String json = new Gson().toJson(platsPreferencePourFilm(idFilm));
        return json;
    }

    // Permet de trouver tous les commandes qui verifie au moins une critere
    public List<Commande> commandesAvecCriteres(List<ObjectPreference> criteres, String idClient) {
        List<Commande> targetCommandes = new ArrayList<Commande>();
        //Trouver tous les factures correspondants
        List<String> targetFactures = new ArrayList<String>();
        for (String f : nomsFactures()) {
            if (f.contains(idClient)) {
                targetFactures.add(f);
                System.out.println(f);
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

                    for (ObjectPreference o : criteres) {
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

                                if (qName.equalsIgnoreCase((String) o.getL())) {
                                    targetCritere = criteres.indexOf(o);
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

                                if (targetCritere == criteres.indexOf(o)) {
                                    matchingCommand = true;
                                }
                                break;

                            case XMLStreamConstants.END_ELEMENT:
                                EndElement endElement = event.asEndElement();

                                if (endElement.getName().getLocalPart().equalsIgnoreCase("facture")) {

                                    if (matchingCommand) {
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

    /**
     * ***************** FILMS POUR PLAT
     *
     * @param plat
     * @return *******************
     */
    // Afficher couples (film, preference) pour plat
    public List<ObjectPreference> PreferencePourPlat(String plat) {
        List<ObjectPreference> filmsPourPlat = new ArrayList<ObjectPreference>();

        for (String film : filmsAvecPlat(plat)) {
            boolean added = false;

            for (ObjectPreference op : filmsPourPlat) {
                String nomFilm = (String) op.getL();
                if (nomFilm.equals(film)) {
                    added = true;
                    op.setR((Integer) op.getR() + 1);
                }
            }
            if (!added) {
                filmsPourPlat.add(new ObjectPreference(film, 1));
            }
        }

        // Affichage
        for (ObjectPreference op : filmsPourPlat) {
            System.out.println(op.getL() + " " + op.getR());
        }

        return filmsPourPlat;
    }

    // Afficher tous les films achetes avec plat
    public List<String> filmsAvecPlat(String plat) {

        List<String> factures = nomsFactures();
        List<String> films = new ArrayList<String>();

        for (String facture : factures) {
            List<String> filmsFacture = getFilmsFromFacture(plat, facture);
            for (String film : filmsFacture) {
                films.add(film);
                System.out.print(film);
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
                            System.out.println("plat" + characters.getData());
                            System.out.println(plat);
                            if (plat.equals(characters.getData())) {
                                targetAudience = true;
                            }
                            bIdPlat = false;
                        } else if (bFilm) {
                            System.out.println(characters.getData());
                            films.add(characters.getData());
                            bFilm = false;
                        } else if (bClient) {
                            bClient = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("facture")) {
                            System.out.println("targetAudience " + targetAudience);
                            if (targetAudience) {
                                System.out.println("--------");
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
    public List<ObjectPreference> platsPreferencePourFilm(String film) {
        List<ObjectPreference> platsPourFilm = new ArrayList<ObjectPreference>();

        for (String plat : platsAvecFilm(film)) {
            boolean added = false;

            for (ObjectPreference op : platsPourFilm) {
                String nomPlat = (String) op.getL();
                if (nomPlat.equals(plat)) {
                    added = true;
                    op.setR((Integer) op.getR() + 1);
                }
            }
            if (!added) {
                platsPourFilm.add(new ObjectPreference(plat, 1));
            }
        }

        // Affichage
        for (ObjectPreference op : platsPourFilm) {
            System.out.println(op.getL() + " " + op.getR());
        }

        return platsPourFilm;
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
