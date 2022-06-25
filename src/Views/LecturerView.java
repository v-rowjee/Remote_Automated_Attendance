package Views;

import Components.AASButton;
import Components.AASLabel;
import Components.AASRadioButton;


import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class LecturerView {
    private JFrame frame;
    private JTable tableAtt, tableStats;
    private AASButton btnLogout, btnSubmitAttendance;
    private AASRadioButton rdBtnAttendance, rdBtnStatistics;
    private ButtonGroup radioGroup;
    private JComboBox comboBoxModule;
    private AASLabel lblUser, lblDate, lblTime, lblOptionSelected, lblModuleSelected, lblFor, lbldummy;
    private JPanel panelcenter ,panelnavbar, paneldate, panelMain;
    private JPanel cardattendance, cardstats,cardsearch;
    private CardLayout cardLayout = new CardLayout();


    private DefaultComboBoxModel moduleName;
    private DefaultTableModel tblModelAtt;
    private DefaultTableModel tblModelStats;


    public LecturerView(String title){
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800,500));
        frame.setVisible(true);
        URL iconURL = getClass().getResource("../images/favicon.png");
        frame.setIconImage(new ImageIcon(iconURL).getImage());

        // Create UI elements
        btnLogout = new AASButton("Logout");
        btnSubmitAttendance=new AASButton("Submit");

        rdBtnAttendance= new AASRadioButton("Attendance",true);
        rdBtnStatistics= new AASRadioButton("Statistics", false);

        radioGroup= new ButtonGroup();
        radioGroup.add(rdBtnAttendance);
        radioGroup.add(rdBtnStatistics);

        moduleName = new DefaultComboBoxModel(new String[] {});
        comboBoxModule= new JComboBox(moduleName);
        comboBoxModule.setMaximumRowCount(5);

        lblUser= new AASLabel("Welcome User");
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblUser.setVerticalAlignment(SwingConstants.TOP);
        lblDate= new AASLabel();
        lblTime= new AASLabel();
        lblModuleSelected=new AASLabel((String)comboBoxModule.getSelectedItem());
        lblOptionSelected=new AASLabel("Attendance");
        lblFor=new AASLabel("for");
        lbldummy=new AASLabel("                ");


        //Creating panels
        panelnavbar = new JPanel();
        panelcenter = new JPanel();
        paneldate=new JPanel();
        panelMain=new JPanel();
        cardattendance = new JPanel();
        cardstats = new JPanel();
        cardsearch=new JPanel();

        panelcenter.setLayout(cardLayout);
        BoxLayout boxlayout1 = new BoxLayout(cardattendance,BoxLayout.Y_AXIS);
        cardattendance.setLayout(boxlayout1);
        BoxLayout boxlayout2 = new BoxLayout(cardstats,BoxLayout.Y_AXIS);
        cardstats.setLayout(boxlayout2);

        paneldate.setLayout(new FlowLayout());
        paneldate.setBorder(new EmptyBorder(15,10,15,10));


        panelnavbar.setLayout(new GridLayout(5,1,10,10));
        panelnavbar.setBorder(new EmptyBorder(20,10,20,10) );

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
        panelnavbar.setBackground(Color.decode("#00adb5"));
        paneldate.setBackground(Color.darkGray);
        cardattendance.setBackground(Color.darkGray);
        cardstats.setBackground(Color.yellow);
        cardsearch.setBackground(Color.black);


        //adding card to panel
        panelcenter.add(cardattendance, "attendance");
        panelcenter.add(cardstats,"stats");
        panelcenter.add(cardsearch,"search");


        //creating JTable for attendance
        tblModelAtt = new DefaultTableModel();
        tableAtt = new JTable(tblModelAtt){
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0 -> String.class;
                    case 1 -> String.class;
                    default -> Boolean.class;
                };
            }
        };
        tableAtt.setShowGrid(true);
        tableAtt.setShowVerticalLines(true);
        JScrollPane paneAtt = new JScrollPane(tableAtt);
        paneAtt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAtt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        cardattendance.add(paneAtt);
        btnSubmitAttendance.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardattendance.add(btnSubmitAttendance);


        //creating JTable for statistics
        tblModelStats = new DefaultTableModel();
        tableStats = new JTable(tblModelStats);
        tableStats.setShowGrid(true);
        tableStats.setShowVerticalLines(true);
        JScrollPane paneStats = new JScrollPane(tableStats);
        paneStats.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneStats.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        cardstats.add(paneStats);


        //Adding panels to frame
        frame.add(panelnavbar,BorderLayout.WEST);
        frame.add(panelMain,BorderLayout.CENTER);
    }

    public JFrame getFrame() {
        return frame;
    }

    public AASLabel getLblOptionSelected() {
        return lblOptionSelected;
    }

    public AASLabel getLblModuleSelected() {
        return lblModuleSelected;
    }


    public JPanel getPanelcenter() {
        return panelcenter;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public AASButton getBtnLogout() {
        return btnLogout;
    }

    public AASLabel getLblDate() {
        return lblDate;
    }

    public AASLabel getLblTime() {
        return lblTime;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setLblDate(AASLabel lblDate) {
        this.lblDate = lblDate;
    }

    public void setLblTime(AASLabel lblTime) {
        this.lblTime = lblTime;
    }

    public AASRadioButton getRdBtnAttendance() {
        return rdBtnAttendance;
    }

    public AASRadioButton getRdBtnStatistics() {
        return rdBtnStatistics;
    }

    public JPanel getPanelnavbar() {
        return panelnavbar;
    }


    public AASLabel getLblUser() {
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

    public JTable getTableAtt(){
        return tableAtt;
    }

    public DefaultComboBoxModel getModuleName() {
        return moduleName;
    }

    public DefaultTableModel getTblModelAtt() {
        return tblModelAtt;
    }

    public DefaultTableModel getTblModelStats() {
        return tblModelStats;
    }
}
