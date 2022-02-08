import entity.Profile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResourceReader {

    public static Set<Profile> parseProfiles(String resourceDirectory) throws JAXBException {

        JAXBContext jc = JAXBContext.newInstance(Profile.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        File pr1Dir = new File(resourceDirectory);
        File[] fileList = pr1Dir.listFiles();

        if(fileList == null){
            return null; // todo throw custom exception
        }

        return Arrays.stream(fileList).parallel().map(f ->
        {
            Profile p = null;
            try {
                p = (Profile) unmarshaller.unmarshal(f);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return p;
        }).collect(Collectors.toSet());

    }

    public static Collection<String> getResourcesFromDirectory( final File directory, final Pattern pattern) {

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


}
