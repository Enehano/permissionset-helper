package utils;

import entity.PermissionSet;
import entity.Profile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

public class ResourceReaderWriter {

    private static final String CLEAN_PROFILE = "/clean_profile";
    private static final String NEGATIVE_PROFILE = "/negative_profile";
    private static final String PERMISSIONSET = "/permissionset";

    private static final Logger log = LogManager.getLogger(ResourceReaderWriter.class.getSimpleName());

    public static Map<String, Profile> parseProfiles(File resourceDirectory) throws JAXBException, IOException {

        JAXBContext jc = JAXBContext.newInstance(Profile.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        File[] fileList = resourceDirectory.listFiles();

        if (fileList == null || fileList.length == 0) {
            throw new IOException("Provided input path is empty or not a directory");
        }
        return Arrays.stream(fileList)
                .sorted()
                .collect(
                        LinkedHashMap::new,                           // Supplier
                        (map, file) -> {                              // Accumulator
                            if (!file.isDirectory()) {
                                try {
                                    map.put(file.getName().split("\\.")[0], (Profile) unmarshaller.unmarshal(file));
                                } catch (JAXBException e) {
                                    log.error("Unable to process file " + file.getName() + " - " + e);
                                }
                            }
                        },
                        Map::putAll);                                 // Combiner
    }

    private static void writeProfileFiles(Map<String, Profile> profilesMap, File outDir) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance(Profile.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        for (Map.Entry<String, Profile> entry : profilesMap.entrySet()) {
            marshaller.marshal(entry.getValue(), new File(outDir + "/" + entry.getKey()));
        }
    }

    private static void writePermissionsetFiles(Map<String, PermissionSet> permissionSetMap, File outDir) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance(PermissionSet.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        for (Map.Entry<String, PermissionSet> entry : permissionSetMap.entrySet()) {
            marshaller.marshal(entry.getValue(), new File(outDir + "/" + entry.getKey()));
        }
    }

    static Collection<String> getResourcesFromDirectory(final File directory, final Pattern pattern) {

        final ArrayList<String> retval = new ArrayList<String>();
        final File[] fileList = directory.listFiles();
        for (final File file : fileList) {
            if (file.isDirectory()) {
                retval.addAll(getResourcesFromDirectory(file, pattern));
            } else {
                try {
                    final String fileName = file.getCanonicalPath();
                    final boolean accept = pattern.matcher(fileName).matches();
                    if (accept) {
                        retval.add(fileName);
                    }
                } catch (final IOException e) {
                    throw new Error(e);
                }
            }
        }
        return retval;
    }

    public void serialize(File outputFilesDir, Map<String, Profile> profilesWithNegativeValuesMap,
                          Map<String, PermissionSet> permissionSetMap, Map<String, Profile> emptyProfilesMap) {

        File emptyProfilesDir = new File(outputFilesDir.getAbsolutePath() + CLEAN_PROFILE);
        File negativeProfilesDir = new File(outputFilesDir.getAbsolutePath() + NEGATIVE_PROFILE);
        File permissionSetsDir = new File(outputFilesDir.getAbsolutePath() + PERMISSIONSET);

        //  Path createDirs = Paths.get(outputFilesDir.getAbsolutePath() + CLEAN_PROFILE + "//.." + NEGATIVE_PROFILE + "//.." + PERMISSIONSET);

        try {
            Files.createDirectories(emptyProfilesDir.toPath());
            Files.createDirectories(negativeProfilesDir.toPath());
            Files.createDirectories(permissionSetsDir.toPath());

            ResourceReaderWriter.writePermissionsetFiles(permissionSetMap, permissionSetsDir);
            ResourceReaderWriter.writeProfileFiles(emptyProfilesMap, emptyProfilesDir);
            ResourceReaderWriter.writeProfileFiles(profilesWithNegativeValuesMap, negativeProfilesDir);

        } catch (IOException e) {
            log.error("Cannot create directories - " + e);
            // todo throw new custom exceptions and show on UI
        } catch (JAXBException e) {
            log.error("XML marshalling failed - " + e);
        }
    }


}
