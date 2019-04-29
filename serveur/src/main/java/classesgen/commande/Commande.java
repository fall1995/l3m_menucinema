//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.04.29 à 01:19:35 PM CEST 
//
package classesgen.commande;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import classesgen.client.Client;
import classesgen.plat.Plat;

/**
 * <p>
 * Classe Java pour Commande complex type.
 *
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette
 * classe.
 *
 * <pre>
 * &lt;complexType name="Commande">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="client" type="{classesGen/client}Client"/>
 *         &lt;element name="plat" type="{http://classesGen/plat}Plat" maxOccurs="unbounded"/>
 *         &lt;element name="film" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="adresseLivraison" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="prix" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Commande", propOrder = {
    "client",
    "plat",
    "film"
})
public class Commande {

    @XmlElement(required = true)
    protected Client client;
    @XmlElement(required = true)
    protected List<Plat> plat;
    protected List<String> film;
    @XmlAttribute(name = "adresseLivraison")
    protected String adresseLivraison;
    @XmlAttribute(name = "date")
    protected String date;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "prix")
    protected Double prix;
    @XmlAttribute(name = "idClient")
    protected String idClient;

    /**
     * Obtient la valeur de la propriété client.
     *
     * @return possible object is {@link Client }
     *
     */
    public Client getClient() {
        return client;
    }

    /**
     * Définit la valeur de la propriété client.
     *
     * @param value allowed object is {@link Client }
     *
     */
    public void setClient(Client value) {
        this.client = value;
    }

    /**
<<<<<<< HEAD
     * Gets the value of the plat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plat property.
     * 
=======
     * Gets the value of the plats property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the plats property.
     *
>>>>>>> 9e0376f8646db0bead7f8efab1c6760987b47101
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlat().add(newItem);
     * </pre>
     *
     *
     * <p>
<<<<<<< HEAD
     * Objects of the following type(s) are allowed in the list
     * {@link Plat }
     * 
     * 
=======
     * Objects of the following type(s) are allowed in the list {@link Plats }
     *
     *
     * @return
>>>>>>> 9e0376f8646db0bead7f8efab1c6760987b47101
     */
    public List<Plat> getPlat() {
        if (plat == null) {
            plat = new ArrayList<Plat>();
        }
        return this.plat;
    }

    /**
     * Gets the value of the film property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the film property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilm().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     *
     *
     * @return
     */
    public List<String> getFilm() {
        if (film == null) {
            film = new ArrayList<String>();
        }
        return this.film;
    }

    /**
     * Obtient la valeur de la propriété adresseLivraison.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    /**
     * Définit la valeur de la propriété adresseLivraison.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAdresseLivraison(String value) {
        this.adresseLivraison = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     *
     * @return possible object is {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété prix.
     *
     * @return possible object is {@link Double }
     *
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * Définit la valeur de la propriété prix.
     *
     * @param value allowed object is {@link Double }
     *
     */
    public void setPrix(Double value) {
        this.prix = value;
    }

    /**
     * Methode qui permet de modifier le prix
     *
     * @param idFilms
     */
    public void setIdFilms(List<String> idFilms) {
        this.film = idFilms;
    }

    /**
     * Methode qui permet de modifier la liste des id des plats
     *
     * @param idplats
     */
    public void setIdplats(List<Plat> idplats) {
        this.plat = idplats;
    }

    /**
     * Methode qui permet d'envoyer l'id du client
     *
     * @return idClient
     */
    public String getIdClient() {
        return idClient;
    }

    /**
     * Methode qui permet de modifier l id du client
     *
     * @param idClient
     */
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

}
