//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1-b171012.0423 
//         See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2022.01.30 at 01:02:57 AM CET 
//


import entity.Profile;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Profile }
     * 
     */
    public Profile createProfile() {
        return new Profile();
    }

    /**
     * Create an instance of {@link Profile.ApplicationVisibilities }
     * 
     */
    public Profile.ApplicationVisibilities createProfileApplicationVisibilities() {
        return new Profile.ApplicationVisibilities();
    }

    /**
     * Create an instance of {@link Profile.CategoryGroupVisibilities }
     * 
     */
    public Profile.CategoryGroupVisibilities createProfileCategoryGroupVisibilities() {
        return new Profile.CategoryGroupVisibilities();
    }

    /**
     * Create an instance of {@link Profile.ClassAccesses }
     * 
     */
    public Profile.ClassAccesses createProfileClassAccesses() {
        return new Profile.ClassAccesses();
    }

    /**
     * Create an instance of {@link Profile.CustomMetadataTypeAccesses }
     * 
     */
    public Profile.CustomMetadataTypeAccesses createProfileCustomMetadataTypeAccesses() {
        return new Profile.CustomMetadataTypeAccesses();
    }

    /**
     * Create an instance of {@link Profile.CustomPermissions }
     * 
     */
    public Profile.CustomPermissions createProfileCustomPermissions() {
        return new Profile.CustomPermissions();
    }

    /**
     * Create an instance of {@link Profile.FieldPermissions }
     * 
     */
    public Profile.FieldPermissions createProfileFieldPermissions() {
        return new Profile.FieldPermissions();
    }

    /**
     * Create an instance of {@link Profile.LayoutAssignments }
     * 
     */
    public Profile.LayoutAssignments createProfileLayoutAssignments() {
        return new Profile.LayoutAssignments();
    }

    /**
     * Create an instance of {@link Profile.LoginIpRanges }
     * 
     */
    public Profile.LoginIpRanges createProfileLoginIpRanges() {
        return new Profile.LoginIpRanges();
    }

    /**
     * Create an instance of {@link Profile.ObjectPermissions }
     * 
     */
    public Profile.ObjectPermissions createProfileObjectPermissions() {
        return new Profile.ObjectPermissions();
    }

    /**
     * Create an instance of {@link Profile.PageAccesses }
     * 
     */
    public Profile.PageAccesses createProfilePageAccesses() {
        return new Profile.PageAccesses();
    }

    /**
     * Create an instance of {@link Profile.RecordTypeVisibilities }
     * 
     */
    public Profile.RecordTypeVisibilities createProfileRecordTypeVisibilities() {
        return new Profile.RecordTypeVisibilities();
    }

    /**
     * Create an instance of {@link Profile.TabVisibilities }
     * 
     */
    public Profile.TabVisibilities createProfileTabVisibilities() {
        return new Profile.TabVisibilities();
    }

    /**
     * Create an instance of {@link Profile.UserPermissions }
     * 
     */
    public Profile.UserPermissions createProfileUserPermissions() {
        return new Profile.UserPermissions();
    }

}
