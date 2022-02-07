import entity.PermissionSet;
import entity.Profile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.HashSet;

public class Main {

    public static final String IN_DIR = "src/main/resources/pr1";
    public static final String OUT_DIR = "src/main/resources/pr1_out";

    public static void main(String[] args) {

        // readAllProfiles
        /*
        try {
           HashSet<Profile> profileSet = (HashSet<Profile>) ResourceReader.parseProfiles("src/main/resources/pr1");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        */

        /* read args from cmdline

        Options options = new Options();

        Option input = new Option("i", "input", true, "input directory");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output directory");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;//not a good practice, it serves it purpose

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("SF-profile-transform", options);
            System.exit(1);
        }

        String inputFilePath = cmd.getOptionValue("input");
        String outputFilePath = cmd.getOptionValue("output");


         */

        // test on one file
        try {

            JAXBContext jc = JAXBContext.newInstance(Profile.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            Profile profile = (Profile) unmarshaller.unmarshal(new File("src/main/resources/pr1/Admin.profile-meta.xml"));

            PermissionSet permissionSet = new PermissionSet();
            permissionSet.setApplicationVisibilities(profile.getApplicationVisibilities());
            permissionSet.setUserPermissions(profile.getUserPermissions());
            permissionSet.setClassAccesses(profile.getClassAccesses());

            profile.setApplicationVisibilities(null);
            profile.setUserPermissions(null);
            profile.setClassAccesses(null);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(profile, new File(OUT_DIR + "/Admin.profile-meta.xml"));
            marshaller.marshal(permissionSet, new File(OUT_DIR + "/Admin.permissionset-meta.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
