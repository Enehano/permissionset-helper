package utils;

import entity.PermissionSet;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class PermSetComparator implements Comparator<PermissionSet> {

    @Override
    public int compare(PermissionSet o1, PermissionSet o2) {

        if (o1 == o2) return 0;
        if (o1 == null || o2 == null) return -1;

        return compare(o1.getCustomPermissions(), o2.getCustomPermissions()) +
                compare(o1.getFieldPermissions(), o2.getFieldPermissions()) +
                compare(o1.getObjectPermissions(), o2.getObjectPermissions()) +
                compare(o1.getUserPermissions(), o2.getUserPermissions()) +
                compare(o1.getApplicationVisibilities(), o2.getApplicationVisibilities()) +
                compare(o1.getClassAccesses(), o2.getClassAccesses()) +
                compare(o1.getRecordTypeVisibilities(), o2.getRecordTypeVisibilities()) +
                compare(o1.getCustomMetadataTypeAccesses(), o2.getCustomMetadataTypeAccesses()) +
                compare(o1.getPageAccesses(), o2.getPageAccesses());
    }

    /**
     * tohle by melo fungovat protoze overriduju equals a hashcode, lets see
     *
     * @param permissions1
     * @param permissions2
     * @param <T>
     * @return
     */
    private <T> int compare(List<T> permissions1, List<T> permissions2) {

        if (permissions1 == permissions2) return 0;
        if (permissions1 == null || permissions2 == null) return -1;

        HashSet<T> hashSet1 = new HashSet<>(permissions1);
        HashSet<T> hashSet2 = new HashSet<>(permissions2);

        if (hashSet1.containsAll(hashSet2)) {
            return 0;
        } else {
            return -1;
        }
    }
}
