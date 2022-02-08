import entity.Profile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        // readAllProfiles
        /*
        try {
           HashSet<Profile> profileSet = (HashSet<Profile>) ResourceReader.parseProfiles("src/main/resources/pr1");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        */

        // test on one file
        try {

            JAXBContext jc = JAXBContext.newInstance(Profile.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            Profile profile = (Profile) unmarshaller.unmarshal(new File("src/main/resources/pr1/Admin.profile-meta.xml"));

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(profile, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
