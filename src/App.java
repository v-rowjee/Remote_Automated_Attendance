import Controllers.LoginController;
import Models.UserModel;
import Views.LoginView;

public class App {
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        LoginView v = new LoginView("Login");

        UserModel m = new UserModel();

        LoginController c = new LoginController(m, v);
        c.initController();
    }
}
