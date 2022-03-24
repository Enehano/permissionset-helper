package utils;

import com.github.cjwizard.WizardSettings;
import entity.*;

import javax.swing.table.TableModel;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ProfileFactory {

    ResourceReaderWriter rw;

    public ProfileFactory(ResourceReaderWriter rw) {
        this.rw = rw;
    }

    // TODO: 24.03.2022 Filter fields with all negative values from profiles

    public static Map<String, PermissionSet> processPermissionSets(WizardSettings settings) {
        Map<String, Profile> profilesMap = (Map<String, Profile>) settings.get("profilesMap");
        List<String> permCatToMove = (List<String>) settings.get("permCatToMove");

        List<String> standAlonePermSet = (List<String>) settings.get("standAlonePermSet");

        Map<String, PermissionSet> permissionSetMap = new LinkedHashMap<>();
        TableModel model = (TableModel) settings.get("filenamesDataModel");
        for (int i = 0; i < model.getRowCount(); i++) {
            permissionSetMap.put((String) model.getValueAt(i, 1),
                    ProfileFactory.createPermissionSet(profilesMap.get((String) model.getValueAt(i, 0)), new HashSet<>(permCatToMove)));
        }
        return permissionSetMap;
    }

    private static Profile createEmptyProfile(Profile inProfile) {
        Profile ret = new Profile();
        ret.setCategoryGroupVisibilities(inProfile.getCategoryGroupVisibilities());   //not in permseet
        ret.setTabVisibilities(inProfile.getTabVisibilities());  //not in permseet
        ret.setCustom(inProfile.isCustom());
        ret.setUserLicense(inProfile.getUserLicense());
        return ret;
    }

    private static Profile createProfileWithNegativeValues(Profile inProfile) {

        Profile ret = new Profile();

        ret.setApplicationVisibilities(inProfile.getApplicationVisibilities().stream()
                .map(v -> {
                    ApplicationVisibilities newV = new ApplicationVisibilities();
                    newV.setApplication(v.getApplication());
                    newV.setVisible(false);
                    newV.setDefault(v.isDefault());
                    return newV;
                })
                .collect(Collectors.toList()));

        ret.setRecordTypeVisibilities(inProfile.getRecordTypeVisibilities().stream()
                .map(v -> {
                    RecordTypeVisibilities newV = new RecordTypeVisibilities();
                    newV.setPersonAccountDefault(v.isPersonAccountDefault());
                    newV.setRecordType(v.getRecordType());
                    newV.setVisible(false);
                    newV.setDefault(v.isDefault());
                    return newV;
                })
                .collect(Collectors.toList()));

        ret.setUserPermissions(inProfile.getUserPermissions().stream()
                .map(p -> {
                    UserPermissions newP = new UserPermissions();
                    newP.setName(newP.getName());
                    newP.setEnabled(false);
                    return newP;
                })
                .collect(Collectors.toList()));

        ret.setCustomPermissions(inProfile.getCustomPermissions().stream()
                .map(p -> {
                    CustomPermissions newP = new CustomPermissions();
                    newP.setName(newP.getName());
                    newP.setEnabled(false);
                    return newP;
                })
                .collect(Collectors.toList()));

        ret.setFieldPermissions(inProfile.getFieldPermissions().stream()
                .map(p -> {
                    FieldPermissions newP = new FieldPermissions();
                    newP.setEditable(false);
                    newP.setReadable(false);
                    newP.setField(p.getField());
                    return newP;
                })
                .collect(Collectors.toList()));

        ret.setObjectPermissions(inProfile.getObjectPermissions().stream()
                .map(p -> {
                    ObjectPermissions newP = new ObjectPermissions();
                    newP.setAllowEdit(false);
                    newP.setAllowRead(false);
                    newP.setAllowCreate(false);
                    newP.setAllowDelete(false);
                    newP.setModifyAllRecords(false);
                    newP.setViewAllRecords(false);
                    newP.setObject(p.getObject());
                    return newP;
                })
                .collect(Collectors.toList()));

        ret.setClassAccesses(inProfile.getClassAccesses().stream()
                .map(p -> {
                    ClassAccesses newP = new ClassAccesses();
                    newP.setEnabled(false);
                    newP.setApexClass(p.getApexClass());
                    return newP;
                })
                .collect(Collectors.toList()));

        ret.setCustomMetadataTypeAccesses(inProfile.getCustomMetadataTypeAccesses().stream()
                .map(p -> {
                    CustomMetadataTypeAccesses newP = new CustomMetadataTypeAccesses();
                    newP.setEnabled(false);
                    newP.setName(p.getName());
                    return newP;
                })
                .collect(Collectors.toList()));

        ret.setPageAccesses(inProfile.getPageAccesses().stream()
                .map(p -> {
                    PageAccesses newP = new PageAccesses();
                    newP.setEnabled(false);
                    newP.setApexPage(p.getApexPage());
                    return newP;
                })
                .collect(Collectors.toList()));

        return ret;
    }

    private static PermissionSet createPermissionSet(Profile profile, Set<String> permCatToMove) {

        PermissionSet permissionSet = new PermissionSet();
        // todo move to constants - or better create Enum
        if (permCatToMove.contains("app")) {
            permissionSet.setApplicationVisibilities(profile.getApplicationVisibilities());
        }
        if (permCatToMove.contains("user")) {
            permissionSet.setUserPermissions(profile.getUserPermissions());
        }
        if (permCatToMove.contains("class")) {
            permissionSet.setClassAccesses(profile.getClassAccesses());
        }
        if (permCatToMove.contains("record type")) {
            permissionSet.setRecordTypeVisibilities(profile.getRecordTypeVisibilities());
        }

        if (permCatToMove.contains("custom")) {
            permissionSet.setCustomPermissions(profile.getCustomPermissions());
        }
        if (permCatToMove.contains("field")) {
            permissionSet.setFieldPermissions(profile.getFieldPermissions());
        }
        if (permCatToMove.contains("object")) {
            permissionSet.setObjectPermissions(profile.getObjectPermissions());
        }

        if (permCatToMove.contains("metadata")) {
            permissionSet.setCustomMetadataTypeAccesses(profile.getCustomMetadataTypeAccesses());
        }
        if (permCatToMove.contains("page")) {
            permissionSet.setPageAccesses(profile.getPageAccesses());
        }

        return permissionSet;
    }

    public void processAndSerialize(WizardSettings settings, File outputDir) {
        rw.serialize(outputDir, processNegativeProfiles(settings), processPermissionSets(settings), processEmptyProfiles(settings));
    }

    private Map<String, Profile> processEmptyProfiles(WizardSettings settings) {
        Map<String, Profile> profilesMap = (Map<String, Profile>) settings.get("profilesMap");

        Map<String, Profile> emptyProfilesMap = new LinkedHashMap<>();

        TableModel model = (TableModel) settings.get("filenamesDataModel");
        for (int i = 0; i < model.getRowCount(); i++) {
            emptyProfilesMap.put((String) model.getValueAt(i, 0),
                    ProfileFactory.createEmptyProfile(profilesMap.get((String) model.getValueAt(i, 0))));
        }

        return emptyProfilesMap;
    }

    private Map<String, Profile> processNegativeProfiles(WizardSettings settings) {

        Map<String, Profile> profilesMap = (Map<String, Profile>) settings.get("profilesMap");

        Map<String, Profile> profilesWithNegativeValuesMap = new LinkedHashMap<>();

        TableModel model = (TableModel) settings.get("filenamesDataModel");
        for (int i = 0; i < model.getRowCount(); i++) {
            profilesWithNegativeValuesMap.put((String) model.getValueAt(i, 0),
                    ProfileFactory.createProfileWithNegativeValues(profilesMap.get((String) model.getValueAt(i, 0))));
        }

        return profilesWithNegativeValuesMap;
    }

}
