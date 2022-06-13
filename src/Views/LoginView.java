package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView {
    JFrame frame;
    JPanel panel;
    JLabel lblUsername, lblPassword;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JCheckBox chxShowPassword;
    JButton btnLogin;

    public LoginView(String title){
        frame = new JFrame(title);
        frame.setLayout(new GridLayout(1,2,0,0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Icon icon =new ImageIcon( new ImageIcon(getClass().getResource("../images/thank_you_teacher.png")).getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);
        logo.setBounds(0, 0, 400, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));
        panel.setBorder(new EmptyBorder(50,50,50,50));
        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");
        chxShowPassword = new JCheckBox("Show Password");

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(chxShowPassword);
        panel.add(btnLogin);


        frame.add(logo);
        frame.add(panel);


    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public JLabel getLblPassword() {
        return lblPassword;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JCheckBox getChxShowPassword() {
        return chxShowPassword;
    }
}
