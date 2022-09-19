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

    // TODO: 24.03.2022 Filter fields with all negative values from profiles (optimization)

    public static Map<String, PermissionSet> processPermissionSets(WizardSettings cachedSettings) {
        Map<String, Profile> profilesMap = (Map<String, Profile>) cachedSettings.get("profilesMap");
        List<String> permCatToMove = (List<String>) cachedSettings.get("permCatToMove");

        List<String> standAlonePermSet = (List<String>) cachedSettings.get("standAlonePermSet"); //todo

        Map<String, PermissionSet> permissionSetMap = new LinkedHashMap<>();
        TableModel model = (TableModel) cachedSettings.get("filenamesDataModel");
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
                    newP.setName(p.getName());
                    newP.setEnabled(false);
                    return newP;
                })
                .collect(Collectors.toList()));

        ret.setCustomPermissions(inProfile.getCustomPermissions().stream()
                .map(p -> {
                    CustomPermissions newP = new CustomPermissions();
                    newP.setName(p.getName());
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
        // todo create Enum and switch
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

    public static Map<PermissionSet, List<String>> filterDuplicates(WizardSettings cachedSettings) {

        Map<String, PermissionSet> objectPerms = (Map<String, PermissionSet>) cachedSettings.get("objectPerms");

        PermSetComparator comparator = new PermSetComparator();
        HashSet<PermissionSet> l = new HashSet<>();

        Map<PermissionSet, List<String>> duplicates = new HashMap<>();

        for (Map.Entry<String, PermissionSet> entry : objectPerms.entrySet()) {
            if (l.isEmpty()) {
                l.add(entry.getValue());
                duplicates.put(entry.getValue(), new ArrayList(Arrays.asList(entry.getKey())));
            } else {
                boolean contains = false;
                for (PermissionSet s : l) {
                    if (comparator.compare(entry.getValue(), s) == 0) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    l.add(entry.getValue());
                    duplicates.put(entry.getValue(), new ArrayList(Arrays.asList(entry.getKey())));
                } else {
                    ArrayList<String> newList = (ArrayList) duplicates.get(entry.getValue());
                    newList.add(entry.getKey());
                    duplicates.put(entry.getValue(), newList);
                }
            }
        }
        return duplicates.entrySet().stream()
                .filter(x -> x.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public File processAndSerialize(WizardSettings cachedSettings, File outputDir) {
        return rw.serialize(outputDir, processPermissionSets(cachedSettings), processNegativeProfiles(cachedSettings), processEmptyProfiles(cachedSettings));
    }

    private Map<String, Profile> processEmptyProfiles(WizardSettings cachedSettings) {
        Map<String, Profile> profilesMap = (Map<String, Profile>) cachedSettings.get("profilesMap");

        Map<String, Profile> emptyProfilesMap = new LinkedHashMap<>();

        TableModel model = (TableModel) cachedSettings.get("filenamesDataModel");
        for (int i = 0; i < model.getRowCount(); i++) {
            emptyProfilesMap.put((String) model.getValueAt(i, 0),
                    ProfileFactory.createEmptyProfile(profilesMap.get((String) model.getValueAt(i, 0))));
        }

        return emptyProfilesMap;
    }

    private Map<String, Profile> processNegativeProfiles(WizardSettings cachedSettings) {

        Map<String, Profile> profilesMap = (Map<String, Profile>) cachedSettings.get("profilesMap");

        Map<String, Profile> profilesWithNegativeValuesMap = new LinkedHashMap<>();

        TableModel model = (TableModel) cachedSettings.get("filenamesDataModel");
        for (int i = 0; i < model.getRowCount(); i++) {
            profilesWithNegativeValuesMap.put((String) model.getValueAt(i, 0),
                    ProfileFactory.createProfileWithNegativeValues(profilesMap.get((String) model.getValueAt(i, 0))));
        }

        return profilesWithNegativeValuesMap;
    }

}
