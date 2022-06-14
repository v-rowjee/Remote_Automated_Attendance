package Controllers;

import Models.ModuleModel;
import Models.UserModel;
import Views.LoginView;
import Views.AppView;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.*;

public class LoginController {

    private UserModel model;
    private LoginView view;

    public LoginController(UserModel m, LoginView v) {
        model = m;
        view = v;
        initView();
    }

    public void initView() {
        view.getFrame().setSize(700,400);
    }

    public void initController(){
        view.getBtnLogin().addActionListener(e->login());
        view.getChxShowPassword().addItemListener(e->showPassword(e));
    }

    private void showPassword(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            view.getTxtPassword().setEchoChar((char) 0);
        } else {
            view.getTxtPassword().setEchoChar('\u2022'); // code for dot
        }
    }

    private void login() {
        boolean allowLogin = verifyLogin();

        // check for empty fields
        if(view.getTxtUsername().getText().isEmpty() || String.valueOf(view.getTxtPassword().getPassword()).isEmpty()){
            view.getTxtUsername().setBorder(new LineBorder(Color.red));
            view.getTxtPassword().setBorder(new LineBorder(Color.red));
            view.getLblErrorMsg().setText("Please fill all fields");
            view.getLblErrorMsg().setVisible(true);
        }
        // check if username and password match
        else if(allowLogin) {
            view.getFrame().dispose();
            AppView v = new AppView("App");
            ModuleModel m = new ModuleModel();
            AppController c = new AppController(m, v);
            c.initController();
        }
        // username or password incorrect
        else{
            view.getTxtUsername().setBorder(new JTextField().getBorder());
            view.getTxtPassword().setBorder(new LineBorder(Color.red));
            view.getLblErrorMsg().setText("Incorrect Credentials");
            view.getLblErrorMsg().setVisible(true);
        }
    }

    private boolean verifyLogin() {
        final String dbURL = "jdbc:mysql://localhost:3306/addressbook";
        final String username = "root";
        final String password = "";

        String query = "SELECT COUNT(*) AS rowCount FROM record WHERE name = ? AND phone = ?";

        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,view.getTxtUsername().getText());
            stmt.setString(2,String.valueOf(view.getTxtPassword().getPassword()));
            stmt.executeQuery();

            ResultSet rs = stmt.getResultSet();
            rs.next();

            return rs.getInt("rowCount") > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
