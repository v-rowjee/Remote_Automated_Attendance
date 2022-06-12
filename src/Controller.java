import Models.ModuleModel;
import javax.swing.JPanel;
import java.awt.*;
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

   public void initController2() {
        view.getBtn2().addActionListener(e-> changeCard2());
    }

    private void changeTxt() {
        view.getTxt1().setText("Changed Veeed");
    }

    private void changeCard( JPanel panelcenter){
        CardLayout tempcl = new CardLayout();
        tempcl=view.getCl();
       // System.out.println("tempcl");
       view.getCl().show(panelcenter, "attendance");}

    private void changeCard1( ){
        CardLayout tempcl = new CardLayout();
        tempcl=view.getCl();
        JPanel temppanel = new JPanel();
        temppanel = view.getPanelCenter();
        tempcl.show(temppanel, "attendance");}

    private void changeCard2() {
    view.setPanelAttendance();
    }
    }




