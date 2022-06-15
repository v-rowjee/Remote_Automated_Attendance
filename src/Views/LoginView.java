package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class LoginView {
    JFrame frame;
    JPanel panel;
    JLabel lblUsername, lblPassword,lblTitle, image;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JCheckBox chxShowPassword;
    JButton btnLogin;

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
        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");
        lblTitle = new JLabel("LOGIN");

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");
        chxShowPassword = new JCheckBox("Show Password");

        //Login Title
        lblTitle.setFont(new Font("Bodoni", Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setLayout(new GridLayout(10,1));
        panel.setBorder(new EmptyBorder(25,50,50,50));

        // adding colors
        lblTitle.setForeground(Color.decode("#eeeeee"));
        lblUsername.setForeground(Color.decode("#eeeeee"));
        lblPassword.setForeground(Color.decode("#eeeeee"));
        chxShowPassword.setBackground(Color.darkGray);
        chxShowPassword.setForeground(Color.decode("#aaaaaa"));
        chxShowPassword.setBorder(null);
        chxShowPassword.setBorderPainted(false);
        panel.setBackground(Color.darkGray);
        txtUsername.setBackground(Color.decode("#eeeeee"));
        txtUsername.setBorder(null);
        txtPassword.setBackground(Color.decode("#eeeeee"));
        txtPassword.setBorder(null);
        btnLogin.setBorder(null);
        btnLogin.setBackground(Color.decode("#00adb5"));
        btnLogin.setForeground(Color.decode("#f5f5f5"));

//        panel.add(lblTitle);
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
