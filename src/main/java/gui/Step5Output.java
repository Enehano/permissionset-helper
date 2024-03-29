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
import controller.MetadataController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ProfileFactory;
import utils.ResourceReaderWriter;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

public class Step5Output extends WizardPage {
    private final Logger log = LogManager.getLogger(Step5Output.class.getSimpleName());
    private WizardSettings cachedSettings;
    private File outputFilesDir = new File(System.getProperty("user.dir"));
    private ProfileFactory profileFactory;
    private javax.swing.JFileChooser outputDirectory;
    private JTextField jTextField1;

    public Step5Output(WizardSettings cachedSettings) {
        super("Output Options", "Finished");
        this.cachedSettings = cachedSettings;
        initComponents();
    }

    public Step5Output() {
        super("Output Options", "Finished");
        initComponents();
    }

    /**
     * This is the last page in the wizard, so we will enable the finish
     * button and disable the "Next >" button just before the page is
     * displayed:
     */
    public void rendering(List<WizardPage> path, WizardSettings settings) {
        super.rendering(path, settings);
        setFinishEnabled(true);
        setNextEnabled(false);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int option = outputDirectory.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            outputFilesDir = outputDirectory.getSelectedFile();
            jTextField1.setText(outputFilesDir.getPath());
            log.info("Folder Selected: " + outputFilesDir.getName());
        } else {
            log.info("Open command canceled");
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        doProcess();
    }

    void doProcess() {

        buttonSaveMetadata.setText("Processing");
        buttonSaveMetadata.setEnabled(false);
        setPrevEnabled(false);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                profileFactory = new ProfileFactory(new ResourceReaderWriter());

                // todo optimize files creation (can copy files not double process)

                if(checkBoxSaveFiles.isSelected()){
                    File outputDir = null;
                    if(checkBoxTransformProfiles.isSelected()) {
                        outputDir = profileFactory.processAndSerializeAll(cachedSettings, outputFilesDir);
                    } else {
                        outputDir = profileFactory.processAndSerializePermSets(cachedSettings, outputFilesDir);
                    }
                    try {
                        Desktop.getDesktop().open(outputDir);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(checkBoxPushToOrg.isSelected()){

                    if(checkBoxTransformProfiles.isSelected()) {
                        profileFactory.processAndSerializeAllDeploy(cachedSettings, outputFilesDir);
                    } else {
                        profileFactory.processAndSerializePermSetsDeploy(cachedSettings, outputFilesDir);
                    }

                    MetadataConnection connection = (MetadataConnection) cachedSettings.get("connection");
                    MetadataController metadataController = new MetadataController(connection);
                    InputStream manifest = (getClass().getResourceAsStream("/deploy_manifest/package.xml"));
                    File zipToDeploy = ResourceReaderWriter.createZipToDeploy(outputFilesDir, manifest);
                    try {
                        metadataController.deployPermissionsToOrg(zipToDeploy);
                    } catch (Exception e) {
                        log.error("Deploy to Org failed", e);
                    }

                }

                setPrevEnabled(true);
                buttonSaveMetadata.setText("Done");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JButton();
        checkBoxPushToOrg = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JButton();
        checkBoxSaveFiles = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        buttonSaveMetadata = new javax.swing.JButton();
        checkBoxTransformProfiles = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        checkBoxPushToOrg.setSelected(true);
        checkBoxSaveFiles.setSelected(true);
        checkBoxTransformProfiles.setSelected(true);

        checkBoxPushToOrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Webp.net-resizeimage2.png")));

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD, jLabel4.getFont().getSize()+5));
        jLabel4.setText("   Push to Org");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(checkBoxPushToOrg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addContainerGap()
                                                 )
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(checkBoxPushToOrg)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        //  jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        //  jRadioButton2.setBackground(new java.awt.Color(204, 255, 204));
        checkBoxSaveFiles.setMaximumSize(new java.awt.Dimension(42, 42));
        checkBoxSaveFiles.setMinimumSize(new java.awt.Dimension(42, 42));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Webp.net-resizeimage6.png"))); // NOI18N

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | java.awt.Font.BOLD, jLabel3.getFont().getSize()+5));
        jLabel3.setText("Save to Filesystem");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(checkBoxSaveFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(checkBoxSaveFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("");
        jPanel6.add(jLabel14);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        buttonSaveMetadata.setText("Save Metadata");
        buttonSaveMetadata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        checkBoxTransformProfiles.setText("  Transform the Profiles");
        checkBoxTransformProfiles.setFont(checkBoxTransformProfiles.getFont().deriveFont(checkBoxTransformProfiles.getFont().getStyle(), checkBoxTransformProfiles.getFont().getSize()+5));

        checkBoxTransformProfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Removes the selected permissions from Profiles. Generates two sets of Profile files on the system:");

        jLabel6.setText("1st one with negative XML values, 2nd one with reduced files");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonSaveMetadata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        )
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(checkBoxTransformProfiles)
                                                .addGap(300, 300, 300))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(123, 123, 123))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(214, 214, 214))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addComponent(checkBoxTransformProfiles)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(48, 48, 48)
                                .addComponent(buttonSaveMetadata, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSaveMetadata;
    private javax.swing.JCheckBox checkBoxTransformProfiles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jPanel1;
    private javax.swing.JButton jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JCheckBox checkBoxPushToOrg;
    private javax.swing.JCheckBox checkBoxSaveFiles;
    private javax.swing.JSeparator jSeparator1;
        // End of variables declaration//GEN-END:variables
}
