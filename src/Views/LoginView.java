package Views;

import javax.swing.*;

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

        btnLogin = new JButton("Login");

        frame.add(btnLogin);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }
}
