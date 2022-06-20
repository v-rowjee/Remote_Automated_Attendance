package Views;

import Components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class LoginView {
    JFrame frame;
    JPanel panel;
    AASLabel lblUsername;
    JLabel lblPassword, image;
    AASTextField txtUsername;
    JPasswordField txtPassword;
    JCheckBox chxShowPassword;
    AASButton btnLogin;

    public LoginView(String title){
        frame = new JFrame(title);
        frame.setLayout(new GridLayout(1,2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.decode("#333333"));
        frame.setVisible(true);
        URL iconURL = getClass().getResource("../images/favicon.png");
        frame.setIconImage(new ImageIcon(iconURL).getImage());

        Icon img =new ImageIcon( new ImageIcon(getClass().getResource("../images/logo.png")).getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH));
        image = new JLabel(img);

        panel = new JPanel();
        lblUsername = new AASLabel("Username");
        lblPassword = new JLabel("Password");

        txtUsername = new AASTextField();
        txtPassword = new JPasswordField();
        btnLogin = new AASButton("Login");
        chxShowPassword = new JCheckBox("Show Password");

        //Login Title
        panel.setLayout(new GridLayout(10,1));
        panel.setBorder(new EmptyBorder(25,50,50,50));

        // adding colors
        panel.setBackground(Color.darkGray);
        lblUsername.setForeground(Color.decode("#eeeeee"));
        lblPassword.setForeground(Color.decode("#eeeeee"));
        chxShowPassword.setBackground(Color.darkGray);
        chxShowPassword.setForeground(Color.decode("#aaaaaa"));
        chxShowPassword.setBorder(null);
        chxShowPassword.setBorderPainted(false);
        chxShowPassword.setFocusPainted( false );
        txtPassword.setBackground(Color.decode("#eeeeee"));
        txtPassword.setBorder(null);

        panel.add(Box.createVerticalGlue());
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(Box.createVerticalGlue());
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(chxShowPassword);
        panel.add(Box.createVerticalGlue());
        panel.add(Box.createVerticalGlue());
        panel.add(btnLogin);

        frame.add(image);
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
