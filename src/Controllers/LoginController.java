package Controllers;

import Models.ModuleModel;
import Models.UserModel;
import Views.LoginView;
import Views.AppView;
import Controllers.AppController;

public class LoginController {
    private UserModel model;
    private LoginView view;

    public LoginController(UserModel m, LoginView v) {
        model = m;
        view = v;
        initView();
    }

    public void initView() {
        view.getFrame().setSize(400,400);
    }

    public void initController(){
        view.getBtnLogin().addActionListener(e->login());
    }

    private void login() {
        view.getFrame().dispose();

        AppView v = new AppView("App");
        ModuleModel m = new ModuleModel();
        AppController c = new AppController(m,v);
        c.initController();
    }

}
