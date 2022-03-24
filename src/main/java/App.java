import com.formdev.flatlaf.FlatLightLaf;
import gui.MainNavDialog;

import java.awt.*;

/**
 * CJWizard is a library for creating swing based wizard dialogs in Java.
 * https://github.com/cjwizard/cjwizard
 * <p>
 * FlatLaf is a modern open-source cross-platform Look and Feel for Java Swing desktop applications.
 * https://github.com/JFormDesigner/FlatLaf
 */
public class App {

    public static void main(String[] args) {

        FlatLightLaf.setup();

        MainNavDialog dialog = new MainNavDialog();
        // dialog.setBounds(300, 300, 1000, 500);
        dialog.setSize(new Dimension(1000, 300));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }
}
