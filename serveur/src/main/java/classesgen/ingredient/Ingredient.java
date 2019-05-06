//
// Ce fichier a xe9txe9 gxe9nxe9rxe9 par l'implxe9mentation de rxe9fxe9rence JavaTM Architecture for XML Binding (JAXB), v2.3.1-b171012.0423 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportxe9e xe0 ce fichier sera perdue lors de la recompilation du schxe9ma source. 
// Gxe9nxe9rxe9 le : 2019.05.06 xe0 12:36:42 PM CEST 
//


package classesgen.ingredient;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Ingredient.
 * 
 * <p>Le fragment de schxE9ma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="Ingredient"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Jambon"/&gt;
 *     &lt;enumeration value="Saumon"/&gt;
 *     &lt;enumeration value="Thon"/&gt;
 *     &lt;enumeration value="Poulet"/&gt;
 *     &lt;enumeration value="Mozza"/&gt;
 *     &lt;enumeration value="Chevre"/&gt;
 *     &lt;enumeration value="Roquefort"/&gt;
 *     &lt;enumeration value="Gruyere"/&gt;
 *     &lt;enumeration value="Champignons"/&gt;
 *     &lt;enumeration value="Poivrons"/&gt;
 *     &lt;enumeration value="Aubergine"/&gt;
 *     &lt;enumeration value="Ananas"/&gt;
 *     &lt;enumeration value="Coca"/&gt;
 *     &lt;enumeration value="Bananes"/&gt;
 *     &lt;enumeration value="Melon"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Ingredient", namespace = "http://classesGen/ingredient")
@XmlEnum
public enum Ingredient {

    @XmlEnumValue("Jambon")
    JAMBON("Jambon"),
    @XmlEnumValue("Saumon")
    SAUMON("Saumon"),
    @XmlEnumValue("Thon")
    THON("Thon"),
    @XmlEnumValue("Poulet")
    POULET("Poulet"),
    @XmlEnumValue("Mozza")
    MOZZA("Mozza"),
    @XmlEnumValue("Chevre")
    CHEVRE("Chevre"),
    @XmlEnumValue("Roquefort")
    ROQUEFORT("Roquefort"),
    @XmlEnumValue("Gruyere")
    GRUYERE("Gruyere"),
    @XmlEnumValue("Champignons")
    CHAMPIGNONS("Champignons"),
    @XmlEnumValue("Poivrons")
    POIVRONS("Poivrons"),
    @XmlEnumValue("Aubergine")
    AUBERGINE("Aubergine"),
    @XmlEnumValue("Ananas")
    ANANAS("Ananas"),
    @XmlEnumValue("Coca")
    COCA("Coca"),
    @XmlEnumValue("Bananes")
    BANANES("Bananes"),
    @XmlEnumValue("Melon")
    MELON("Melon");
    private final String value;

    Ingredient(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Ingredient fromValue(String v) {
        for (Ingredient c: Ingredient.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
