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

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.util.Vector;

public class Step2SelectPermissions extends WizardPage {
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable permissionSettingsTable;

    public Step2SelectPermissions() {
        super("Select Permissions", "");
    }

    public Step2SelectPermissions(WizardSettings cache) {
        super("Select Permissions", "");
        initComponents();
        cache.put("config", permissionSettingsTable.getModel());
    }
    @Override
    public void updateSettings(WizardSettings settings) {
        super.updateSettings(settings);
    }

    private TableModel initPermSettingsModel() {
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {"object", true, null},
                        {"field", true, null},
                        {"custom", false, null},
                        {"class", false, null},
                        {"record type", false, null},
                        {"page", false, null},
                        {"app", false, null},
                     //   {"user", true, null},
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
                // todo uncomment when backend support ready
//                switch (columnIndex) {
//                    case 0:
//                    case 1:
//                        return canEdit[columnIndex];
//                    case 2:
//                        System.out.println(getDataVector());
//                        System.out.println(getDataVector().get(rowIndex));
//                        System.out.println(((Vector) getDataVector().get(rowIndex)).get(1));
//                        return (Boolean) ((Vector) getDataVector().get(rowIndex)).get(1);
//                }
//                return true;
                return canEdit[columnIndex];
            }
        };
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        permissionSettingsTable = new javax.swing.JTable();
        permissionSettingsTable.setModel(initPermSettingsModel());
        permissionSettingsTable.setRowHeight(25);
        jScrollPane1.setViewportView(permissionSettingsTable);
        if (permissionSettingsTable.getColumnModel().getColumnCount() > 0) {
            permissionSettingsTable.getColumnModel().getColumn(1).setResizable(false);
            permissionSettingsTable.getColumnModel().getColumn(2).setResizable(false);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
           // centerRenderer.setBorder(new BorderUIResource.CompoundBorderUIResource(jScrollPane1.getBorder(), javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 0)));
            permissionSettingsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        }

      //  jScrollPane1.setBorder(new BorderUIResource.CompoundBorderUIResource(jScrollPane1.getBorder(), javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 0)));

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
