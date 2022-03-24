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
import entity.Profile;
import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ResourceReaderWriter;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Step1LoadProfiles extends WizardPage {

    private final Logger log = LogManager.getLogger(Step1LoadProfiles.class.getSimpleName());
    Map<String, Profile> profilesMap = null;
    JFileChooser inputFilesDir;
    private WizardSettings settings;
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;

    public Step1LoadProfiles() {
        super("Load Profiles", "Navigate to input directory");
    }

    /**
     * Creates new form Step1LoadProfiles
     */
    public Step1LoadProfiles(WizardSettings settings) {
        super("Load Profiles", "Navigate to input directory");
        this.settings = settings;
        initComponents();
        setFinishEnabled(false);
        setNextEnabled(true);
    }

    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTextField1.setName("inputDir");

        inputFilesDir = new JFileChooser();
        inputFilesDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        jButton1.setText("Choose");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[0][9],
                new String[]{
                        "Profile Name",
                        "# objects", "# fields", "# custom",
                        "# classes", "# record types", "# pages",
                        "# app", "#user"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
                    , java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
                    , java.lang.Integer.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(200);
            jTable1.getColumnModel().getColumn(1).setMinWidth(1);
            jTable1.getColumnModel().getColumn(2).setMinWidth(1);
            jTable1.getColumnModel().getColumn(3).setMinWidth(1);
        }

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel = jTable1.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(300);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addGap(2, 2, 2))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
        );
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int option = inputFilesDir.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File inputDirectory = inputFilesDir.getSelectedFile();
            jTextField1.setText(inputDirectory.getPath());
            log.info("Folder Selected: " + inputDirectory.getName());
            doProcess(inputDirectory);

        } else {
            log.info("Open command canceled");
        }

    }

    void doProcess(File inputDirectory) {

        //todo show processing

        jTextField1.setText("processing");
        jTextField1.setEnabled(false);
        jButton1.setEnabled(false);
        setPrevEnabled(false);
        setNextEnabled(false);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    profilesMap = ResourceReaderWriter.parseProfiles(inputDirectory);
                } catch (JAXBException | IOException e) {
                    log.error(e);
                }

                setNextEnabled(true);
//                } catch (IOException | InterruptedException ex) {
//                    log.error("{}", ex.getMessage());
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
                jTextField1.setEnabled(true);
                jButton1.setEnabled(true);
                jTextField1.setText(inputDirectory.getPath());

                if (profilesMap != null) {

                    settings.put("profilesMap", profilesMap);

                    // Data of the table
                    Object[][] data = new Object[profilesMap.size()][9];
                    int i = 0;
                    for (Map.Entry<String, Profile> entry : profilesMap.entrySet()) {

                        data[i][0] = entry.getKey();
                        data[i][1] = entry.getValue().getObjectPermissions().size();
                        data[i][2] = entry.getValue().getFieldPermissions().size();
                        data[i][3] = entry.getValue().getCustomPermissions().size();
                        data[i][4] = entry.getValue().getClassAccesses().size();
                        data[i][5] = entry.getValue().getRecordTypeVisibilities().size();
                        data[i][6] = entry.getValue().getPageAccesses().size();
                        data[i][7] = entry.getValue().getApplicationVisibilities().size();
                        data[i][8] = entry.getValue().getUserPermissions().size();
                        i++;
                    }

                    jTable1.setModel(new javax.swing.table.DefaultTableModel(
                            data,
                            new String[]{
                                    "Profile Name",
                                    "# objects", "# fields", "# custom",
                                    "# classes", "# record types", "# pages",
                                    "# app", "#user"
                            }
                    ) {
                        Class[] types = new Class[]{
                                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
                                , java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
                                , java.lang.Integer.class
                        };
                        boolean[] canEdit = new boolean[]{
                                false, false, false, false, false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                            return types[columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit[columnIndex];
                        }
                    });

                    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                    TableColumnModel colModel = jTable1.getColumnModel();
                    colModel.getColumn(0).setPreferredWidth(300);

                }
            }
        });
    }

}
