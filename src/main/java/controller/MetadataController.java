package controller;

import com.sforce.soap.metadata.MetadataConnection;
import utils.FileBasedDeployAndRetrieve;
import utils.MetadataLoginUtil;
import utils.ResourceReaderWriter;

import java.io.File;

public class MetadataController {
    public File retrieveProfilesFromOrg() throws Exception {
        MetadataConnection metadataConnection = MetadataLoginUtil.oauthLogin(OAuthController.getToken());
        FileBasedDeployAndRetrieve metadataTool = new FileBasedDeployAndRetrieve(metadataConnection);
        return ResourceReaderWriter.unzip(metadataTool.retrieveZipWithProfiles());
    }
}
