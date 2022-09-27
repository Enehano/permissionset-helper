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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.table.TableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Step3TransformedFilenames extends WizardPage {

    private final Logger log = LogManager.getLogger(Step3TransformedFilenames.class.getSimpleName());
    WizardSettings cache;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    public Step3TransformedFilenames() {
        super("Transform", "Step 3");
    }

    public Step3TransformedFilenames(WizardSettings cache) {
        super("Transform", "Step 3");
        this.cache = cache;
        processStep2Settings();
        initComponents();
    }

    @Override
    public boolean onNext(WizardSettings settings) {
        if (jTable1.isEditing())
            jTable1.getCellEditor().stopCellEditing();
        return super.onNext(settings);
    }
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTable1.setModel(createSettingsModel());
        jScrollPane1.setViewportView(jTable1);

        cache.put("filenamesDataModel", jTable1.getModel());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );
    }

    private TableModel createSettingsModel() {

        Map<String, Profile> profilesMap = (Map<String, Profile>) cache.get("profilesMap");
        List<String> permCatToMove = (List<String>) cache.get("permCatToMove");
        List<String> standAlonePermSet = (List<String>) cache.get("standAlonePermSet");

        // Data of the table
        Object[][] filenames = new Object[profilesMap.size() * (standAlonePermSet.size() + 1)][2];
        int i = 0;
        for (Map.Entry<String, Profile> entry : profilesMap.entrySet()) {
            String profileName = entry.getKey();
            filenames[i][0] = profileName;
            String permissionSetName = createPermSetName(profileName);
            filenames[i][1] = permissionSetName;
            if (!standAlonePermSet.isEmpty()) {
                for (String permCat : standAlonePermSet) {
                    filenames[++i][1] = permissionSetName + "_" + permCat;
                }
            }
            ++i;
        }
        return new javax.swing.table.DefaultTableModel(
                filenames,
                new String[]{
                        "Profile name", "Permission set name"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }
    /**
     * Skip the profile file suffix, remove all special characters and whitespaces
     * and add _PS suffix (cannot have duplicate API names even if different metadata)
      */
    private String createPermSetName(String profileName) {
        String name = profileName.split("\\.")[0];
        return name.replaceAll("\\s|%2E|%3A|%C2|%A0|-", "") + "_PS";
    }

    private void processStep2Settings() {

        List<String> standAlonePermSet = new LinkedList<>();
        List<String> permCatToMove = new LinkedList<>();

        TableModel model = (TableModel) cache.get("config");
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1) != null && (Boolean) model.getValueAt(i, 1)) {
                permCatToMove.add((String) model.getValueAt(i, 0));
            }
            if (model.getValueAt(i, 2) != null && (Boolean) model.getValueAt(i, 2)) {
                standAlonePermSet.add((String) model.getValueAt(i, 0));
            }
        }

        cache.put("standAlonePermSet", standAlonePermSet);
        cache.put("permCatToMove", permCatToMove);
    }
}
