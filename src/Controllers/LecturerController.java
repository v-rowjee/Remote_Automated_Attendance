package Controllers;

import Models.UserModel;
import Views.LecturerView;
import Views.LoginView;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
        view.getFrame().setSize(700,500);
        view.getCl().show(view.getPanelcenter(),"attendance");
        centreWindow(view.getFrame());
    }

    public void initController() {
        view.getRdBtnAttendance().addActionListener(e-> showAttendance());
        view.getRdBtnStatistics().addActionListener(e->showStatistic());
        view.getBtnLogout().addActionListener(e-> logout());
    }

    private void showStatistic() {
        view.getCl().show(view.getPanelcenter(),"stats");
    }

    private void showAttendance() {
        view.getCl().show(view.getPanelcenter(),"attendance");
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

                   view.getLblDate().setText(day+"/"+(month+1)+"/"+(year));


                    int second=cal.get(Calendar.SECOND);
                    int mint=cal.get(Calendar.MINUTE);
                    int hour=cal.get(Calendar.HOUR);
                    view.getLblTime().setText(hour+":"+(mint)+":"+(second));


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





}




