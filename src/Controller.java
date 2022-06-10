import Models.ModuleModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private ModuleModel model;
    private View view;

    public Controller(ModuleModel m, View v) {
        model = m;
        view = v;
        initView();
    }

    public void initView() {
        view.getTxt1().setText("Dummy");
    }

    public void initController() {
        view.getBtn1().addActionListener(e-> changeTxt());
    }

    private void changeTxt() {
        view.getTxt1().setText("Changed");
    }
}
