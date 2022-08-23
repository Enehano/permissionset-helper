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
     *         &lt;element name="layout" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="recordType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "layout",
        "recordType"
    })
    public class LayoutAssignments {

        @XmlElement(required = true)
        protected String layout;
        protected String recordType;

        /**
         * Gets the value of the layout property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getLayout() {
            return layout;
        }

        /**
         * Sets the value of the layout property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setLayout(String value) {
            this.layout = value;
        }

        /**
         * Gets the value of the recordType property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRecordType() {
            return recordType;
        }

        /**
         * Sets the value of the recordType property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRecordType(String value) {
            this.recordType = value;
        }

    }
