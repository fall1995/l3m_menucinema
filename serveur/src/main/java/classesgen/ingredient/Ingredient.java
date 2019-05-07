//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.05.07 à 04:05:00 PM CEST 
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
 *     &lt;enumeration value="viande"/>
 *     &lt;enumeration value="tomates"/>
 *     &lt;enumeration value="salade"/>
 *     &lt;enumeration value="olives"/>
 *     &lt;enumeration value="pain"/>
 *     &lt;enumeration value="avocate"/>
 *     &lt;enumeration value="sel"/>
 *     &lt;enumeration value="sucre"/>
 *     &lt;enumeration value="lait"/>
 *     &lt;enumeration value="fromage"/>
 *     &lt;enumeration value="poulet"/>
 *     &lt;enumeration value="orange"/>
 *     &lt;enumeration value="citron"/>
 *     &lt;enumeration value="framboise"/>
 *     &lt;enumeration value="fraise"/>
 *     &lt;enumeration value="champignons"/>
 *     &lt;enumeration value="mangue"/>
 *     &lt;enumeration value="pâtes"/>
 *     &lt;enumeration value="pomme"/>
 *     &lt;enumeration value="oignons"/>
 *     &lt;enumeration value="kiwi"/>
 *     &lt;enumeration value="thé"/>
 *     &lt;enumeration value="espresso"/>
 *     &lt;enumeration value="amande"/>
 *     &lt;enumeration value="vanille"/>
 *     &lt;enumeration value="chocolat"/>
 *     &lt;enumeration value="caramel"/>
 *     &lt;enumeration value="café"/>
 *     &lt;enumeration value="avocat"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Ingredient", namespace = "http://classesGen/ingredient")
@XmlEnum
public enum Ingredient {

    @XmlEnumValue("viande")
    VIANDE("viande"),
    @XmlEnumValue("tomates")
    TOMATES("tomates"),
    @XmlEnumValue("salade")
    SALADE("salade"),
    @XmlEnumValue("olives")
    OLIVES("olives"),
    @XmlEnumValue("pain")
    PAIN("pain"),
    @XmlEnumValue("avocate")
    AVOCATE("avocate"),
    @XmlEnumValue("sel")
    SEL("sel"),
    @XmlEnumValue("sucre")
    SUCRE("sucre"),
    @XmlEnumValue("lait")
    LAIT("lait"),
    @XmlEnumValue("fromage")
    FROMAGE("fromage"),
    @XmlEnumValue("poulet")
    POULET("poulet"),
    @XmlEnumValue("orange")
    ORANGE("orange"),
    @XmlEnumValue("citron")
    CITRON("citron"),
    @XmlEnumValue("framboise")
    FRAMBOISE("framboise"),
    @XmlEnumValue("fraise")
    FRAISE("fraise"),
    @XmlEnumValue("champignons")
    CHAMPIGNONS("champignons"),
    @XmlEnumValue("mangue")
    MANGUE("mangue"),
    @XmlEnumValue("p\u00e2tes")
    PÂTES("p\u00e2tes"),
    @XmlEnumValue("pomme")
    POMME("pomme"),
    @XmlEnumValue("oignons")
    OIGNONS("oignons"),
    @XmlEnumValue("kiwi")
    KIWI("kiwi"),
    @XmlEnumValue("th\u00e9")
    THÉ("th\u00e9"),
    @XmlEnumValue("espresso")
    ESPRESSO("espresso"),
    @XmlEnumValue("amande")
    AMANDE("amande"),
    @XmlEnumValue("vanille")
    VANILLE("vanille"),
    @XmlEnumValue("chocolat")
    CHOCOLAT("chocolat"),
    @XmlEnumValue("caramel")
    CARAMEL("caramel"),
    @XmlEnumValue("caf\u00e9")
    CAFÉ("caf\u00e9"),
    @XmlEnumValue("avocat")
    AVOCAT("avocat");
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
