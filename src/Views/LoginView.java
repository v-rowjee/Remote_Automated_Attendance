package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
//        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setVisible(true);

        Icon icon =new ImageIcon( new ImageIcon(getClass().getResource("../images/thank_you_teacher.png")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        image = new JLabel(icon);

        panel = new JPanel();
        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");
        lblTitle = new JLabel("Automated Attendance System");

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");
        chxShowPassword = new JCheckBox("Show Password");

        //Login Title
        lblTitle.setFont(new Font("Bodoni", Font.BOLD, 16));

        panel.setLayout(new GridLayout(11,1));
        panel.setBorder(new EmptyBorder(25,50,50,50));

        panel.add(lblTitle);
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
