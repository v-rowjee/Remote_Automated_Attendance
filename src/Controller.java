import Models.ModuleModel;
import javax.swing.JPanel;
import java.awt.*;

public class Controller {
    private ModuleModel model;
    private View view;

    public Controller(ModuleModel m, View v) {
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
    }

    private void showAttendance() {
        view.getCl().show(view.getPanelcenter(),"attendance");
    }



}




