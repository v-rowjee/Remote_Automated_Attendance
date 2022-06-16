import Controllers.LoginController;
import Models.UserModel;
import Views.LoginView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        // javax.swing.plaf.nimbus.NimbusLookAndFeel
        // com.sun.java.swing.plaf.motif.MotifLookAndFeel
        // javax.swing.plaf.metal.MetalLookAndFeel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Assemble all the pieces of the MVC
        LoginView v = new LoginView("Automated Attendance System");

        UserModel m = new UserModel();

        LoginController c = new LoginController(m, v);
        c.initController();
    }
}