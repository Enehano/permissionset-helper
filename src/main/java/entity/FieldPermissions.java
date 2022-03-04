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
     *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *         &lt;element name="field" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="readable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
        "editable",
        "field",
        "readable"
    })
    public class FieldPermissions {

        protected boolean editable;
        @XmlElement(required = true)
        protected String field;
        protected boolean readable;

        /**
         * Gets the value of the editable property.
         *
         */
        public boolean isEditable() {
            return editable;
        }

        /**
         * Sets the value of the editable property.
         *
         */
        public void setEditable(boolean value) {
            this.editable = value;
        }

        /**
         * Gets the value of the field property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getField() {
            return field;
        }

        /**
         * Sets the value of the field property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setField(String value) {
            this.field = value;
        }

        /**
         * Gets the value of the readable property.
         *
         */
        public boolean isReadable() {
            return readable;
        }

        /**
         * Sets the value of the readable property.
         *
         */
        public void setReadable(boolean value) {
            this.readable = value;
        }

    }
