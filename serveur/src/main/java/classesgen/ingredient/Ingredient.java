//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.04.30 à 01:32:10 PM CEST 
//


package classesgen.ingredient;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Ingredient.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="Ingredient">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="jambon"/>
 *     &lt;enumeration value="saumon"/>
 *     &lt;enumeration value="thon"/>
 *     &lt;enumeration value="poulet"/>
 *     &lt;enumeration value="mozza"/>
 *     &lt;enumeration value="chèvre"/>
 *     &lt;enumeration value="roquefort"/>
 *     &lt;enumeration value="gruyère"/>
 *     &lt;enumeration value="champignons"/>
 *     &lt;enumeration value="poivrons"/>
 *     &lt;enumeration value="aubergine"/>
 *     &lt;enumeration value="ananas"/>
 *     &lt;enumeration value="coca"/>
 *     &lt;enumeration value="bananes"/>
 *     &lt;enumeration value="melon"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Ingredient", namespace = "http://classesGen/ingredient")
@XmlEnum
public enum Ingredient {

    @XmlEnumValue("jambon")
    JAMBON("jambon"),
    @XmlEnumValue("saumon")
    SAUMON("saumon"),
    @XmlEnumValue("thon")
    THON("thon"),
    @XmlEnumValue("poulet")
    POULET("poulet"),
    @XmlEnumValue("mozza")
    MOZZA("mozza"),
    @XmlEnumValue("ch\u00e8vre")
    CHÈVRE("ch\u00e8vre"),
    @XmlEnumValue("roquefort")
    ROQUEFORT("roquefort"),
    @XmlEnumValue("gruy\u00e8re")
    GRUYÈRE("gruy\u00e8re"),
    @XmlEnumValue("champignons")
    CHAMPIGNONS("champignons"),
    @XmlEnumValue("poivrons")
    POIVRONS("poivrons"),
    @XmlEnumValue("aubergine")
    AUBERGINE("aubergine"),
    @XmlEnumValue("ananas")
    ANANAS("ananas"),
    @XmlEnumValue("coca")
    COCA("coca"),
    @XmlEnumValue("bananes")
    BANANES("bananes"),
    @XmlEnumValue("melon")
    MELON("melon");
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
