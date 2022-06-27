package Controllers;

import Models.ModuleModel;
import Models.StudentModel;
import Models.UserModel;
import Views.LecturerView;
import Views.LoginView;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Date;

import static Controllers.LoginController.centreWindow;
import static java.lang.Integer.parseInt;

public class LecturerController {
    private UserModel model;
    private LecturerView view;


    public LecturerController(UserModel m, LecturerView v) {
        model = m;
        view = v;
        initView();
    }

    public void initView() {
        view.getFrame().setSize(700, 500);
        centreWindow(view.getFrame());
        setCurrentDateTime();

        view.getCardLayout().show(view.getPanelcenter(), "attendance");
        view.getLblUser().setText("Welcome, " + model.getName());
        setComboBoxModule();
        setTableAttendance();
        setTableStats();
    }

    public void initController() {
        view.getBtnLogout().addActionListener(e -> logout());
        view.getRdBtnAttendance().addActionListener(e -> showAttendance());
        view.getRdBtnStatistics().addActionListener(e -> showStatistic());
        view.getComboBoxModule().addActionListener(e -> setTableAttendance());
        view.getComboBoxModule().addActionListener(e -> setTableStats());
        view.getBtnSubmitAttendance().addActionListener(e -> findPresent());
    }

    private void logout() {
        view.getFrame().dispose();
        LoginView v = new LoginView("Login");
        UserModel m = new UserModel();
        LoginController c = new LoginController(m, v);
        c.initController();
    }

    public  void setCurrentDateTime(){
        tickTock();
        Timer timer = new Timer(500, e -> tickTock());
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }
    public void tickTock() {
        view.getClock().setText(DateFormat.getDateTimeInstance().format(new Date()));
        view.getClock().setFont(new Font("Roboto",Font.PLAIN,13));
    }

    public void setComboBoxModule(){
        for(ModuleModel m : model.getModuleList()){
            view.getModuleName().addElement(m.getName());
        }
        view.getComboBoxModule().setSelectedIndex(0);
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());
    }

    private void showStatistic() {
        view.getCardLayout().show(view.getPanelcenter(), "stats");
        view.getLblOptionSelected().setText("Statistic");
    }

    private void showAttendance() {
        view.getCardLayout().show(view.getPanelcenter(), "attendance");
        view.getLblOptionSelected().setText("Attendance");
    }

    //listener to combo box module + setting up table attendance
    void setTableAttendance() {
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());
        for(ModuleModel m : model.getModuleList()){

            if(m.getName()==view.getComboBoxModule().getSelectedItem()) {
                int size = m.getStudentList().size();
                int i = 0;
                Object[][] data = new Object[size][4];
                for (StudentModel s : m.getStudentList()) {


                    data[i][0] = String.valueOf(s.getId());
                    data[i][1] = s.getName();
                    data[i][2] = m.getAttendanceFor(s);
                    data[i][3] = m.getAbsenceFor(s);
                    i++;


                }
                String columns[] = {"Student ID", "Name", "Present", "Absent"};
                view.getTblModelAtt().setDataVector(data, columns);
            }
        }
    }

    void setTableStats(){

        int mid=0;
        for(ModuleModel m : model.getModuleList()){
            if (m.getName()==view.getComboBoxModule().getSelectedItem()){
                mid = m.getId();
            }
        }

        String queryAttendance ="SELECT sum(presence=1) AS present, sum(presence=0) AS absent, date FROM attendance  WHERE mid =? GROUP BY date ORDER BY date DESC";


        try{
            final String dbURL = "jdbc:mysql://localhost:3306/attendance";
            final String username = "root";
            final String password = "";
            Connection conn = DriverManager.getConnection(dbURL,username,password);
            PreparedStatement stmt = conn.prepareStatement(queryAttendance);
            stmt.setInt(1, mid);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData1 = rs.getMetaData();
            int row = metaData1.getColumnCount();
            Object[][] data2 = new Object[row][4];
            int i=0;
            while(rs.next()){

                data2[i][0] = rs.getDate("date");
                data2[i][1] = rs.getInt("present");
                data2[i][2] = rs.getInt("absent");

                double intpresent=Integer.parseInt(String.valueOf(data2[i][1]));
                double intabsent=Integer.parseInt(String.valueOf(data2[i][2]));
                double percentage= (intpresent/(intpresent + intabsent))*100;
                DecimalFormat df = new DecimalFormat("0.0");
                data2[i][3] = df.format(percentage);

                i++;
            }


            String columns2[] = { "Date", "Present","Absent","% Present" };
            view.getTblModelStats().setDataVector(data2,columns2);

        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(view.getFrame(),"Error Connecting To Database LecturerController Stats Table","Alert",JOptionPane.ERROR_MESSAGE);
        }



    }

    void findPresent() {
        //view.getTable();
        Object[][] columnData = new Object[view.getTableAtt().getRowCount()][4];
        for (int i = 0; i < view.getTableAtt().getRowCount(); i++) {  // Loop through the rows
            columnData[i][0] = view.getTableAtt().getValueAt(i, 0);
            columnData[i][1] = view.getTableAtt().getValueAt(i, 2);
            columnData[i][2] = view.getTableAtt().getValueAt(i, 3);
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
        JOptionPane.showMessageDialog(view.getFrame(), "ALL GOOD", "Information", JOptionPane.INFORMATION_MESSAGE);

        ////////////////adding attendance to db//////////////////
        int ans = JOptionPane.showConfirmDialog(null, "Attendance can only be taken once per day. Do you want to proceed?");
        if(ans==JOptionPane.YES_OPTION) {
            //getting selected mid
            int mid = 0;
            for (ModuleModel m : model.getModuleList()) {
                if (m.getName() == view.getComboBoxModule().getSelectedItem()) {
                    mid = m.getId();
                }
            }


            final String query2 = "INSERT INTO attendance (mid,sid,presence)VALUES (?,?,?)";
            try {
                final String dbURL = "jdbc:mysql://localhost:3306/attendance";
                final String username = "root";
                final String password = "";
                Connection conn = DriverManager.getConnection(dbURL, username, password);
                PreparedStatement stmt = conn.prepareStatement(query2);

                for (int i = 0; i < view.getTableAtt().getRowCount(); i++) {  // Loop through the rows
                    int id = parseInt(String.valueOf(view.getTableAtt().getValueAt(i, 0)));
                    String presence = String.valueOf(view.getTableAtt().getValueAt(i, 2));


                    stmt.setInt(1, mid);
                    stmt.setInt(2, id);


                    if (presence.equals("true")) {
                        stmt.setInt(3, 1);
                    } else {
                        stmt.setInt(3, 0);
                    }
                    stmt.executeUpdate();
                }

            } catch (SQLException e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(view.getFrame(), "Duplicate Attendance Data", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    }




