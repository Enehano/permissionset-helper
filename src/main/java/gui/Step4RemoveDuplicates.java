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
import entity.PermissionSet;
import utils.PermSetComparator;
import utils.ProfileFactory;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.*;
import java.util.stream.Collectors;

public class Step4RemoveDuplicates extends WizardPage {
    private WizardSettings cache;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    public Step4RemoveDuplicates() {
        super("Remove Duplicates", "Almost done");
        //initComponents();
    }

    public Step4RemoveDuplicates(WizardSettings cache) {
        super("Remove Duplicates", "Almost done");
        this.cache = cache;
        processCachedSettings();
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTable1.setName("unified");

        jTable1.setModel(createSettingsModel());
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel = jTable1.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(500);
        colModel.getColumn(1).setPreferredWidth(30);
        colModel.getColumn(2).setPreferredWidth(200);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
        );
    }

    private TableModel createSettingsModel() {

        Map<PermissionSet, List<String>> filteredDuplicates = (Map<PermissionSet, List<String>>) cache.get("duplicates");

        // Data of the table
        Object[][] filenames = new Object[filteredDuplicates.size()][3];
        int i = 0;
        for (Map.Entry<PermissionSet, List<String>> entry : filteredDuplicates.entrySet()) {
            filenames[i][0] = Arrays.toString(entry.getValue().toArray());
            //  filenames[i][2] = new HintTextField("TBS");
            i++;
        }
        return new javax.swing.table.DefaultTableModel(
                filenames,
                new String[]{
                        "Permission sets", "Unify", "New Name"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                    false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }

    private void processCachedSettings() {
        if( cache!=null ) {
            Map<String, PermissionSet> objectPerms = ProfileFactory.processPermissionSets(cache);
            cache.put("objectPerms", objectPerms);
            Map<PermissionSet, List<String>> filteredDuplicates = ProfileFactory.filterDuplicates(cache);
            cache.put("duplicates", filteredDuplicates);
        }
    }
}
