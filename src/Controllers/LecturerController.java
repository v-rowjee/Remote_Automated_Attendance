package Controllers;

import Models.ModuleModel;
import Models.StudentModel;
import Models.UserModel;
import Views.LecturerView;
import Views.LoginView;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controllers.LoginController.centreWindow;
import static java.lang.Integer.parseInt;

public class LecturerController {
    private UserModel model;
    private LecturerView view;


    public LecturerController(UserModel m, LecturerView v) {
        model = m;
        view = v;
        initView();
        CurrentDate();

    }

    public void initView() {
        view.getFrame().setSize(700, 500);
        centreWindow(view.getFrame());
        view.getCl().show(view.getPanelcenter(), "attendance");
        view.getLblUser().setText("Welcome " + model.getName());
        setComboBoxModule();
        comBoModAction();
    }

    public void initController() {
        view.getRdBtnAttendance().addActionListener(e -> showAttendance());
        view.getRdBtnStatistics().addActionListener(e -> showStatistic());
        view.getBtnLogout().addActionListener(e -> logout());
        view.getComboBoxModule().addActionListener(e -> comBoModAction());
        view.getBtnSubmitAttendance().addActionListener(e -> findPresent());
    }

    public void setComboBoxModule(){
        for(ModuleModel m : model.getModuleList()){
            view.getModuleName().addElement(m.getName());
        }
        view.getComboBoxModule().setSelectedIndex(0);
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());
    }

    private void showStatistic() {
        view.getCl().show(view.getPanelcenter(), "stats");
        view.getLblOptionSelected().setText("Statistic");
    }

    private void showAttendance() {
        view.getCl().show(view.getPanelcenter(), "attendance");
        view.getLblOptionSelected().setText("Attendance");
    }

    private void logout() {
        view.getFrame().dispose();
        LoginView v = new LoginView("Login");
        UserModel m = new UserModel();
        LoginController c = new LoginController(m, v);
        c.initController();
    }


    public void CurrentDate() {
        Thread clock = new Thread() {
            public void run() {
                for (; ; ) {

                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    view.getLblDate().setText("Date: " + day + "/" + (month + 1) + "/" + (year));

                    String am_pm = new String();
                    int second = cal.get(Calendar.SECOND);
                    int mint = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    int timezone = cal.get(Calendar.AM_PM);
                    if (timezone == 0) {
                        am_pm = "AM";
                    } else {
                        am_pm = "PM";
                    }
                    view.getLblTime().setText("Time: " + hour + ":" + (mint) + ":" + (second) + " " + am_pm);


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

    void comBoModAction() {
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());
        for(ModuleModel m : model.getModuleList()){

                if(m.getName()==view.getComboBoxModule().getSelectedItem()) {
                    int size = m.getStudentList().size();
                    int i = 0;
                    Object[][] data = new Object[size][4];
                    for (StudentModel s : m.getStudentList()) {


                        data[i][0] = String.valueOf(s.getId());
                        data[i][1] = s.getName();
                        data[i][2] = false;
                        data[i][3] = false;
                        i++;


                    }
                    String columns[] = {"Student ID", "Name", "Present", "Absent"};
                    view.getModel().setDataVector(data, columns);
                }
                }
        }



    void findPresent() {
        //view.getTable();
        Object[][] columnData = new Object[view.getTable().getRowCount()][4];
        for (int i = 0; i < view.getTable().getRowCount(); i++) {  // Loop through the rows
            columnData[i][0] = view.getTable().getValueAt(i, 0);
            columnData[i][1] = view.getTable().getValueAt(i, 2);
            columnData[i][2] = view.getTable().getValueAt(i, 3);
            if (columnData[i][1] == columnData[i][2]) {
                JOptionPane.showMessageDialog(view.getFrame(), "Student with Id" + columnData[i][0] + " should be either present or absent", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (columnData[i][1].equals(true)) {
                columnData[i][3]="1";
            }else{
                columnData[i][3]="0";
            }
        }
        for (int x = 0; x < columnData.length; x++) {
            System.out.println(columnData[x][3]);
        }
        JOptionPane.showMessageDialog(view.getFrame(), "ALL GOOD", "Information", JOptionPane.INFORMATION_MESSAGE);

        ////////////////adding attendance to db//////////////////

        //getting selected mid
        for(ModuleModel m : model.getModuleList()){
            if (m.getName()==view.getComboBoxModule().getSelectedItem()){
                int mid = m.getId();
            }
        }

        //getting date
        LocalDate localDate = LocalDate.now();
        String date = String.valueOf(localDate);
        System.out.println(date);

        final String query2 = "INSERT INTO attendance (mid,sid,presence)VALUES (mid,?,?)";
        try{
            final String dbURL = "jdbc:mysql://localhost:3306/attendance";
            final String username = "root";
            final String password = "";
            Connection conn = DriverManager.getConnection(dbURL,username,password);
            PreparedStatement stmt = conn.prepareStatement(query2);

            for (int i = 0; i < view.getTable().getRowCount(); i++) {  // Loop through the rows
                int id = parseInt(String.valueOf(view.getTable().getValueAt(i, 0)));
                System.out.println(id);
                String presence = String.valueOf(view.getTable().getValueAt(i, 2));

                stmt.setInt(1, id);

                System.out.println(presence);

                if (presence.equals("true")){
                    stmt.setInt(2,1);
                }
                else{
                    stmt.setInt(2,0);
                }
               stmt.executeUpdate();
            }

//            if(rs == 0){
//                JOptionPane.showMessageDialog(null, "No lecturer added!", "Error!", JOptionPane.ERROR_MESSAGE);
//            }else{
//                JOptionPane.showMessageDialog(null, "lecturer added!", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
//            }

        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(view.getFrame(),"Error Connecting To Database LecturerController","Alert",JOptionPane.ERROR_MESSAGE);
        }



    }
}




