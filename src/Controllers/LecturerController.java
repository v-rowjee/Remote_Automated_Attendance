package Controllers;

import Models.ModuleModel;
import Models.UserModel;
import Views.LecturerView;
import Views.LoginView;

import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controllers.LoginController.centreWindow;

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
        view.setModName(getModulesName());
    }

    private String[] getModulesName() {
        List<String> list = new ArrayList<>();
        for (ModuleModel module : model.getModuleList()){
            list.add(module.getName());
            System.out.println(module.getName());
        }
        return list.toArray(new String[0]);
    }

    public void initController() {
        view.getRdBtnAttendance().addActionListener(e -> showAttendance());
        view.getRdBtnStatistics().addActionListener(e -> showStatistic());
        view.getBtnLogout().addActionListener(e -> logout());
        view.getComboBoxModule().addActionListener(e -> comBoModAction());
        view.getBtnSubmitAttendance().addActionListener(e -> findPresent());
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

//    private void setBtnModule(){
//        int x = 5;
//         String[] modNameArray = {"module1","module2","module3","module4","module5"};
//
//       JButton[] btnMod= view.getBtnMod();
//       btnMod = new JButton[x];
//
//
//        for (int i = 0; i<x;i++){
//
//            btnMod[i]= new JButton();
//          final String  modName=modNameArray[i];
//            btnMod[i].setText(modNameArray[i]);
//            btnMod[i].setOpaque(false);
//            btnMod[i].setContentAreaFilled(false);
//            btnMod[i].setBorderPainted(false);
//            view.getPanelnavbar().add(btnMod[i]);
//            view.getPanelnavbar().add(Box.createVerticalGlue());
//
//            btnMod[i].addActionListener(e->btnModAction(modName));
//        }
//
//
//    }
//    void btnModAction(String modName){
//        view.getLblModName().setText(modName);
//
//    }

    void comBoModAction() {
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());

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
    }
}




