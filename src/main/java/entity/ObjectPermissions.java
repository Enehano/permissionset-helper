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
     *         &lt;element name="allowCreate" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *         &lt;element name="allowDelete" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *         &lt;element name="allowEdit" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *         &lt;element name="allowRead" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *         &lt;element name="modifyAllRecords" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *         &lt;element name="object" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="viewAllRecords" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
        "allowCreate",
        "allowDelete",
        "allowEdit",
        "allowRead",
        "modifyAllRecords",
        "object",
        "viewAllRecords"
    })
    public class ObjectPermissions {

        protected boolean allowCreate;
        protected boolean allowDelete;
        protected boolean allowEdit;
        protected boolean allowRead;
        protected boolean modifyAllRecords;
        @XmlElement(required = true)
        protected String object;
        protected boolean viewAllRecords;

        /**
         * Gets the value of the allowCreate property.
         *
         */
        public boolean isAllowCreate() {
            return allowCreate;
        }

        /**
         * Sets the value of the allowCreate property.
         *
         */
        public void setAllowCreate(boolean value) {
            this.allowCreate = value;
        }

        /**
         * Gets the value of the allowDelete property.
         *
         */
        public boolean isAllowDelete() {
            return allowDelete;
        }

        /**
         * Sets the value of the allowDelete property.
         *
         */
        public void setAllowDelete(boolean value) {
            this.allowDelete = value;
        }

        /**
         * Gets the value of the allowEdit property.
         *
         */
        public boolean isAllowEdit() {
            return allowEdit;
        }

        /**
         * Sets the value of the allowEdit property.
         *
         */
        public void setAllowEdit(boolean value) {
            this.allowEdit = value;
        }

        /**
         * Gets the value of the allowRead property.
         *
         */
        public boolean isAllowRead() {
            return allowRead;
        }

        /**
         * Sets the value of the allowRead property.
         *
         */
        public void setAllowRead(boolean value) {
            this.allowRead = value;
        }

        /**
         * Gets the value of the modifyAllRecords property.
         *
         */
        public boolean isModifyAllRecords() {
            return modifyAllRecords;
        }

        /**
         * Sets the value of the modifyAllRecords property.
         *
         */
        public void setModifyAllRecords(boolean value) {
            this.modifyAllRecords = value;
        }

        /**
         * Gets the value of the object property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getObject() {
            return object;
        }

        /**
         * Sets the value of the object property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setObject(String value) {
            this.object = value;
        }

        /**
         * Gets the value of the viewAllRecords property.
         *
         */
        public boolean isViewAllRecords() {
            return viewAllRecords;
        }

        /**
         * Sets the value of the viewAllRecords property.
         *
         */
        public void setViewAllRecords(boolean value) {
            this.viewAllRecords = value;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectPermissions that = (ObjectPermissions) o;
        return allowCreate == that.allowCreate &&
                allowDelete == that.allowDelete &&
                allowEdit == that.allowEdit &&
                allowRead == that.allowRead &&
                modifyAllRecords == that.modifyAllRecords &&
                viewAllRecords == that.viewAllRecords &&
                object.equals(that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allowCreate, allowDelete, allowEdit, allowRead, modifyAllRecords, object, viewAllRecords);
    }
}
