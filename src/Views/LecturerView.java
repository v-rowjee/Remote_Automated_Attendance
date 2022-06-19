package Views;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LecturerView {
    private JFrame frame;
    private JButton  btnLogout, btnMod[];
    private JRadioButton rdBtnAttendance, rdBtnStatistics;
    private ButtonGroup radioGroup;
    private JComboBox comboBoxModule;
    private JLabel lblUser, lblDate, lblTime, lblOptionSelected, lblModuleSelected, lblFor;
    private JPanel panelcenter ,panelnavbar, paneldate, panelMain;
    private JPanel cardattendance, cardstats,cardsearch;

    private static String[] modName={"hjhj","sdfsd","sfs"};

    CardLayout cl = new CardLayout();

    public LecturerView(String title){
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //      frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        // Create UI elements
        btnLogout = new JButton("Logout");

        rdBtnAttendance= new JRadioButton("Attendance",true);
        rdBtnStatistics= new JRadioButton("Statistics", false);

        radioGroup= new ButtonGroup();
        radioGroup.add(rdBtnAttendance);
        radioGroup.add(rdBtnStatistics);

        comboBoxModule= new JComboBox(modName);
        comboBoxModule.setMaximumRowCount(5);

        lblUser= new JLabel("Welcome User");
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate= new JLabel();
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblTime= new JLabel();
        lblTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblModuleSelected=new JLabel((String)comboBoxModule.getSelectedItem());
        lblModuleSelected.setHorizontalAlignment(SwingConstants.LEFT);
        lblOptionSelected=new JLabel("Attendance");
        lblOptionSelected.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFor=new JLabel("for");
        lblFor.setHorizontalAlignment(SwingConstants.CENTER);


        //Creating panels
        panelnavbar = new JPanel();
        panelcenter = new JPanel();
        paneldate=new JPanel();
        panelMain=new JPanel();
        cardattendance = new JPanel();
        cardstats = new JPanel();
        cardsearch=new JPanel();

        panelcenter.setLayout(cl);

        //paneldate.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
        paneldate.setLayout(new GridLayout(1,6));
        paneldate.setBorder(new EmptyBorder(20,20,20,20));


        panelnavbar.setLayout(new GridLayout(5,1,10,10));
        panelnavbar.setBorder(new EmptyBorder(20,20,20,20) );

        panelMain.setLayout(new BorderLayout());
        panelMain.add(paneldate,BorderLayout.NORTH);
        panelMain.add(panelcenter,BorderLayout.CENTER);


        // Add UI element to panels
        paneldate.add(lblOptionSelected);
        paneldate.add(lblFor);
        paneldate.add(lblModuleSelected);
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
        String columns[] = { "Student ID", "Name","Present" };

        Object[][] data = {
                {"Buy", "IBM", false},
                {"Sell", "MicroSoft",true},
                {"Sell", "Apple",  true},
                {"Buy", "Nortel", false}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model){
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        cardattendance.add(pane);




        //creating JTable for statistics
        String columns2[] = { "Date", "Present","Absent","% Present" };

        Object[][] data2 = {
                {"11/02/2022", "15", "45","15"},
                {"12/02/2022", "23","37","38"},
                {"13/02/2022", "45",  "15","75"},
                {"14/02/2022", "33", "27","55"}
        };

        DefaultTableModel model2 = new DefaultTableModel(data2, columns2);
        JTable table2 = new JTable(model2);

        table2.setShowGrid(true);
        table2.setShowVerticalLines(true);
        JScrollPane pane2 = new JScrollPane(table2);
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

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public static String[] getModName() {
        return modName;
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

    public JButton[] getBtnMod() {
        return btnMod;
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

    public static void setModName(String[] modName) {
        LecturerView.modName = modName;
    }
}
