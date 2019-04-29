//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.04.29 à 11:46:15 AM CEST 
//
package classesgen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour Client complex type.
 *
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette
 * classe.
 *
 * <pre>
 * &lt;complexType name="Client">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telephone" type="{classesGen/client}Tel"/>
 *         &lt;element name="adresse" type="{classesGen/client}Adresse"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Client", propOrder = {
    "nom",
    "prenom",
    "telephone",
    "adresse"
})
public class Client {

    @XmlElement(required = true)
    protected String nom;
    @XmlElement(required = true)
    protected String prenom;
    @XmlElement(required = true)
    protected String telephone;
    @XmlElement(required = true)
    protected String adresse;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String photo;
    @XmlElement(required = true)
    protected String email;

    /**
     * Methode permet d'envoyer id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Methode permet de modifier id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtient la valeur de la propriété nom.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de la propriété nom.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Obtient la valeur de la propriété prenom.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit la valeur de la propriété prenom.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPrenom(String value) {
        this.prenom = value;
    }

    /**
     * Obtient la valeur de la propriété telephone.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Définit la valeur de la propriété telephone.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

    /**
     * Obtient la valeur de la propriété adresse.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit la valeur de la propriété adresse.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAdresse(String value) {
        this.adresse = value;
    }

    /**
     * Methode permet d'envoyer la photo
     *
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }
    /**
     *Methode permet de modifier la photo  
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
     /**
     * Methode permet d'envoyer   email  
     * @return email
     */
    public String getEmail() {
        return email;
    }
     /**
     * Methode permet de modifier  l'email 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
