package Controllers;

import Models.ModuleModel;
import Models.UserModel;
import Views.AdminView;
import Views.LecturerView;
import Views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
        setcomBoModule();
    }
    public void initView() {
        view.getFrame().setSize(700,500);
        view.getCl().show(view.getPanelcenter(),"search");
        centreWindow(view.getFrame());
        view.getLblUser().setText("Welcome, " + model.getName());
        setCurrentDateTime();
    }

    public void initController() {
        view.getRdBtnSearch().addActionListener(e-> showSearch());
        view.getRdBtnStatistics().addActionListener(e->showStatistic());
        view.getrdBtnDefaulter().addActionListener(e->showDefaulter());
        view.getRdBtnAdd().addActionListener(e->showAdd());
        view.getBtnaddLecturer().addActionListener(e->addLecturer());
        view.getBtnGo().addActionListener(e->Search());
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


    void Search() {

        final String query = "SELECT id,name,course FROM student WHERE name=?";
        //final String query1 = "SELECT a.mid,a.sid,a.date,a.presence FROM attendance a INNER JOIN student s ON a.sid=s.id WHERE name=? GROUP BY a.mid DESC";
        final String query1 = "SELECT mid,sid,date,presence FROM attendance WHERE sid= 1";
        try {
            final String dbURL = "jdbc:mysql://localhost:3306/attendance";
            final String username = "root";
            final String password = "";
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            String name=view.getSearchBar().getText();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);



            ResultSet rs = stmt.executeQuery();

            Object[][] data = new Object[1][3];
            int i=0;
            while(rs.next()){
                data[i][0] = rs.getString("id");
                data[i][1] = rs.getString("name");
                data[i][2] = rs.getString("course");

                i++;
            }
            String columns[] = { "id", "name","course" };
            view.getTableInfo().setDataVector(data,columns);


            PreparedStatement stmt1 = conn.prepareStatement(query1,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //stmt1.setString(1, name);

            ResultSet rs1 = stmt1.executeQuery();


            int j=0;
            rs1.last();
            int RowCount=rs1.getRow();

            Object[][] data1 = new Object[RowCount][4];

            rs1.beforeFirst();


            while(rs1.next()){
                data1[j][0] = rs1.getInt("mid");
                data1[j][1] = rs1.getInt("sid");
                data1[j][2] = rs1.getDate("date");
                data1[j][3] = rs1.getInt("presence");

                j++;
            }


            String columns1[] = { "mid", "sid","date","presence" };
            view.getTableAttendance().setDataVector(data1,columns1);






        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Error Connecting To Database", "Alert", JOptionPane.ERROR_MESSAGE);
        }

    }

    /*public void setComboBoxModule(){
        for(ModuleModel m : model.getModuleList()){
            view.getModuleName().addElement(m.getName());
        }
        view.getComboBoxModule().setSelectedIndex(0);
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());
    }*/







}




