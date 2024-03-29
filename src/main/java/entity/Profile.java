package entity;//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1-b171012.0423 
//         See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2022.01.30 at 01:02:57 AM CET 
//


import jakarta.xml.bind.annotation.*;

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
 *         &lt;element name="applicationVisibilities" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="application" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="default" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="visible" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="categoryGroupVisibilities" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="dataCategories" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="dataCategoryGroup" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="visibility" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="classAccesses" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="apexClass" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="custom" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="customMetadataTypeAccesses" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="customPermissions" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="fieldPermissions" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="field" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="readable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="layoutAssignments" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="layout" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="recordType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="loginIpRanges" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="endAddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="startAddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="objectPermissions" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="allowCreate" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="allowDelete" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="allowEdit" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="allowRead" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="modifyAllRecords" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="object" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="viewAllRecords" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="pageAccesses" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="apexPage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="recordTypeVisibilities" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="default" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="personAccountDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *                   &lt;element name="recordType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="visible" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="tabVisibilities" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="tab" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="visibility" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="userLicense" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="userPermissions" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "applicationVisibilities",
    "categoryGroupVisibilities",
    "classAccesses",
    "custom",
    "customMetadataTypeAccesses",
    "customPermissions",
    "fieldPermissions",
        // "layoutAssignments",
        // "loginIpRanges",
    "objectPermissions",
    "pageAccesses",
    "recordTypeVisibilities",
    "tabVisibilities",
    "userLicense",
    "userPermissions"
})
@XmlRootElement(name = "Profile")
@XmlSeeAlso({PermissionSet.class})
public class Profile {

    protected List<ApplicationVisibilities> applicationVisibilities;
    @XmlElement(required = true)
    protected List<CategoryGroupVisibilities> categoryGroupVisibilities;
    @XmlElement(required = true)
    protected List<ClassAccesses> classAccesses;
    protected boolean custom;
    @XmlElement(required = true)
    protected List<CustomMetadataTypeAccesses> customMetadataTypeAccesses;
    @XmlElement(required = true)
    protected List<CustomPermissions> customPermissions;
    @XmlElement(required = true)
    protected List<FieldPermissions> fieldPermissions;
    @XmlElement(required = true)
    protected List<ObjectPermissions> objectPermissions;
    @XmlElement(required = true)
    protected List<PageAccesses> pageAccesses;
    @XmlElement(required = true)
    protected List<RecordTypeVisibilities> recordTypeVisibilities;
    @XmlElement(required = true)
    protected List<TabVisibilities> tabVisibilities;
    @XmlElement(required = true)
    protected String userLicense;
    @XmlElement(required = true)
    protected List<UserPermissions> userPermissions;

    /**
     * Gets the value of the applicationVisibilities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationVisibilities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationVisibilities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationVisibilities }
     * 
     * 
     */
    public List<ApplicationVisibilities> getApplicationVisibilities() {
        if (applicationVisibilities == null) {
            applicationVisibilities = new ArrayList<ApplicationVisibilities>();
        }
        return this.applicationVisibilities;
    }

    /**
     * Gets the value of the categoryGroupVisibilities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoryGroupVisibilities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoryGroupVisibilities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoryGroupVisibilities }
     * 
     * 
     */
    public List<CategoryGroupVisibilities> getCategoryGroupVisibilities() {
        if (categoryGroupVisibilities == null) {
            categoryGroupVisibilities = new ArrayList<CategoryGroupVisibilities>();
        }
        return this.categoryGroupVisibilities;
    }

    /**
     * Gets the value of the classAccesses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classAccesses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassAccesses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClassAccesses }
     * 
     * 
     */
    public List<ClassAccesses> getClassAccesses() {
        if (classAccesses == null) {
            classAccesses = new ArrayList<ClassAccesses>();
        }
        return this.classAccesses;
    }

    /**
     * Gets the value of the custom property.
     * 
     */
    public boolean isCustom() {
        return custom;
    }

    /**
     * Sets the value of the custom property.
     * 
     */
    public void setCustom(boolean value) {
        this.custom = value;
    }

    /**
     * Gets the value of the customMetadataTypeAccesses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customMetadataTypeAccesses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomMetadataTypeAccesses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomMetadataTypeAccesses }
     * 
     * 
     */
    public List<CustomMetadataTypeAccesses> getCustomMetadataTypeAccesses() {
        if (customMetadataTypeAccesses == null) {
            customMetadataTypeAccesses = new ArrayList<CustomMetadataTypeAccesses>();
        }
        return this.customMetadataTypeAccesses;
    }

    /**
     * Gets the value of the customPermissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customPermissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomPermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomPermissions }
     * 
     * 
     */
    public List<CustomPermissions> getCustomPermissions() {
        if (customPermissions == null) {
            customPermissions = new ArrayList<CustomPermissions>();
        }
        return this.customPermissions;
    }

    /**
     * Gets the value of the fieldPermissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldPermissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldPermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldPermissions }
     * 
     * 
     */
    public List<FieldPermissions> getFieldPermissions() {
        if (fieldPermissions == null) {
            fieldPermissions = new ArrayList<FieldPermissions>();
        }
        return this.fieldPermissions;
    }

    /**
     * Gets the value of the objectPermissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectPermissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectPermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectPermissions }
     * 
     * 
     */
    public List<ObjectPermissions> getObjectPermissions() {
        if (objectPermissions == null) {
            objectPermissions = new ArrayList<ObjectPermissions>();
        }
        return this.objectPermissions;
    }

    /**
     * Gets the value of the pageAccesses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pageAccesses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPageAccesses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PageAccesses }
     * 
     * 
     */
    public List<PageAccesses> getPageAccesses() {
        if (pageAccesses == null) {
            pageAccesses = new ArrayList<PageAccesses>();
        }
        return this.pageAccesses;
    }

    /**
     * Gets the value of the recordTypeVisibilities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recordTypeVisibilities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecordTypeVisibilities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecordTypeVisibilities }
     * 
     * 
     */
    public List<RecordTypeVisibilities> getRecordTypeVisibilities() {
        if (recordTypeVisibilities == null) {
            recordTypeVisibilities = new ArrayList<RecordTypeVisibilities>();
        }
        return this.recordTypeVisibilities;
    }

    /**
     * Gets the value of the tabVisibilities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tabVisibilities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTabVisibilities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TabVisibilities }
     * 
     * 
     */
    public List<TabVisibilities> getTabVisibilities() {
        if (tabVisibilities == null) {
            tabVisibilities = new ArrayList<TabVisibilities>();
        }
        return this.tabVisibilities;
    }

    /**
     * Gets the value of the userLicense property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserLicense() {
        return userLicense;
    }

    /**
     * Sets the value of the userLicense property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserLicense(String value) {
        this.userLicense = value;
    }

    /**
     * Gets the value of the userPermissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userPermissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserPermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserPermissions }
     * 
     * 
     */
    public List<UserPermissions> getUserPermissions() {
        if (userPermissions == null) {
            userPermissions = new ArrayList<UserPermissions>();
        }
        return this.userPermissions;
    }

    public void setApplicationVisibilities(List<ApplicationVisibilities> applicationVisibilities) {
        this.applicationVisibilities = applicationVisibilities;
    }

    public void setCategoryGroupVisibilities(List<CategoryGroupVisibilities> categoryGroupVisibilities) {
        this.categoryGroupVisibilities = categoryGroupVisibilities;
    }

    public void setClassAccesses(List<ClassAccesses> classAccesses) {
        this.classAccesses = classAccesses;
    }

    public void setCustomMetadataTypeAccesses(List<CustomMetadataTypeAccesses> customMetadataTypeAccesses) {
        this.customMetadataTypeAccesses = customMetadataTypeAccesses;
    }

    public void setCustomPermissions(List<CustomPermissions> customPermissions) {
        this.customPermissions = customPermissions;
    }

    public void setFieldPermissions(List<FieldPermissions> fieldPermissions) {
        this.fieldPermissions = fieldPermissions;
    }

    public void setObjectPermissions(List<ObjectPermissions> objectPermissions) {
        this.objectPermissions = objectPermissions;
    }

    public void setPageAccesses(List<PageAccesses> pageAccesses) {
        this.pageAccesses = pageAccesses;
    }

    public void setRecordTypeVisibilities(List<RecordTypeVisibilities> recordTypeVisibilities) {
        this.recordTypeVisibilities = recordTypeVisibilities;
    }

    public void setTabVisibilities(List<TabVisibilities> tabVisibilities) {
        this.tabVisibilities = tabVisibilities;
    }

    public void setUserPermissions(List<UserPermissions> userPermissions) {
        this.userPermissions = userPermissions;
    }

}