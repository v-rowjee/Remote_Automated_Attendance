package Controllers;

import Models.ModuleModel;
import Models.UserModel;
import Views.AppView;

public class AppController {
    private UserModel model;
    private AppView view;

    public AppController(UserModel m, AppView v) {
        model = m;
        view = v;
        initView();
    }

    public void initView() {
        view.getFrame().setSize(400,400);
        view.getCl().show(view.getPanelcenter(),"stats");
    }

    public void initController() {
        view.getBtnAttendance().addActionListener(e-> showAttendance());
        view.getBtnStatistic().addActionListener(e->showStatistic());
    }

    private void showStatistic() {
        view.getCl().show(view.getPanelcenter(),"stats");
    }

    private void showAttendance() {
        view.getCl().show(view.getPanelcenter(),"attendance");
    }



}




