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
     *         &lt;element name="endAddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="startAddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "endAddress",
        "startAddress"
    })
    public class LoginIpRanges {

        @XmlElement(required = true)
        protected String endAddress;
        @XmlElement(required = true)
        protected String startAddress;

        /**
         * Gets the value of the endAddress property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getEndAddress() {
            return endAddress;
        }

        /**
         * Sets the value of the endAddress property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setEndAddress(String value) {
            this.endAddress = value;
        }

        /**
         * Gets the value of the startAddress property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getStartAddress() {
            return startAddress;
        }

        /**
         * Sets the value of the startAddress property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setStartAddress(String value) {
            this.startAddress = value;
        }

    }
