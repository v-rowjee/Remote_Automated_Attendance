package Controllers;

import Database.Database;
import Models.UserModel;
import Views.AdminView;
import Views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;

import static Controllers.LoginController.centreWindow;

public class AdminController {
    private UserModel model;
    private AdminView view;

    public AdminController(UserModel m, AdminView v) {
        model = m;
        view = v;
        initView();
        setcomBoModule();
    }
    public void initView() {
        view.getFrame().setSize(700,500);
        view.getCl().show(view.getPanelcenter(),"Statistic");
        centreWindow(view.getFrame());
        view.getLblUser().setText("Welcome, " + model.getName());
        setCurrentDateTime();
        setTableStats();


    }

    public void initController() {
        view.getRdBtnSearch().addActionListener(e-> showSearch());
        view.getRdBtnStatistics().addActionListener(e->showStatistic());
        view.getRdBtnDefaulter().addActionListener(e->showDefaulter());
        view.getRdBtnViewLecturer().addActionListener(e->showLecturer());
        view.getRdBtnReportGeneration().addActionListener(e->generateReport());
        view.getRdBtnAllAttendance().addActionListener(e->viewAllAttendance());
        view.getRdBtnViewStudent().addActionListener(e->showStudent());
        view.getRdBtnAdd().addActionListener(e->showAdd());
        view.getBtnaddLecturer().addActionListener(e->addLecturer());
        view.getBtnGo().addActionListener(e->Search());
        view.getBtnLogout().addActionListener(e-> logout());
        view.getComboBoxModule().addActionListener(e -> comboBoxAction());

    }

    private void comboBoxAction(){
        setTableDefaulter();
        setTableStats();
        setTableReport();
    }

    private void showStatistic() {
        view.getCl().show(view.getPanelcenter(),"stats");
        view.getLblOptionSelected().setText("Statistic");
        view.getLblFor().setVisible(true);
        view.getLblModuleSelected().setVisible(true);
        setTableStats();
    }
    private void viewAllAttendance(){
        view.getCl().show(view.getPanelcenter(),"View All Attendance");
        view.getLblOptionSelected().setText("View All Attendance");
        view.getLblModuleSelected().setVisible(false);
        view.getLblFor().setVisible(false);
    }


    private void showLecturer(){
        view.getCl().show(view.getPanelcenter(),"View Lecturer");
        view.getLblOptionSelected().setText("View Lecturers");
        view.getLblModuleSelected().setVisible(false);
        view.getLblFor().setVisible(false);
    }

    private void showStudent(){
        view.getCl().show(view.getPanelcenter(),"View Student");
        view.getLblOptionSelected().setText("View Students");
        view.getLblModuleSelected().setVisible(false);
        view.getLblFor().setVisible(false);
    }

    private void generateReport(){
        view.getCl().show(view.getPanelcenter(),"View Report");
        view.getLblOptionSelected().setText("Report");
        view.getLblFor().setVisible(true);
        view.getLblModuleSelected().setVisible(true);
        setTableReport();
    }

    private void showSearch() {
        view.getCl().show(view.getPanelcenter(),"search");
        view.getLblOptionSelected().setText("Search Student");
        view.getLblModuleSelected().setVisible(false);
        view.getLblFor().setVisible(false);
    }

    private void showDefaulter() {
        view.getCl().show(view.getPanelcenter(),"Defaulter list");
        view.getLblOptionSelected().setText("Defaulter List");
        view.getLblFor().setVisible(true);
        view.getLblModuleSelected().setVisible(true);
        setTableDefaulter();
    }

    private void showAdd() {
        view.getCl().show(view.getPanelcenter(),"Add lecturer");
        view.getLblOptionSelected().setText("Add Lecturer");
        view.getLblModuleSelected().setVisible(false);
        view.getLblFor().setVisible(false);
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
        view.getClock().setText(DateFormat.getDateTimeInstance().format(new java.util.Date()));
        view.getClock().setFont(new Font("Roboto",Font.PLAIN,13));
    }

    private void setcomBoModule(){

        view.getComboBoxModule().addActionListener(e->comBoModAction());
    }

    void comBoModAction(){
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());

    }

    void addLecturer() {

        final String query = "INSERT INTO user (name,username,password,type)VALUES (?,?,?,'lecturer')";
        try {
            final String dbURL = "jdbc:mysql://localhost:3306/attendance";
            final String username = "root";
            final String password = "";
            Connection conn = DriverManager.getConnection(dbURL, username, password);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, view.getTxtName().getText());
            stmt.setString(2, view.getTxtUserName().getText());
            stmt.setString(3, view.getTxtPassword().getText());
            int rs = stmt.executeUpdate();

            if (rs == 0) {
                JOptionPane.showMessageDialog(null, "No lecturer added!", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "lecturer added!", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Error Connecting To Database", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }

        void setTableStats(){
            int mid=getMid((String) view.getComboBoxModule().getSelectedItem());

            String queryAttendance ="SELECT sum(presence=1) AS present, sum(presence=0) AS absent, date FROM attendance  WHERE mid =? GROUP BY date ORDER BY date DESC";

            try{
                Connection conn = Database.getConnection();

                PreparedStatement stmt = conn.prepareStatement(queryAttendance,ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                stmt.setInt(1, mid);
                ResultSet rs = stmt.executeQuery();

                int rowCount =0;
                rs.last();
                rowCount=rs.getRow();
                rs.beforeFirst();

                Object[][] data2 = new Object[rowCount][4];
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
                e.getStackTrace();
                JOptionPane.showMessageDialog(view.getFrame(),"Error Connecting To Database Admin Controller Stats Table","Alert",JOptionPane.ERROR_MESSAGE);
            }
        }

    void setTableReport(){
        int mid=getMid((String) view.getComboBoxModule().getSelectedItem());

        String queryReport ="SELECT  date, sid, presence, name FROM attendance a INNER JOIN student s ON a.sid=s.id  WHERE mid =?  ORDER BY date DESC";

        try{
            Connection conn = Database.getConnection();

            PreparedStatement stmt = conn.prepareStatement(queryReport,ResultSet.TYPE_SCROLL_SENSITIVE,     ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, mid);
            ResultSet rs = stmt.executeQuery();


            int rowCount =0;
            rs.last();
            rowCount=rs.getRow();
            rs.beforeFirst();

            Object[][] data2 = new Object[rowCount][4];
            int i=0;
            while(rs.next()){

                data2[i][0] = rs.getDate("date");
                data2[i][1] = rs.getInt("sid");
                data2[i][2] = rs.getString("name");
                System.out.println("haha");
                int x  = rs.getInt("presence");
                    if (x ==0){
                        data2[i][3] = "Absent";
                    }else {
                        data2[i][3]="Present";
                    }

                i++;
            }

            String columns2[] = { "Date", "Student Id","Student Name","Attendance" };
            view.getTblModelReport().setDataVector(data2,columns2);

        } catch (SQLException e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(view.getFrame(),"Error Connecting To Database Admin Controller Report Table","Alert",JOptionPane.ERROR_MESSAGE);
        }
    }


    void Search() {

        final String query = "SELECT id,name,course FROM student WHERE name=?";
        //final String query1 = "SELECT a.mid,a.sid,a.date,a.presence FROM attendance a INNER JOIN student s ON a.sid=s.id WHERE name=? GROUP BY a.mid DESC";
        final String query1 = "SELECT mid,sid,date,presence FROM attendance WHERE sid= 1";
        String name;
        try {
            final String dbURL = "jdbc:mysql://localhost:3306/attendance";
            final String username = "root";
            final String password = "";
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            name = view.getSearchBar().getText();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);


            ResultSet rs = stmt.executeQuery();

            Object[][] data = new Object[1][3];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("id");
                data[i][1] = rs.getString("name");
                data[i][2] = rs.getString("course");

                i++;
            }
            String columns[] = {"id", "name", "course"};
            view.getTableInfo().setDataVector(data, columns);


            PreparedStatement stmt1 = conn.prepareStatement(query1, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //stmt1.setString(1, name);

            ResultSet rs1 = stmt1.executeQuery();


            int j = 0;
            rs1.last();
            int RowCount = rs1.getRow();

            Object[][] data1 = new Object[RowCount][4];

            rs1.beforeFirst();


            while (rs1.next()) {
                data1[j][0] = rs1.getInt("mid");
                data1[j][1] = rs1.getInt("sid");
                data1[j][2] = rs1.getDate("date");
                data1[j][3] = rs1.getInt("presence");

                j++;
            }


            String columns1[] = {"mid", "sid", "date", "presence"};
            view.getTableAttendance().setDataVector(data1, columns1);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Error Connecting To Database", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }

        public int getMid (String name){
            String modname = name;
            String queryMname = "Select id FROM module WHERE name=? ";
            try {
                Connection conn = Database.getConnection();

                PreparedStatement stmt = conn.prepareStatement(queryMname, ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                stmt.setString(1, modname);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    return rs.getInt("id");
                }

            } catch (SQLException e) {
                e.getStackTrace();
                JOptionPane.showMessageDialog(view.getFrame(), "Error Connecting To Database Admin Controller Finding mid", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }

    void setTableDefaulter(){
        int mid=getMid((String) view.getComboBoxModule().getSelectedItem());
        //String queryDefaulter ="SELECT  sum(presence=0) AS absent, sid FROM attendance ,date WHERE mid =? GROUP BY sid ORDER BY date DESC";
        String queryDefaulter ="SELECT  sum(presence=0) AS absent, sid,date, name FROM attendance a INNER JOIN student s ON a.sid=s.id WHERE mid =? GROUP BY sid ORDER BY date DESC";
        //final String query1 = "SELECT a.mid,a.sid,a.date,a.presence FROM attendance a INNER JOIN student s ON a.sid=s.id WHERE name=? GROUP BY a.mid DESC";
        try{
            Connection conn = Database.getConnection();

            PreparedStatement stmt = conn.prepareStatement(queryDefaulter,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, mid);
            ResultSet rs = stmt.executeQuery();

            int rowCount =0;
            rs.last();
            rowCount=rs.getRow();
            rs.beforeFirst();

            Object[][] data = new Object[rowCount][3];
            int i=0;
            while(rs.next()){
                int absenceCount = rs.getInt("absent");
                if (absenceCount >=3){

                    data[i][0] = rs.getInt("sid");
                    data[i][1]=rs.getString("name");
                    data[i][2] = rs.getInt("absent");

                    i++;
                }
            }

            String columns[] = { "Student Id","Student Name","Num of Absence" };
            view.getTblModelDefaulter().setDataVector(data,columns);

        } catch (SQLException e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(view.getFrame(),"Error Connecting To Database Admin Controller Defaulter Table","Alert",JOptionPane.ERROR_MESSAGE);
        }
    }

    }




