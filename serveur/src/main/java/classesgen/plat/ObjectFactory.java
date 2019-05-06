//
// Ce fichier a xe9txe9 gxe9nxe9rxe9 par l'implxe9mentation de rxe9fxe9rence JavaTM Architecture for XML Binding (JAXB), v2.3.1-b171012.0423 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportxe9e xe0 ce fichier sera perdue lors de la recompilation du schxe9ma source. 
// Gxe9nxe9rxe9 le : 2019.05.06 xe0 12:36:42 PM CEST 
//


package classesgen.plat;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the classesgen.plat package. 
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

    private final static QName _Plat_QNAME = new QName("http://classesGen/plat", "plat");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: classesgen.plat
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Plat }
     * 
     */
    public Plat createPlat() {
        return new Plat();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Plat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Plat }{@code >}
     */
    @XmlElementDecl(namespace = "http://classesGen/plat", name = "plat")
    public JAXBElement<Plat> createPlat(Plat value) {
        return new JAXBElement<Plat>(_Plat_QNAME, Plat.class, null, value);
    }

}
