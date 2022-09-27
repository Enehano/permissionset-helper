/*

 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gui;

import com.github.cjwizard.WizardPage;
import com.github.cjwizard.WizardSettings;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.ws.ConnectionException;
import controller.MetadataController;
import controller.OAuthController;
import entity.Profile;
import gui.utils.PathShortener;
import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Step1LoadProfiles extends WizardPage {
    private final Logger log = LogManager.getLogger(Step1LoadProfiles.class.getSimpleName());
    public static final String RETRIEVE_FROM_ORG_OPT = "org";
    boolean retrieveFromOrgSelected = true;
    JFileChooser inputFilesDir;
    File inputDirectory;
    private WizardSettings cache;
    OAuthController loginController;
    MetadataController metadataController;
    MetadataConnection metadataConnection;

    public Step1LoadProfiles() {
        super("Load Profiles", "Navigate to input directory");
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton loadProfilesButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton logToSandboxButton;
    private javax.swing.JButton logToProdButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel chosenDirectoryLabel;
    private javax.swing.JLabel loggedToOrgLabel;
    private javax.swing.JLabel profilesLoadedLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jPanel1;
    private javax.swing.JButton jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSeparator jSeparator1;

    /**
     * Creates new form Step1LoadProfiles
     */
    public Step1LoadProfiles(WizardSettings cache) {
        super("Load Profiles", "Navigate to input directory or login to Salesforce Instance");
        this.cache = cache;
        initComponents();
    }

    private void radioButtonActionPerformed(java.awt.event.ActionEvent e) {
        retrieveFromOrgSelected = e.getActionCommand().equals(RETRIEVE_FROM_ORG_OPT);
        jButton1.setEnabled(!retrieveFromOrgSelected);
        jRadioButton1.setEnabled(!retrieveFromOrgSelected);
        logToSandboxButton.setEnabled(retrieveFromOrgSelected);
        logToProdButton.setEnabled(retrieveFromOrgSelected);
    }

    private void chooseDirButtonClicked(java.awt.event.ActionEvent evt) {
        int option = inputFilesDir.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            inputDirectory = inputFilesDir.getSelectedFile();
            chosenDirectoryLabel.setText(PathShortener.shortenPath(inputDirectory.getPath(), 2));
            log.info("Folder Selected: " + inputDirectory.getName());
        } else {
            log.info("Open command canceled");
        }
        loadProfilesButton.setEnabled(true);
    }

    private void loadProfilesButtonClicked(java.awt.event.ActionEvent evt) {

        if(retrieveFromOrgSelected) {

            loadProfilesButton.setText("Retrieving...");
            loadProfilesButton.setEnabled(false);
            setPrevEnabled(false);
            setNextEnabled(false);

            if (OAuthController.getToken() != null) {
                SwingUtilities.invokeLater(() -> {
                    Map<String, Profile> profilesMap = null;
                    try {
                        metadataController = new MetadataController(metadataConnection);
                        inputDirectory = ResourceReaderWriter.moveToProfilesFolder(metadataController.retrieveProfilesFromOrg());
                        profilesMap = ResourceReaderWriter.parseProfiles(inputDirectory);
                    } catch (Exception e) {
                        log.error(e);
                    }
                    jButton1.setEnabled(true);
                    loadProfilesButton.setEnabled(true);
                    loadProfilesButton.setText("Load Profiles");

                    setNextEnabled(true);

                    if (profilesMap != null) {
                        cache.put("profilesMap", profilesMap);
                        profilesLoadedLabel.setText(profilesMap.size() + " Profiles loaded");
                    }
                });
            }
        } else {
            try {
                Map<String, Profile> profilesMap = ResourceReaderWriter.parseProfiles(inputDirectory);
                if (profilesMap != null) {
                    cache.put("profilesMap", profilesMap);
                    profilesLoadedLabel.setText(profilesMap.size() + " Profiles loaded");
                }
            } catch (JAXBException | IOException e ) {
                throw new RuntimeException(e);
            }
        }
    }

    private void logToProdButtonClicked(ActionEvent evt) {
        loginController = new OAuthController( Config.OAUTH_PROD_SERVER_VAL );
        callLoginThread();
    }

    private void callLoginThread() {
        SwingUtilities.invokeLater(() -> {
            while(true){
                if (loginController.isLoginProcessCompleted()) {
                    loadProfilesButton.setEnabled(true);
                    try {
                        metadataConnection = MetadataLoginUtil.oauthLogin(OAuthController.getToken());
                    } catch (ConnectionException e) {
                        log.error(e);
                    }
                    cache.put("connection", metadataConnection);
                    try {
                        URL endpointURL = new URL(metadataConnection.getConfig().getServiceEndpoint());
                        loggedToOrgLabel.setText("Connected to:" + endpointURL.getHost());
                    } catch (MalformedURLException e) {
                        log.warn(e);
                        loggedToOrgLabel.setText("Login successful!");
                    }
                    break;
                }
            }
        });
    }

    private void logToSandboxButtonClicked(ActionEvent evt) {
        loginController = new OAuthController(Config.OAUTH_SB_SERVER_VAL);
        callLoginThread();
    }


    private void initComponents() {

        inputFilesDir = new JFileChooser();
        inputFilesDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        jPanel1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        chosenDirectoryLabel = new javax.swing.JLabel();
        loggedToOrgLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        loadProfilesButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        profilesLoadedLabel = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        logToSandboxButton = new javax.swing.JButton();
        logToProdButton = new javax.swing.JButton();

        jButton1.setEnabled(false);
        jRadioButton1.setSelected(true);
        jRadioButton1.setEnabled(true);

        ButtonGroup group = new ButtonGroup();
        group.add(jRadioButton1);
        group.add(jRadioButton2);

        jButton3.setEnabled(false);

        jPanel1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel1.setFocusPainted(true);
                jRadioButton1.doClick();
                jPanel2.setFocusPainted(false);
            }
        });

        jPanel2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel2.setFocusPainted(true);
                jRadioButton2.doClick();
                jPanel1.setFocusPainted(false);
            }
        });

        jRadioButton1.setActionCommand(RETRIEVE_FROM_ORG_OPT);
        jRadioButton1.addActionListener(e -> radioButtonActionPerformed(e));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Webp.net-resizeimage2.png")));

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD, jLabel4.getFont().getSize()+5));
        jLabel4.setText("Retrieve from Org");

        loggedToOrgLabel.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        loggedToOrgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loggedToOrgLabel.setText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jRadioButton1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jRadioButton2.setMaximumSize(new java.awt.Dimension(42, 42));
        jRadioButton2.setMinimumSize(new java.awt.Dimension(42, 42));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Webp.net-resizeimage6.png"))); // NOI18N

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | java.awt.Font.BOLD, jLabel3.getFont().getSize()+5));
        jLabel3.setText("Load from Filesystem");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                // .addGap(27, 27, 27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("Choose Directory");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseDirButtonClicked(evt);
            }
        });

        chosenDirectoryLabel.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        chosenDirectoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chosenDirectoryLabel.setText("No Directory Chosen");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chosenDirectoryLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(123, 123, 123))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(60, Short.MAX_VALUE)
                                .addComponent(chosenDirectoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
        );

        jPanel6.setLayout(new java.awt.GridLayout());

        loadProfilesButton.setText("Load Profiles");
        loadProfilesButton.setEnabled(false);
        loadProfilesButton.addActionListener(evt -> loadProfilesButtonClicked(evt));
        jPanel6.add(loadProfilesButton);

        jPanel7.setLayout(new java.awt.GridLayout());

        profilesLoadedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profilesLoadedLabel.setText("0 Profiles Loaded");
        jPanel7.add(profilesLoadedLabel);

        jButton3.setText("Inspect and Compare");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // yea nah
            }
        });
        jPanel7.add(jButton3);

        logToSandboxButton.setText("Log into Sandbox");
        logToSandboxButton.setActionCommand("Log into Sandbox");
        logToSandboxButton.addActionListener(evt -> logToSandboxButtonClicked(evt));

        logToProdButton.setText("Log into Production");
        logToProdButton.setActionCommand("Log into Production");
        logToProdButton.addActionListener(evt -> logToProdButtonClicked(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(loggedToOrgLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(112, 112, 112)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(logToProdButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(logToSandboxButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(loggedToOrgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(logToSandboxButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(logToProdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jRadioButton2.setActionCommand("file");
        jRadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioButtonActionPerformed(e);
            }
        });
    }// </editor-fold>

}
