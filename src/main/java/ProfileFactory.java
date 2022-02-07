import entity.Profile;

public class ProfileFactory {


    Profile createEmptyProfile(Profile inProfile){
        inProfile.setApplicationVisibilities(null);
        //inProfile.setCategoryGroupVisibilities(null);   //not in permseet
        //inProfile.setTabVisibilities(null);  //not in permseet
        inProfile.setRecordTypeVisibilities(null);

        inProfile.setUserPermissions(null);
        inProfile.setCustomPermissions(null);
        inProfile.setFieldPermissions(null);
        inProfile.setObjectPermissions(null);

        inProfile.setClassAccesses(null);
        inProfile.setCustomMetadataTypeAccesses(null);
        inProfile.setPageAccesses(null);

        return inProfile;
    }

    Profile createProfileWithNegativeValues(Profile inProfile){




        return inProfile;
    }

}
