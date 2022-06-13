package Views;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    JFrame frame;
    JLabel lblUsername;
    JTextField txtUsername;
    JButton btnLogin;

    public LoginView(String title){
        frame = new JFrame(title);
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Icon icon =new ImageIcon( new ImageIcon(getClass().getResource("thank_you_teacher.png")).getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);
        logo.setBounds(0, 0, 400, 400);

        btnLogin = new JButton("Login");

       // frame.add(btnLogin);
        frame.add(logo);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }
}
