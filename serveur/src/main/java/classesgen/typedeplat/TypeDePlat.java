//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.05.05 à 04:57:11 PM CEST 
//


package classesgen.typedeplat;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TypeDePlat.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeDePlat">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="entree"/>
 *     &lt;enumeration value="plat"/>
 *     &lt;enumeration value="dessert"/>
 *     &lt;enumeration value="boisson"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TypeDePlat", namespace = "http://classesGen/typeDePlat")
@XmlEnum
public enum TypeDePlat {

    @XmlEnumValue("entree")
    ENTREE("entree"),
    @XmlEnumValue("plat")
    PLAT("plat"),
    @XmlEnumValue("dessert")
    DESSERT("dessert"),
    @XmlEnumValue("boisson")
    BOISSON("boisson");
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
