//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.05.07 à 04:05:53 PM CEST 
//


package classesgen.typedeplat;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the classesgen.typedeplat package. 
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

    private final static QName _TypeDePlat_QNAME = new QName("http://classesGen/typeDePlat", "typeDePlat");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: classesgen.typedeplat
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TypeDePlat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://classesGen/typeDePlat", name = "typeDePlat")
    public JAXBElement<TypeDePlat> createTypeDePlat(TypeDePlat value) {
        return new JAXBElement<TypeDePlat>(_TypeDePlat_QNAME, TypeDePlat.class, null, value);
    }

}
