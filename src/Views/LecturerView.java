package Views;

import Components.AASButton;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class LecturerView {
    private JFrame frame;
    private JTable tableAttendance, tableStats;
    private AASButton btnLogout, btnSubmitAttendance;
    private JRadioButton rdBtnAttendance, rdBtnStatistics;
    private ButtonGroup radioGroup;
    private JComboBox comboBoxModule;
    private JLabel lblUser, lblDate, lblTime, lblOptionSelected, lblModuleSelected, lblFor, lbldummy;
    private JPanel panelcenter ,panelnavbar, paneldate, panelMain;
    private JPanel cardAttendance, cardStats, cardSearch;
    private CardLayout cardLayout;

    private DefaultComboBoxModel moduleName = new DefaultComboBoxModel(new String[] {});
    private DefaultTableModel tblModelAttendance;
    private DefaultTableModel tblModelStats;


    public LecturerView(String title){
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 500));
        frame.setVisible(true);
        URL iconURL = getClass().getResource("../images/favicon.png");
        frame.setIconImage(new ImageIcon(iconURL).getImage());

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
        lbldummy=new JLabel("                          ");


        //Creating panels
        panelnavbar = new JPanel();
        panelcenter = new JPanel();
        paneldate=new JPanel();
        panelMain=new JPanel();
        cardAttendance = new JPanel();
        cardStats = new JPanel();
        cardSearch =new JPanel();

        cardLayout = new CardLayout();
        panelcenter.setLayout(cardLayout);
        BoxLayout boxlayout1 = new BoxLayout(cardAttendance,BoxLayout.Y_AXIS);
        cardAttendance.setLayout(boxlayout1);
        BoxLayout boxlayout2 = new BoxLayout(cardStats,BoxLayout.Y_AXIS);
        cardStats.setLayout(boxlayout2);

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
        cardAttendance.setBackground(Color.blue);
        cardStats.setBackground(Color.yellow);
        cardSearch.setBackground(Color.black);


        //adding card to panel
        panelcenter.add(cardAttendance, "attendance");
        panelcenter.add(cardStats,"stats");
        panelcenter.add(cardSearch,"search");


        //creating JTable for attendance
        tblModelAttendance = new DefaultTableModel();
        tableAttendance = new JTable(tblModelAttendance){
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0 -> Integer.class;
                    case 1 -> String.class;
                    default -> Boolean.class;
                };
            }
        };
        tableAttendance.setShowGrid(true);
        tableAttendance.setShowVerticalLines(true);
        tableAttendance.setAutoCreateRowSorter(true);
        JScrollPane paneAttendance = new JScrollPane(tableAttendance);
        paneAttendance.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAttendance.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        btnSubmitAttendance.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardAttendance.add(paneAttendance);
        cardAttendance.add(btnSubmitAttendance);


        //creating JTable for statistics
        tblModelStats = new DefaultTableModel();
        tableStats = new JTable(tblModelStats);
        tableStats.setShowGrid(true);
        tableStats.setShowVerticalLines(true);
        JScrollPane paneStats = new JScrollPane(tableStats);
        paneStats.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneStats.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        cardStats.add(paneStats);



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

    public JPanel getCardStats() {
        return cardStats;
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

    public JTable getTableAttendance(){
        return tableAttendance;
    }

    public DefaultComboBoxModel getModuleName() {
        return moduleName;
    }

    public DefaultTableModel getTblModelAttendance() {
        return tblModelAttendance;
    }

    public DefaultTableModel getTblModelStats() {
        return tblModelStats;
    }

}
