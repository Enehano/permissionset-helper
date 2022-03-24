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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ProfileFactory;
import utils.ResourceReaderWriter;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class Step5Output extends WizardPage {

    private final Logger log = LogManager.getLogger(Step5Output.class.getSimpleName());

    private WizardSettings settings;

    private File outputFilesDir;

    private ProfileFactory profileFactory;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser outputDirectory;
    private javax.swing.JTextField jTextField1;

    public Step5Output(WizardSettings settings) {
        super("Output Options", "Finished");
        this.settings = settings;
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

        //todo show processing

        jTextField1.setText("processing");
        jTextField1.setEnabled(false);
        jButton1.setEnabled(false);
        setPrevEnabled(false);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //      try {
                profileFactory = new ProfileFactory(new ResourceReaderWriter());
                profileFactory.processAndSerialize(settings, outputFilesDir);

              //  setNextEnabled(true);
//                } catch (IOException | InterruptedException ex) {
//                    log.error("{}", ex.getMessage());
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
                jTextField1.setEnabled(true);
                jButton1.setEnabled(true);
                setPrevEnabled(true);
            }
        });
    }

    private void initComponents() {

        outputDirectory = new javax.swing.JFileChooser();
        outputDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        jTextField1 = new javax.swing.JTextField();
        jTextField1.setName("outputDir");

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("Choose");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Process");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addGap(2, 2, 2))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1))
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addContainerGap(268, Short.MAX_VALUE))
        );
    }
}
