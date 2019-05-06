//
// Ce fichier a xe9txe9 gxe9nxe9rxe9 par l'implxe9mentation de rxe9fxe9rence JavaTM Architecture for XML Binding (JAXB), v2.3.1-b171012.0423 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportxe9e xe0 ce fichier sera perdue lors de la recompilation du schxe9ma source. 
// Gxe9nxe9rxe9 le : 2019.05.06 xe0 12:36:42 PM CEST 
//


package classesgen.plat;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import classesgen.ingredient.Ingredient;
import classesgen.typedeplat.TypeDePlat;


/**
 * <p>Classe Java pour Plat complex type.
 * 
 * <p>Le fragment de schxE9ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Plat"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="type" type="{http://classesGen/typeDePlat}TypeDePlat"/&gt;
 *         &lt;element name="prix" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="ingredients" type="{http://classesGen/ingredient}Ingredient" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Plat", propOrder = {
    "id",
    "image",
    "type",
    "prix",
    "ingredients"
})
public class Plat {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String image;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TypeDePlat type;
    protected double prix;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected List<Ingredient> ingredients;

    /**
     * Obtient la valeur de la proprixE9txE9 id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * DxE9finit la valeur de la proprixE9txE9 id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la proprixE9txE9 image.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * DxE9finit la valeur de la proprixE9txE9 image.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Obtient la valeur de la proprixE9txE9 type.
     * 
     * @return
     *     possible object is
     *     {@link TypeDePlat }
     *     
     */
    public TypeDePlat getType() {
        return type;
    }

    /**
     * DxE9finit la valeur de la proprixE9txE9 type.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeDePlat }
     *     
     */
    public void setType(TypeDePlat value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la proprixE9txE9 prix.
     * 
     */
    public double getPrix() {
        return prix;
    }

    /**
     * DxE9finit la valeur de la proprixE9txE9 prix.
     * 
     */
    public void setPrix(double value) {
        this.prix = value;
    }

    /**
     * Gets the value of the ingredients property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ingredients property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIngredients().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ingredient }
     * 
     * 
     */
    public List<Ingredient> getIngredients() {
        if (ingredients == null) {
            ingredients = new ArrayList<Ingredient>();
        }
        return this.ingredients;
    }

}
