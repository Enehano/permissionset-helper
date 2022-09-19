package controller;

import com.sforce.soap.metadata.MetadataConnection;
import utils.FileBasedDeployAndRetrieve;
import utils.MetadataLoginUtil;
import utils.ResourceReaderWriter;

import java.io.File;

public class MetadataController {

    private MetadataConnection metadataConnection;

    public MetadataController(MetadataConnection metadataConnection) {
        this.metadataConnection = metadataConnection;
    }

    public File retrieveProfilesFromOrg() throws Exception {
        FileBasedDeployAndRetrieve metadataTool = new FileBasedDeployAndRetrieve(metadataConnection);
        return ResourceReaderWriter.unzip(metadataTool.retrieveZipWithProfiles());
    }

    public void deployPermissionsToOrg(File zipFile) throws Exception {
        FileBasedDeployAndRetrieve metadataTool = new FileBasedDeployAndRetrieve(metadataConnection);
        metadataTool.deployProfilesAndPermissionSets(zipFile);
    }
}
