import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.util.SystemInfo;
import gui.MainNavDialog;

import javax.swing.*;
import java.awt.*;

/**
 * This app transforms Salesforce profile metadata files into set of permission set
 * files based on selected configuration. It can also reduce the original profile files
 * in order to optimize and remove duplicities.
 *
 * CJWizard is a library for creating swing based wizard dialogs in Java.
 * https://github.com/cjwizard/cjwizard
 * <p>
 * FlatLaf is a modern open-source cross-platform Look and Feel for Java Swing desktop applications.
 * https://github.com/JFormDesigner/FlatLaf
 */
public class SalesforcePermissionHelper {

    public static void main(String[] args) {

        if( SystemInfo.isMacOS ) {
            // enable screen menu bar
            // (moves menu bar from JFrame window to top of screen)
            System.setProperty( "apple.laf.useScreenMenuBar", "true" );

            // application name used in screen menu bar
            // (in first menu after the "apple" menu)
            System.setProperty( "apple.awt.application.name", "Salesforce Permissions Helper" );

            // appearance of window title bars
            // possible values:
            //   - "system": use current macOS appearance (light or dark)
            //   - "NSAppearanceNameAqua": use light appearance
            //   - "NSAppearanceNameDarkAqua": use dark appearance
            // (needs to be set on main thread; setting it on AWT thread does not work)
            System.setProperty( "apple.awt.application.appearance", "system" );
        }

        // Linux
        if( SystemInfo.isLinux ) {
            // enable custom window decorations
            JFrame.setDefaultLookAndFeelDecorated( true );
            JDialog.setDefaultLookAndFeelDecorated( true );
        }

        FlatCobalt2IJTheme.setup();

        MainNavDialog dialog = new MainNavDialog();
        dialog.setSize(new Dimension(960, 600));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setResizable(false);

    }
}
