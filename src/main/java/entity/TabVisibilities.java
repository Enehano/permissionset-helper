package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

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
     *         &lt;element name="tab" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "tab",
        "visibility"
    })
    public class TabVisibilities {

        @XmlElement(required = true)
        protected String tab;
        @XmlElement(required = true)
        protected String visibility;

        /**
         * Gets the value of the tab property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTab() {
            return tab;
        }

        /**
         * Sets the value of the tab property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTab(String value) {
            this.tab = value;
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
