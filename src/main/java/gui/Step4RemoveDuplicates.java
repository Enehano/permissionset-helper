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
import java.util.*;
import java.util.stream.Collectors;

public class Step4RemoveDuplicates extends WizardPage {


    private WizardSettings settings;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    public Step4RemoveDuplicates() {
        super("Remove Duplicates", "Almost done");
        initComponents();
    }

    public Step4RemoveDuplicates(WizardSettings settings) {
        super("Remove Duplicates", "Almost done");
        this.settings = settings;
        initComponents();
    }

    private void initComponents() {

        Map<PermissionSet, List<String>> filteredDuplicates = new HashMap<>();

        if (settings != null) {

            PermSetComparator comparator = new PermSetComparator();
            HashSet<PermissionSet> l = new HashSet<>();
            Map<String, PermissionSet> objectPerms = ProfileFactory.processPermissionSets(settings);
            Map<PermissionSet, List<String>> duplicates = new HashMap<>();

            for (Map.Entry<String, PermissionSet> entry : objectPerms.entrySet()) {
                if (l.isEmpty()) {
                    l.add(entry.getValue());
                    duplicates.put(entry.getValue(), new ArrayList(Arrays.asList(entry.getKey())));
                } else {
                    boolean contains = false;
                    for (PermissionSet s : l) {
                        if (comparator.compare(entry.getValue(), s) == 0) {
                            contains = true;
                        }
                    }
                    if (!contains) {
                        l.add(entry.getValue());
                        duplicates.put(entry.getValue(), new ArrayList(Arrays.asList(entry.getKey())));
                    } else {
                        ArrayList<String> newList = (ArrayList) duplicates.get(entry.getValue());
                        newList.add(entry.getKey());
                        duplicates.put(entry.getValue(), newList);
                    }
                }
            }

            filteredDuplicates = duplicates.entrySet().stream()
                    .filter(x -> x.getValue().size() > 1)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            settings.put("duplicates", filteredDuplicates);
            settings.put("objectPerms", objectPerms);
        }

        // Data of the table
        Object[][] filenames = new Object[filteredDuplicates.size()][3];
        int i = 0;
        for (Map.Entry<PermissionSet, List<String>> entry : filteredDuplicates.entrySet()) {
            filenames[i][0] = Arrays.toString(entry.getValue().toArray());
            filenames[i][2] = ".permissionset-meta.xml";
            i++;
        }

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTable1.setName("unified");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        });
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
}
