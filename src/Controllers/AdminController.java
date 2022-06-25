package Controllers;

import Models.UserModel;
import Views.AdminView;
import Views.LecturerView;
import Views.LoginView;

import javax.swing.*;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controllers.LoginController.centreWindow;

public class AdminController {
    private UserModel model;
    private AdminView view;

    public AdminController(UserModel m, AdminView v) {
        model = m;
        view = v;
        initView();
        CurrentDate();
        setcomBoModule();
    }
    public void initView() {
        view.getFrame().setSize(700,500);
        view.getCl().show(view.getPanelcenter(),"search");
        centreWindow(view.getFrame());
        view.getLblUser().setText("Welcome hardcoded");
    }

    public void initController() {
        view.getRdBtnSearch().addActionListener(e-> showSearch());
        view.getRdBtnStatistics().addActionListener(e->showStatistic());
        view.getrdBtnDefaulter().addActionListener(e->showDefaulter());
        view.getRdBtnAdd().addActionListener(e->showAdd());
        view.getBtnaddLecturer().addActionListener(e->addLecturer());
        view.getBtnLogout().addActionListener(e-> logout());
    }

    private void showStatistic() {
        view.getCl().show(view.getPanelcenter(),"stats");
        view.getLblOptionSelected().setText("Statistic");
    }

    private void showSearch() {
        view.getCl().show(view.getPanelcenter(),"search");
        view.getLblOptionSelected().setText("search");
    }

    private void showDefaulter() {
        view.getCl().show(view.getPanelcenter(),"Defaulter list");
        view.getLblOptionSelected().setText("Defaulter list");
    }

    private void showAdd() {
        view.getCl().show(view.getPanelcenter(),"Add lecturer");
        view.getLblOptionSelected().setText("Add lecturer");
    }


    private void logout() {
        view.getFrame().dispose();
        LoginView v = new LoginView("Login");
        UserModel m = new UserModel();
        LoginController c = new LoginController(m, v);
        c.initController();
    }

    public void CurrentDate(){
        Thread clock=new Thread() {
            public void run() {
                for(;;)   {

                    Calendar cal=new GregorianCalendar();
                    int month=cal.get(Calendar.MONTH);
                    int year=cal.get(Calendar.YEAR);
                    int day=cal.get(Calendar.DAY_OF_MONTH);

                    view.getLblDate().setText("Date: "+day+"/"+(month+1)+"/"+(year));


                    int second=cal.get(Calendar.SECOND);
                    int mint=cal.get(Calendar.MINUTE);
                    int hour=cal.get(Calendar.HOUR);
                    view.getLblTime().setText("Time: "+hour+":"+(mint)+":"+(second));


                    try {
                        sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(LecturerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        clock.start();
    }

    private void setcomBoModule(){

        view.getComboBoxModule().addActionListener(e->comBoModAction());
    }

    void comBoModAction(){
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());

    }

    void addLecturer(){

        final String query = "INSERT INTO user (name,username,password,type)VALUES (?,?,?,'lecturer')";
        try{
            final String dbURL = "jdbc:mysql://localhost:3306/attendance";
            final String username = "root";
            final String password = "";
            Connection conn = DriverManager.getConnection(dbURL,username,password);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, view.getTxtName().getText());
            stmt.setString(2, view.getTxtUserName().getText());
            stmt.setString(3, view.getTxtPassword().getText());
             int rs = stmt.executeUpdate();

            if(rs == 0){
                JOptionPane.showMessageDialog(null, "No lecturer added!", "Error!", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "lecturer added!", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view.getFrame(),"Error Connecting To Database","Alert",JOptionPane.ERROR_MESSAGE);
        }



    }







}




