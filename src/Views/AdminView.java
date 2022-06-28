package Views;

import Components.AASButton;
import Components.AASLabel;
import Components.AASRadioButton;
import Components.AASTable;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;


public class AdminView {
    DefaultTableModel TblModelStats, TblModelDefaulter;
    private JFrame frame;
    private AASButton  btnLogout, btnMod[],BtnaddLecturer;
    private ButtonGroup radioGroup;
    private AASTable tableStats, tableDefaulter;
    private JComboBox comboBoxModule;
    private AASLabel lblUser, lblOptionSelected, lblModuleSelected, lblFor;
    private JPanel panelcenter ,panelnavbar, paneldate, panelMain,panelSearch;
    private JPanel cardViewAttendance, cardstats,cardsearch,cardDefaulter,cardAdd,cardViewLecturer,cardViewStudent, ResultPanel,AttendancePanel;
    private AASRadioButton rdBtnSearch,rdBtnDefaulter,rdBtnAdd, rdBtnViewLecturer, rdBtnViewStudent, rdBtnAttendance,rdBtnStatistics ;
    private JTextField SearchBar;

    private AASLabel lblName,lblUsrName,lblPass, clock;

    private JTextField txtName, txtUserName, txtPassword;
    private JButton btnGo;
    private AASTable tableStudentINFO,TableStudenAttendance;
    private DefaultTableModel TableInfo,TableAttendance;

    CardLayout cl = new CardLayout();


    public AdminView(String title){
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        // Create UI elements
        btnLogout = new AASButton("Logout");
        rdBtnSearch=new AASRadioButton("Search Student",false);
        rdBtnStatistics= new AASRadioButton("View Statistics", true);
        rdBtnDefaulter=new AASRadioButton("Defaulter List",false);
        rdBtnAdd=new AASRadioButton("Add Lecturer",false);
        rdBtnViewLecturer=new AASRadioButton("View Lecturers", false);
        rdBtnViewStudent =new AASRadioButton("View Students",false);
        rdBtnAttendance=new AASRadioButton("View Attendance", false);

        BtnaddLecturer=new AASButton("Add");

        radioGroup= new ButtonGroup();
        radioGroup.add(rdBtnSearch);
        radioGroup.add(rdBtnStatistics);
        radioGroup.add(rdBtnDefaulter);
        radioGroup.add(rdBtnAdd);
        radioGroup.add(rdBtnViewLecturer);
        radioGroup.add(rdBtnViewStudent);
        radioGroup.add(rdBtnAttendance);

        comboBoxModule= new JComboBox(getModule());
        comboBoxModule.setMaximumRowCount(5);


        lblUser= new AASLabel();
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblModuleSelected=new AASLabel((String)comboBoxModule.getSelectedItem());
        lblOptionSelected=new AASLabel("View Statistic");
        lblFor=new AASLabel(" for ");
        clock=new AASLabel();

        SearchBar=new JTextField(20);
        btnGo=new JButton("GO");

        //Creating panels
        panelnavbar = new JPanel();
        panelcenter = new JPanel();
        paneldate=new JPanel();
        panelMain=new JPanel();
        cardViewAttendance = new JPanel();
        cardViewLecturer=new JPanel();
        cardViewStudent=new JPanel();
        cardstats = new JPanel();
        cardsearch=new JPanel();
        cardDefaulter=new JPanel();
        cardAdd=new JPanel();
        ResultPanel=new JPanel();
        AttendancePanel=new JPanel();

        panelcenter.setLayout(cl);

        paneldate.setLayout(new GridLayout(1,6));
        paneldate.setBorder(new EmptyBorder(15,25,15,25));


        panelnavbar.setLayout(new GridLayout(10,1,10,10));
        panelnavbar.setBorder(new EmptyBorder(0,20,20,20) );

        panelMain.setLayout(new BorderLayout());
        panelMain.add(paneldate,BorderLayout.NORTH);
        panelMain.add(panelcenter,BorderLayout.CENTER);

        cardstats.setLayout(new BoxLayout(cardstats,BoxLayout.Y_AXIS));
        cardDefaulter.setLayout(new BoxLayout(cardDefaulter,BoxLayout.Y_AXIS));


        // Add UI element to panels
        paneldate.setLayout(new BoxLayout(paneldate,BoxLayout.X_AXIS));
        paneldate.add(lblOptionSelected);
        paneldate.add(lblFor);
        paneldate.add(lblModuleSelected);
        paneldate.add(Box.createGlue());
        paneldate.add(clock);


        panelnavbar.add(lblUser);
        panelnavbar.add(comboBoxModule);

        panelnavbar.add(rdBtnStatistics);
        panelnavbar.add(rdBtnDefaulter);
        panelnavbar.add(rdBtnAttendance);
        panelnavbar.add(rdBtnSearch);
        panelnavbar.add(rdBtnViewStudent);
        panelnavbar.add(rdBtnViewLecturer);
        panelnavbar.add(rdBtnAdd);
        panelnavbar.add(btnLogout);



        //add colour to panels
        panelnavbar.setBackground(Color.green);
        paneldate.setBackground(Color.red);
        cardstats.setBackground(Color.yellow);
        cardsearch.setBackground(Color.black);
        cardDefaulter.setBackground(Color.black);
        cardAdd.setBackground(Color.black);
        cardViewLecturer.setBackground(Color.PINK);
        cardViewAttendance.setBackground(Color.CYAN);
        cardViewStudent.setBackground(Color.MAGENTA);


        //adding card to panel
        panelcenter.add(cardstats,"stats");
        panelcenter.add(cardsearch,"search");
        panelcenter.add(cardDefaulter,"Defaulter list");
        panelcenter.add(cardAdd,"Add lecturer");
        panelcenter.add(cardViewAttendance,"View Attendance");
        panelcenter.add(cardViewLecturer,"View Lecturer");
        panelcenter.add(cardViewStudent,"View Student");



        //creating JTable for statistics
        TblModelStats = new DefaultTableModel();
        tableStats = new AASTable(TblModelStats){public Class getColumnClass(int column) {
            return switch (column) {
                default -> String.class;
            };
        }};

        JScrollPane paneStats = new JScrollPane(tableStats);

        paneStats.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        paneStats.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        cardstats.add(paneStats);



        //search card
        cardsearch.setLayout(new FlowLayout());
        cardsearch.add(SearchBar);
        cardsearch.add(btnGo);
        cardsearch.add(ResultPanel);
        cardsearch.add(AttendancePanel);

        TableInfo=new DefaultTableModel();
        tableStudentINFO=new AASTable(TableInfo){
        public Class getColumnClass(int column) {
            return switch (column) {

                default -> String.class;
            };
        }
    };

        JScrollPane paneSearch = new JScrollPane(tableStudentINFO);
        paneSearch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneSearch.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);


        ResultPanel.add(paneSearch);


        TableAttendance=new DefaultTableModel();
        TableStudenAttendance=new AASTable(TableAttendance){
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0 -> Integer.class;
                    case 1 -> Integer.class;
                    case 2 -> Date.class;
                    //case 3 -> String.class;
                    default->boolean.class;

                };
            }
        };

        JScrollPane paneAttendance = new JScrollPane(TableStudenAttendance);
        paneAttendance.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAttendance.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        AttendancePanel.add(paneAttendance);


       // cardsearch.setLayout(new FlowLayout());
        cardsearch.add(SearchBar);
        cardsearch.add(btnGo);
        cardsearch.add(ResultPanel);
        cardsearch.add(AttendancePanel);


        //defaulter card
        TblModelDefaulter = new DefaultTableModel();
        tableDefaulter = new AASTable(TblModelDefaulter){
            public Class getColumnClass(int column) {
                return switch (column) {
                    default -> String.class;

                };
            }
        };
        JScrollPane paneDefaulter = new JScrollPane(tableDefaulter);
        paneDefaulter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneDefaulter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cardDefaulter.add(paneDefaulter);



        //add lecturer card


        lblName =new AASLabel("name");
        lblUsrName=new AASLabel("user name");
        lblPass=new AASLabel("password");

        txtName =new JTextField(10);

        txtUserName =new JTextField(10);
        txtPassword =new JTextField(10);

        cardAdd.setLayout(new GridLayout(5,2,50,50));
        cardAdd.setBorder(new EmptyBorder(10,20,20,20) );

        cardAdd.add(lblName);
        cardAdd.add(txtName);
        cardAdd.add(lblUsrName);
        cardAdd.add(txtUserName);
        cardAdd.add(lblPass);
        cardAdd.add(txtPassword);
        cardAdd.add(BtnaddLecturer);




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

    public DefaultTableModel getTblModelStats() {
        return TblModelStats;
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

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public AASRadioButton getRdBtnAttendance() {
        return rdBtnAttendance;
    }

    public AASRadioButton getRdBtnSearch() {
        return rdBtnSearch;
    }

    public AASRadioButton getRdBtnDefaulter() {
        return rdBtnDefaulter;
    }

    public AASRadioButton getRdBtnStatistics() {
        return rdBtnStatistics;
    }

    public AASRadioButton getRdBtnAdd(){return rdBtnAdd;}

    public JPanel getPanelnavbar() {
        return panelnavbar;
    }

    public AASButton[] getBtnMod() {
        return btnMod;
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

    public AASButton getBtnaddLecturer(){return BtnaddLecturer;}

    public JTextField getTxtName(){return txtName;}

    public JTextField getTxtUserName(){return txtUserName;}

    public JTextField getTxtPassword(){return txtPassword;}

    public String[] getModule(){
        String [] array=null;
        final String query = "SELECT name AS row_count FROM  module";

        try{
            final String dbURL = "jdbc:mysql://localhost:3306/attendance";
            final String username = "root";
            final String password = "";
            Connection conn = DriverManager.getConnection(dbURL,username,password);
            PreparedStatement stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery();
            int rowCount=0;
            while(rs.next()){
               rowCount++;
               System.out.println(rowCount);
            }


            array=new String[rowCount];
            int i=0;

            rs.beforeFirst();

            while(rs.next()){

                array[i]=rs.getString(1);
                i++;

            }

        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(frame.getFrame(),"Error Connecting To Database","Alert",JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }

        return array;
    }

    public AASLabel getClock() {
        return clock;
    }

    public JTextField getSearchBar() {
        return SearchBar;
    }

    public DefaultTableModel getTableInfo() {
        return TableInfo;
    }

    public JButton getBtnGo(){return btnGo;}

    public DefaultTableModel getTableAttendance(){return TableAttendance;}

    public DefaultTableModel getTblModelDefaulter() {
        return TblModelDefaulter;
    }

    public AASRadioButton getRdBtnViewLecturer() {
        return rdBtnViewLecturer;
    }

    public AASRadioButton getRdBtnViewStudent() {
        return rdBtnViewStudent;
    }

    public AASLabel getLblFor() {
        return lblFor;
    }
}
