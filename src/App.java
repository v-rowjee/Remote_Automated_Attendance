import Models.ModuleModel;

public class App {
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        View v = new View("Attendance");

        ModuleModel m = new ModuleModel();

        Controller c = new Controller(m, v);
        c.initController();
    }
}
