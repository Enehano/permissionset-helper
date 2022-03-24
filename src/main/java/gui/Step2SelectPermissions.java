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

import java.util.Vector;

public class Step2SelectPermissions extends WizardPage {

    WizardSettings settings;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    public Step2SelectPermissions() {
        super("Select Permissions", "");
    }

    public Step2SelectPermissions(WizardSettings settings) {
        super("Select Permissions", "");
        this.settings = settings;
        initComponents();
    }

    @Override
    public void updateSettings(WizardSettings settings) {
        super.updateSettings(settings);
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {"object", true, null},
                        {"field", true, null},
                        {"custom", true, null},
                        {"class", true, null},
                        {"record type", true, null},
                        {"page", true, null},
                        {"app", true, null},
                        {"user", true, null},
                },
                new String[]{
                        "permission type", "move to permission set", "create stand-alone permission set"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                    false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                    case 1:
                        return canEdit[columnIndex];
                    case 2:
                        System.out.println(getDataVector());
                        System.out.println(getDataVector().get(rowIndex));
                        System.out.println(((Vector) getDataVector().get(rowIndex)).get(1));
                        return (Boolean) ((Vector) getDataVector().get(rowIndex)).get(1);
                }
                return true;
            }
        });

        settings.put("config", jTable1.getModel());

        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
        );
    }
}
