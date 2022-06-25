package Views;

import Components.AASButton;
import Models.ModuleModel;


import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerView {
    private JFrame frame;
    private JTable table;
    private AASButton btnLogout, btnSubmitAttendance;
    private JRadioButton rdBtnAttendance, rdBtnStatistics;
    private ButtonGroup radioGroup;
    private JComboBox comboBoxModule;
    private JLabel lblUser, lblDate, lblTime, lblOptionSelected, lblModuleSelected, lblFor, lbldummy;
    private JPanel panelcenter ,panelnavbar, paneldate, panelMain;
    private JPanel cardattendance, cardstats,cardsearch;
    private Object[][] dataa;


    final DefaultComboBoxModel moduleName = new DefaultComboBoxModel(new String[] {});
    DefaultTableModel model = new DefaultTableModel();

    DefaultTableModel model2 = new DefaultTableModel();

    CardLayout cl = new CardLayout();

    public LecturerView(String title){
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //      frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        // Create UI elements
        btnLogout = new AASButton("Logout");
        btnSubmitAttendance=new AASButton("Submit");

        rdBtnAttendance= new JRadioButton("Attendance",true);
        rdBtnStatistics= new JRadioButton("Statistics", false);

        radioGroup= new ButtonGroup();
        radioGroup.add(rdBtnAttendance);
        radioGroup.add(rdBtnStatistics);

        comboBoxModule= new JComboBox(moduleName);
        comboBoxModule.setMaximumRowCount(5);

        lblUser= new JLabel("Welcome User");
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblUser.setVerticalAlignment(SwingConstants.TOP);
        lblDate= new JLabel();
        lblTime= new JLabel();
        lblModuleSelected=new JLabel((String)comboBoxModule.getSelectedItem());
        lblOptionSelected=new JLabel("Attendance");
        lblFor=new JLabel("for");
        lbldummy=new JLabel("                            ");


        //Creating panels
        panelnavbar = new JPanel();
        panelcenter = new JPanel();
        paneldate=new JPanel();
        panelMain=new JPanel();
        cardattendance = new JPanel();
        cardstats = new JPanel();
        cardsearch=new JPanel();

        panelcenter.setLayout(cl);
        BoxLayout boxlayout1 = new BoxLayout(cardattendance,BoxLayout.Y_AXIS);
        cardattendance.setLayout(boxlayout1);
        BoxLayout boxlayout2 = new BoxLayout(cardstats,BoxLayout.Y_AXIS);
        cardstats.setLayout(boxlayout2);

        paneldate.setLayout(new FlowLayout());
      //  paneldate.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
      //  paneldate.setLayout(new GridLayout(1,6));
        paneldate.setBorder(new EmptyBorder(20,20,20,20));


        panelnavbar.setLayout(new GridLayout(5,1,10,10));
        panelnavbar.setBorder(new EmptyBorder(20,20,20,20) );

        panelMain.setLayout(new BorderLayout());
        panelMain.add(paneldate,BorderLayout.NORTH);
        panelMain.add(panelcenter,BorderLayout.CENTER);


         //Add UI element to panels
        paneldate.add(lblOptionSelected);
        paneldate.add(lblFor);
        paneldate.add(lblModuleSelected);
        paneldate.add(lbldummy);
        paneldate.add(lblDate);
        paneldate.add(lblTime);



        panelnavbar.add(lblUser);
        panelnavbar.add(comboBoxModule);
        panelnavbar.add(rdBtnAttendance);
        panelnavbar.add(rdBtnStatistics);
        panelnavbar.add(btnLogout);


        //add colour to panels
        panelnavbar.setBackground(Color.green);
        paneldate.setBackground(Color.red);
        cardattendance.setBackground(Color.blue);
        cardstats.setBackground(Color.yellow);
        cardsearch.setBackground(Color.black);


        //adding card to panel
        panelcenter.add(cardattendance, "attendance");
        panelcenter.add(cardstats,"stats");
        panelcenter.add(cardsearch,"search");


        //creating JTable for attendance
        table = new JTable(model){
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    case 1:
                        return String.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardattendance.add(pane);
        btnSubmitAttendance.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardattendance.add(btnSubmitAttendance);




        //creating JTable for statistics


        JTable table2 = new JTable(model2);

        table2.setShowGrid(true);
        table2.setShowVerticalLines(true);
        JScrollPane pane2 = new JScrollPane(table2);
        pane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardstats.add(pane2);




        //Adding panels to frame
        frame.add(panelnavbar,BorderLayout.WEST);
        frame.add(panelMain,BorderLayout.CENTER);








    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getLblOptionSelected() {
        return lblOptionSelected;
    }

    public JLabel getLblModuleSelected() {
        return lblModuleSelected;
    }


    public JPanel getPanelcenter() {
        return panelcenter;
    }

    public CardLayout getCl() {
        return cl;
    }

    public AASButton getBtnLogout() {
        return btnLogout;
    }

    public JLabel getLblDate() {
        return lblDate;
    }

    public JLabel getLblTime() {
        return lblTime;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setLblDate(JLabel lblDate) {
        this.lblDate = lblDate;
    }

    public void setLblTime(JLabel lblTime) {
        this.lblTime = lblTime;
    }

    public JRadioButton getRdBtnAttendance() {
        return rdBtnAttendance;
    }

    public JRadioButton getRdBtnStatistics() {
        return rdBtnStatistics;
    }

    public JPanel getPanelnavbar() {
        return panelnavbar;
    }


    public JLabel getLblUser() {
        return lblUser;
    }

    public JComboBox getComboBoxModule() {
        return comboBoxModule;
    }



    public void setComboBoxModule(JComboBox comboBoxModule) {
        this.comboBoxModule = comboBoxModule;
    }

    public AASButton getBtnSubmitAttendance() {
        return btnSubmitAttendance;
    }

    public JTable getTable(){
        return table;
    }

    public DefaultComboBoxModel getModuleName() {
        return moduleName;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public DefaultTableModel getModel2() {
        return model2;
    }
}
