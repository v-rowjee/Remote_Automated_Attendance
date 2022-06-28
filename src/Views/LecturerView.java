package Views;

import Components.AASButton;
import Components.AASLabel;
import Components.AASRadioButton;
import Components.AASTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class LecturerView {
    private JFrame frame;
    private AASTable tableAtt, tableStats;
    private AASButton btnLogout, btnSubmitAttendance, btnClearAttendance;
    private AASRadioButton rdBtnAttendance, rdBtnStatistics;
    private ButtonGroup radioGroup;
    private JComboBox comboBoxModule;
    private AASLabel lblUser, lblOptionSelected, lblModuleSelected, lblFor, clock;
    private JPanel panelcenter ,panelnavbar, paneldate, panelMain;
    private JPanel cardattendance, cardstats,cardsearch;
    private JPanel panelAttBottom,panelStatsBottom;
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
        btnLogout.setDark(true);
        btnSubmitAttendance=new AASButton("Submit");
        btnClearAttendance = new AASButton("Clear");

        rdBtnAttendance= new AASRadioButton("Attendance",true);
        rdBtnStatistics= new AASRadioButton("Statistics", false);

        radioGroup= new ButtonGroup();
        radioGroup.add(rdBtnAttendance);
        radioGroup.add(rdBtnStatistics);

        moduleName = new DefaultComboBoxModel(new String[] {});
        comboBoxModule= new JComboBox(moduleName);
        comboBoxModule.setMaximumRowCount(5);
        lblUser= new AASLabel();
        lblUser.setTitle(true);
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblModuleSelected=new AASLabel((String)comboBoxModule.getSelectedItem());
        lblOptionSelected=new AASLabel("Attendance");
        lblFor = new AASLabel(" for ");
        clock = new AASLabel();


        //Creating panels
        panelnavbar = new JPanel();
        panelcenter = new JPanel();
        paneldate=new JPanel();
        panelMain=new JPanel();
        cardattendance = new JPanel();
        cardstats = new JPanel();
        cardsearch=new JPanel();
        panelAttBottom = new JPanel();
        panelStatsBottom=new JPanel();

        panelcenter.setLayout(cardLayout);
        cardattendance.setLayout(new BorderLayout());
        cardstats.setLayout(new BoxLayout(cardstats,BoxLayout.Y_AXIS));

        paneldate.setBorder(new EmptyBorder(15,25,15,25));


        panelMain.setLayout(new BorderLayout());
        panelMain.add(paneldate,BorderLayout.NORTH);
        panelMain.add(panelcenter,BorderLayout.CENTER);


         //Add UI element to panels
        paneldate.setLayout(new BoxLayout(paneldate,BoxLayout.X_AXIS));
        paneldate.add(lblOptionSelected);
        paneldate.add(lblFor);
        paneldate.add(lblModuleSelected);
        paneldate.add(Box.createGlue());
        paneldate.add(clock);


        panelnavbar.setPreferredSize(new Dimension(frame.getWidth()/4,frame.getHeight()));
        panelnavbar.setLayout(new BorderLayout());
        panelnavbar.setBorder(new EmptyBorder(0,20,5,20) );

        JPanel panelNavbarCenter = new JPanel();
        panelNavbarCenter.setLayout(new GridLayout(3,1,0,40));
        panelNavbarCenter.setBorder(new EmptyBorder(60,0,100,0));
        panelNavbarCenter.setBackground(Color.decode("#00adb5"));
        panelNavbarCenter.add(comboBoxModule);
        panelNavbarCenter.add(rdBtnAttendance);
        panelNavbarCenter.add(rdBtnStatistics);

        lblUser.setPreferredSize(new Dimension(frame.getWidth()/4,frame.getHeight()/10));
        btnLogout.setPreferredSize(new Dimension(frame.getWidth()/4,frame.getHeight()/10-10));
        panelnavbar.add(lblUser,BorderLayout.NORTH);
        panelnavbar.add(panelNavbarCenter,BorderLayout.CENTER);
        panelnavbar.add(btnLogout,BorderLayout.SOUTH);



        //add colour to panels
        panelnavbar.setBackground(Color.decode("#00adb5"));
        paneldate.setBackground(Color.darkGray);
        cardattendance.setBackground(Color.darkGray);
        cardstats.setBackground(Color.darkGray);
        cardsearch.setBackground(Color.black);


        //adding card to panel
        panelcenter.add(cardattendance, "attendance");
        panelcenter.add(cardstats,"stats");
        panelcenter.add(cardsearch,"search");


        //creating AASTable for attendance
        tblModelAtt = new DefaultTableModel();
        tableAtt = new AASTable(tblModelAtt){
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0 -> String.class;
                    case 1 -> String.class;
                    default -> Boolean.class;
                };
            }
        };
        JScrollPane paneAtt = new JScrollPane(tableAtt);
        paneAtt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAtt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        btnSubmitAttendance.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAttBottom.setLayout(new FlowLayout(FlowLayout.TRAILING));
        panelAttBottom.setBackground(Color.darkGray);
        panelAttBottom.add(btnClearAttendance);
        panelAttBottom.add(btnSubmitAttendance);

        cardattendance.add(paneAtt,BorderLayout.CENTER);
        cardattendance.add(panelAttBottom,BorderLayout.SOUTH);


        //creating AASTable for statistics
        tblModelStats = new DefaultTableModel();
        tableStats = new AASTable(tblModelStats){public Class getColumnClass(int column) {
            return switch (column) {
                default -> String.class;
            };
        }};

        JScrollPane paneStats = new JScrollPane(tableStats);

        paneStats.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        paneStats.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        cardstats.add(paneStats);
        panelStatsBottom.setBackground(Color.darkGray);
        panelStatsBottom.setPreferredSize(new Dimension((int) ( frame.getWidth()*0.75), (int )(frame.getHeight()*0.12)));
        cardstats.add(panelStatsBottom);


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

    public AASLabel getClock() {
        return clock;
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

    public AASButton getBtnClearAttendance() {
        return btnClearAttendance;
    }

    public AASRadioButton getRdBtnAttendance() {
        return rdBtnAttendance;
    }

    public AASRadioButton getRdBtnStatistics() {
        return rdBtnStatistics;
    }

    public AASLabel getLblUser() {
        return lblUser;
    }

    public JComboBox getComboBoxModule() {
        return comboBoxModule;
    }

    public AASButton getBtnSubmitAttendance() {
        return btnSubmitAttendance;
    }

    public AASTable getTableAtt(){
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
