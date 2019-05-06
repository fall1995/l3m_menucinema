//
// Ce fichier a xe9txe9 gxe9nxe9rxe9 par l'implxe9mentation de rxe9fxe9rence JavaTM Architecture for XML Binding (JAXB), v2.3.1-b171012.0423 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportxe9e xe0 ce fichier sera perdue lors de la recompilation du schxe9ma source. 
// Gxe9nxe9rxe9 le : 2019.05.06 xe0 12:36:42 PM CEST 
//


package classesgen.typedeplat;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TypeDePlat.
 * 
 * <p>Le fragment de schxE9ma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeDePlat"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Entree"/&gt;
 *     &lt;enumeration value="Plat"/&gt;
 *     &lt;enumeration value="Dessert"/&gt;
 *     &lt;enumeration value="Boisson"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TypeDePlat", namespace = "http://classesGen/typeDePlat")
@XmlEnum
public enum TypeDePlat {

    @XmlEnumValue("Entree")
    ENTREE("Entree"),
    @XmlEnumValue("Plat")
    PLAT("Plat"),
    @XmlEnumValue("Dessert")
    DESSERT("Dessert"),
    @XmlEnumValue("Boisson")
    BOISSON("Boisson");
    private final String value;

    TypeDePlat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeDePlat fromValue(String v) {
        for (TypeDePlat c: TypeDePlat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
