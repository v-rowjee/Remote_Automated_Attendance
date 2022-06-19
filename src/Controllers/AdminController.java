package Controllers;

import Models.UserModel;
import Views.AdminView;
import Views.LecturerView;
import Views.LoginView;

import javax.swing.*;
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


    private void logout() {
        view.getFrame().dispose();
        LoginView v = new LoginView("Login");
        UserModel m = new UserModel();
        LoginController c = new LoginController(m, v);
        c.initController();
    }

    public void CurrentDate(){
        Thread clock=new Thread()   {
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

    private void setcomBoModule(){


//        JComboBox comboBoxModule=view.getComboBoxModule();
//        comboBoxModule= new JComboBox(modNameArray);
//        comboBoxModule.setMaximumRowCount(x);
//        view.getPanelnavbar().add(comboBoxModule);
//

//        for (int i = 0; i<x;i++){
//
//            btnMod[i]= new JButton();
//            final String  modName=modNameArray[i];
//            btnMod[i].setText(modNameArray[i]);
//            btnMod[i].setOpaque(false);
//            btnMod[i].setContentAreaFilled(false);
//            btnMod[i].setBorderPainted(false);
//            view.getPanelnavbar().add(btnMod[i]);
//            view.getPanelnavbar().add(Box.createVerticalGlue());
//
        view.getComboBoxModule().addActionListener(e->comBoModAction());
//        }


    }
    void comBoModAction(){
        view.getLblModuleSelected().setText((String) view.getComboBoxModule().getSelectedItem());

    }





}




