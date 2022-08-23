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

import com.github.cjwizard.AbstractPageFactory;
import com.github.cjwizard.WizardPage;
import com.github.cjwizard.WizardSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class WizardFactory extends AbstractPageFactory {

    /**
     * in this more complex example, we'll use this array to define ALL pages in
     * order. This will be used to populate the navigation pane.
     * <p>
     * We also cheat a bit here to simulate a tree like navigation pane by using
     * some spacing with the names of all the wizard pages.
     */
    protected final WizardPage[] pages = {
            new Step1LoadProfiles(),
            new Step2SelectPermissions(),
            new Step3TransformedFilenames(),
            new Step4RemoveDuplicates(),
            new Step5Output()
    };
    private final Logger log = LogManager.getLogger(WizardFactory.class.getSimpleName());

    private WizardPage buildPage(int pageCount, final WizardSettings settings) {
        switch (pageCount) {
            case 0:
                return new Step1LoadProfiles(settings);
            case 1:
                return new Step2SelectPermissions(settings);
            case 2:
                return new Step3TransformedFilenames(settings);
            case 3:
                return new Step4RemoveDuplicates(settings);
            case 4:
                return new Step5Output(settings);
        }
        return null;
    }

    @Override
    public WizardPage createPage(List<WizardPage> path, WizardSettings settings) {
        log.info("creating page " + path.size());
        WizardPage page = buildPage(path.size(), settings);
        log.debug("Returning page: " + page);
        return page;

    }

}
