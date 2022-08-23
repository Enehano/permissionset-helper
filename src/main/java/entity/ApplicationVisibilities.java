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
 *         &lt;element name="application" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="default" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="visible" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "application",
    "_default",
    "visible"
})
public class ApplicationVisibilities {

    @XmlElement(required = true)
    protected String application;
    @XmlElement(name = "default")  // todo ONLY IN PROFILE
    protected boolean _default;
    protected boolean visible;

    /**
     * Gets the value of the application property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setApplication(String value) {
        this.application = value;
    }

    /**
     * Gets the value of the default property.
     *
     */
    public boolean isDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     *
     */
    public void setDefault(boolean value) {
        this._default = value;
    }

    /**
     * Gets the value of the visible property.
     *
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     *
     */
    public void setVisible(boolean value) {
        this.visible = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationVisibilities that = (ApplicationVisibilities) o;
        return _default == that._default &&
                visible == that.visible &&
                application.equals(that.application);
    }

    @Override
    public int hashCode() {
        return Objects.hash(application, _default, visible);
    }
}
