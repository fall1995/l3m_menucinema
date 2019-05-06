//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.05.05 à 04:57:11 PM CEST 
//


package classesgen.plats;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the classesgen.plats package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Plats_QNAME = new QName("http://classesGen/plats", "plats");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: classesgen.plats
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Plats }
     * 
     */
    public Plats createPlats() {
        return new Plats();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Plats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://classesGen/plats", name = "plats")
    public JAXBElement<Plats> createPlats(Plats value) {
        return new JAXBElement<Plats>(_Plats_QNAME, Plats.class, null, value);
    }

}
