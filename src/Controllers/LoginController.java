package Controllers;

import Models.UserModel;
import Views.AdminView;
import Views.LoginView;
import Views.LecturerView;

import javax.swing.*;
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
        centreWindow(view.getFrame());
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
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
        // check empty fields
        // validate login with db
        // open new frame
        checkEmptyFields();
    }

    private void checkEmptyFields() {
        if(view.getTxtUsername().getText().trim().isEmpty() || String.valueOf(view.getTxtPassword().getPassword()).trim().isEmpty()){
            JOptionPane.showMessageDialog(view.getFrame(),"Some field(s) may be empty. Please fill all the fields","Alert",JOptionPane.WARNING_MESSAGE);
        }
        else{
            authentication();
        }
    }

    private void authentication() {
        final String dbURL = "jdbc:mysql://localhost:3306/attendance_db";
        final String username = "root";
        final String password = "";
        final String query = "SELECT *, COUNT(*) AS row_count FROM user WHERE username = ? AND password = ? ";

        try{
            Connection conn = DriverManager.getConnection(dbURL,username,password);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,view.getTxtUsername().getText());
            stmt.setString(2, String.valueOf(view.getTxtPassword().getPassword()));
            stmt.executeQuery();

            ResultSet rs = stmt.getResultSet();
            rs.next();
            int rowCount = rs.getInt("row_count");

            if(rowCount > 0){
                String userType = rs.getString("type");
                openDashboard(userType);
            }
            else{
                JOptionPane.showMessageDialog(view.getFrame(),"Incorrect Credentials","Alert",JOptionPane.ERROR_MESSAGE);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openDashboard(String type) {
        view.getFrame().dispose();
        UserModel m = new UserModel();

        // open Admin frame
        if(type.equals("admin")) {
            AdminView v = new AdminView(type);
            AdminController c = new AdminController(m, v);
            c.initController();
        }
        // open Lecturer frame
        else{
            LecturerView v = new LecturerView(type);
            LecturerController c = new LecturerController(m, v);
            c.initController();
        }

    }


}
