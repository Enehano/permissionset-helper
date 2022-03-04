package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

/**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected         content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="dataCategories" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="dataCategoryGroup" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="visibility" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "dataCategories",
        "dataCategoryGroup",
        "visibility"
    })
    public class CategoryGroupVisibilities {

        protected List<String> dataCategories;
        @XmlElement(required = true)
        protected String dataCategoryGroup;
        @XmlElement(required = true)
        protected String visibility;

        /**
         * Gets the value of the dataCategories property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dataCategories property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDataCategories().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         *
         *
         */
        public List<String> getDataCategories() {
            if (dataCategories == null) {
                dataCategories = new ArrayList<String>();
            }
            return this.dataCategories;
        }

        /**
         * Gets the value of the dataCategoryGroup property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDataCategoryGroup() {
            return dataCategoryGroup;
        }

        /**
         * Sets the value of the dataCategoryGroup property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDataCategoryGroup(String value) {
            this.dataCategoryGroup = value;
        }

        /**
         * Gets the value of the visibility property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getVisibility() {
            return visibility;
        }

        /**
         * Sets the value of the visibility property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setVisibility(String value) {
            this.visibility = value;
        }

    }
