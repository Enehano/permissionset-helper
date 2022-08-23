package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.Objects;

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
     *         &lt;element name="apexClass" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
        "apexClass",
        "enabled"
    })
    public class ClassAccesses {

        @XmlElement(required = true)
        protected String apexClass;
        protected boolean enabled;

        /**
         * Gets the value of the apexClass property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getApexClass() {
            return apexClass;
        }

        /**
         * Sets the value of the apexClass property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setApexClass(String value) {
            this.apexClass = value;
        }

        /**
         * Gets the value of the enabled property.
         *
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * Sets the value of the enabled property.
         *
         */
        public void setEnabled(boolean value) {
            this.enabled = value;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassAccesses that = (ClassAccesses) o;
        return enabled == that.enabled &&
                apexClass.equals(that.apexClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apexClass, enabled);
    }
}
