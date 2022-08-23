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

import com.github.cjwizard.*;
import com.github.cjwizard.pagetemplates.TitledPageTemplate;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.util.List;

public class MainNavDialog extends javax.swing.JDialog {

    private final org.apache.logging.log4j.Logger log = LogManager.getLogger(MainNavDialog.class.getSimpleName());

    private javax.swing.JList<String> jListNavigation;
    private javax.swing.JScrollPane jScrollPane1;
    private WizardFactory factory = new WizardFactory();

    public MainNavDialog() {
        initComponents();
    }


    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListNavigation = new javax.swing.JList<>();

        // first, build the wizard.  The TestFactory defines the
        // wizard content and behavior.
        final WizardContainer wc
                = new WizardContainer(factory,
                new TitledPageTemplate(),
                new StackWizardSettings());

        //do you want to store previously visited path and repeat it if you hit back
        //and then go forward a second time?
        //this options makes sense if you have a conditional path where depending on choice of a page
        // you can visit one of two other pages.
        wc.setForgetTraversedPath(true);

        // add a wizard listener to update the dialog titles and notify the
        // surrounding application of the state of the wizard:
        wc.addWizardListener(new WizardListener() {
            @Override
            public void onCanceled(List<WizardPage> path, WizardSettings settings) {
                log.info("settings: " + wc.getSettings());
                MainNavDialog.this.dispose();
            }

            @Override
            public void onFinished(List<WizardPage> path, WizardSettings settings) {
                log.info("settings: " + wc.getSettings());
                MainNavDialog.this.dispose();
            }

            @Override
            public void onPageChanged(WizardPage newPage, List<WizardPage> path) {
                log.info("settings: " + wc.getSettings());
                // Set the dialog title to match the description of the new page:
                MainNavDialog.this.setTitle(newPage.getTitle());

                //update our nav view
                jListNavigation.setSelectedValue(newPage.getTitle(), true);
            }

            @Override
            public void onPageChanging(WizardPage newPage, List<WizardPage> path) {
            }
        });

        //this populates the list view (nav bar)
        jListNavigation.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return factory.pages.length * 5;
            }

            @Override
            public String getElementAt(int index) {
                if(index < 4){
                    return " ";
                } else if (index % 5 == 4) {
                    return factory.pages[index/5].getTitle();
                } else {
                    return  "↓";
                }
            }
        });

        //hightlight the first item since that's the start position of the wizard
        jListNavigation.setSelectedIndex(4);
        jListNavigation.setEnabled(false);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) jListNavigation.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jScrollPane1.setViewportView(jListNavigation);

        // Set up the standard bookkeeping stuff for a dialog, and
        // add the wizard to the JDialog:
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(wc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(wc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(50)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                                .addContainerGap())
        );
        pack();
    }
}
