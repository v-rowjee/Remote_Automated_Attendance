package Views;

import Components.AASButton;
import Components.AASLabel;
import Components.AASRadioButton;
import Components.AASTable;
import com.toedter.calendar.JDateChooser;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;


public class AdminView {
    DefaultTableModel TblModelStats, TblModelDefaulter, TblModelReport,TblModelStudent,TblModelLecturers;
    private JFrame frame;
    private AASButton  btnLogout, btnAddLecturer, btnGenerateReport;
    private ButtonGroup radioGroup;
    private AASTable tableStats, tableDefaulter, tableReport,tableStudent, tableLecturers;
    private JComboBox comboBoxModule;
    private AASLabel lblUser, lblOptionSelected, lblModuleSelected, lblFor;
    private JPanel panelCenter, panelNavbar, panelDate, panelMain;
    private JPanel cardReport, cardViewAllAttendance, cardStats, cardSearch,cardDefaulter, cardAddLecturer,cardViewLecturer,cardViewStudent, ResultPanel,AttendancePanel,reportTopPanel,reportBottomPanel;
    private AASRadioButton rdBtnSearch,rdBtnDefaulter,rdBtnAdd, rdBtnViewLecturer, rdBtnViewStudent, rdBtnReportGeneration,rdBtnStatistics, rdBtnAllAttendance ;
    private JTextField SearchBar;

    private AASLabel lblName, lblUserName,lblPass, clock, lblFrom, lblTo;

    private JTextField txtName, txtUserName, txtPassword;
    private AASButton btnGo;
    private AASTable tableStudentINFO,TableStudenAttendance;
    private DefaultTableModel TableInfo,TableAttendance;

    private JDateChooser dateChooserFrom, dateChooserTo;

    UtilDateModel modelDate;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker ;
    CardLayout cardLayout = new CardLayout();


    public AdminView(String title){
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        // Create UI elements
        btnLogout = new AASButton("Logout");
        btnLogout.setDark(true);
        rdBtnSearch=new AASRadioButton("Search Student",false);
        rdBtnStatistics= new AASRadioButton("View Statistics", true);
        rdBtnDefaulter=new AASRadioButton(" View Defaulter List",false);
        rdBtnAllAttendance=new AASRadioButton("View All Attendance",false);
        rdBtnAdd=new AASRadioButton("Add Lecturer",false);
        rdBtnViewLecturer=new AASRadioButton("View Lecturers", false);
        rdBtnViewStudent =new AASRadioButton("View Students",false);
        rdBtnReportGeneration=new AASRadioButton("View Report", false);

        btnAddLecturer =new AASButton("Add");

        radioGroup= new ButtonGroup();
        radioGroup.add(rdBtnSearch);
        radioGroup.add(rdBtnStatistics);
        radioGroup.add(rdBtnDefaulter);
        radioGroup.add(rdBtnAdd);
        radioGroup.add(rdBtnViewLecturer);
        radioGroup.add(rdBtnViewStudent);
        radioGroup.add(rdBtnAllAttendance);
        radioGroup.add(rdBtnReportGeneration);

        comboBoxModule= new JComboBox(getModule());
        comboBoxModule.setMaximumRowCount(5);


        lblUser= new AASLabel();
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblModuleSelected=new AASLabel((String)comboBoxModule.getSelectedItem());
        lblOptionSelected=new AASLabel("View Statistic");
        lblFor=new AASLabel(" for ");
        clock=new AASLabel();
        lblFrom=new AASLabel("From: ");
        lblFrom.setForeground(Color.black);
        lblTo=new AASLabel("To :");
        lblTo.setForeground(Color.black);


        SearchBar=new JTextField(20);

        btnGo=new AASButton("GO");
        btnGo.setPreferredSize(new Dimension(50,20));
        btnGenerateReport=new AASButton("Generate Report");
        btnGenerateReport.setPreferredSize(new Dimension(120,25));

        dateChooserFrom= new JDateChooser();
        dateChooserFrom.setPreferredSize(new Dimension(100,25));
        dateChooserTo=new JDateChooser();
        dateChooserTo.setPreferredSize(new Dimension(100,25));

        //Creating panels
        panelNavbar = new JPanel();
        panelCenter = new JPanel();
        panelDate =new JPanel();
        panelMain=new JPanel();
        cardViewAllAttendance = new JPanel();
        cardViewLecturer=new JPanel();
        cardViewStudent=new JPanel();
        cardStats = new JPanel();
        cardSearch =new JPanel();
        cardDefaulter=new JPanel();
        cardAddLecturer =new JPanel();
        ResultPanel=new JPanel();
        AttendancePanel=new JPanel();
        cardReport=new JPanel();
        reportBottomPanel=new JPanel();
        reportTopPanel=new JPanel();

        panelCenter.setLayout(cardLayout);

        panelDate.setLayout(new GridLayout(1,6));
        panelDate.setBorder(new EmptyBorder(15,25,15,25));


        panelNavbar.setLayout(new GridLayout(14,1,0,10));
        panelNavbar.setBorder(new EmptyBorder(10,20,10,20) );

        panelMain.setLayout(new BorderLayout());
        panelMain.add(panelDate,BorderLayout.NORTH);
        panelMain.add(panelCenter,BorderLayout.CENTER);

        cardStats.setLayout(new BoxLayout(cardStats,BoxLayout.Y_AXIS));
        cardDefaulter.setLayout(new BoxLayout(cardDefaulter,BoxLayout.Y_AXIS));
        cardViewStudent.setLayout(new BoxLayout(cardViewStudent,BoxLayout.Y_AXIS));
        cardViewLecturer.setLayout(new BoxLayout(cardViewLecturer,BoxLayout.Y_AXIS));


        // Add UI element to panels
        panelDate.setLayout(new BoxLayout(panelDate,BoxLayout.X_AXIS));
        panelDate.add(lblOptionSelected);
        panelDate.add(lblFor);
        panelDate.add(lblModuleSelected);
        panelDate.add(Box.createGlue());
        panelDate.add(clock);


        panelNavbar.add(lblUser);
        panelNavbar.add(Box.createGlue());
        panelNavbar.add(comboBoxModule);
        panelNavbar.add(rdBtnStatistics);
        panelNavbar.add(rdBtnDefaulter);
        panelNavbar.add(rdBtnReportGeneration);
        panelNavbar.add(Box.createGlue());
        panelNavbar.add(rdBtnAllAttendance);
        panelNavbar.add(rdBtnSearch);
        panelNavbar.add(rdBtnViewStudent);
        panelNavbar.add(rdBtnViewLecturer);
        panelNavbar.add(rdBtnAdd);
        panelNavbar.add(Box.createGlue());
        panelNavbar.add(btnLogout);


        //Card Report
        cardReport.setLayout(new BoxLayout(cardReport,BoxLayout.Y_AXIS));
        reportTopPanel.setBorder(new EmptyBorder(10,0,10,0));
        reportTopPanel.setBackground(Color.white);
        reportTopPanel.add(lblFrom);
        reportTopPanel.add(dateChooserFrom);
        reportTopPanel.add(lblTo);
        reportTopPanel.add(dateChooserTo);
        reportTopPanel.add(Box.createHorizontalStrut(50));
        reportTopPanel.add(btnGenerateReport);


        //add colour to panels
        panelNavbar.setBackground(Color.decode("#00adb5"));
        panelDate.setBackground(Color.darkGray);
        cardStats.setBackground(Color.yellow);
        cardSearch.setBackground(Color.darkGray);
        cardDefaulter.setBackground(Color.darkGray);
        cardAddLecturer.setBackground(Color.darkGray);
        cardViewLecturer.setBackground(Color.darkGray);
        cardViewAllAttendance.setBackground(Color.CYAN);
        cardViewStudent.setBackground(Color.MAGENTA);
        cardReport.setBackground(Color.darkGray);


        //adding card to panel
        panelCenter.add(cardStats,"stats");
        panelCenter.add(cardSearch,"search");
        panelCenter.add(cardDefaulter,"Defaulter list");
        panelCenter.add(cardAddLecturer,"Add lecturer");
        panelCenter.add(cardViewAllAttendance,"View All Attendance");
        panelCenter.add(cardViewLecturer,"View Lecturer");
        panelCenter.add(cardViewStudent,"View Student");
        panelCenter.add(cardReport,"View Report");



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

        cardStats.add(paneStats);



        //creating JTable for report generation
        TblModelReport = new DefaultTableModel();
        tableReport = new AASTable(TblModelReport){public Class getColumnClass(int column) {
            return switch (column) {
                default -> String.class;
            };
        }};

        JScrollPane paneReport = new JScrollPane(tableReport);

        paneReport.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        paneReport.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);



        cardReport.add(reportTopPanel);
        cardReport.add(paneReport);
        reportBottomPanel.setBackground(Color.darkGray);
        reportBottomPanel.setPreferredSize(new Dimension((int) ( frame.getWidth()*0.75), 50));
//        cardReport.add(reportBottomPanel);


//view student card
        TblModelStudent = new DefaultTableModel();
        tableStudent = new AASTable(TblModelStudent){
            public Class getColumnClass(int column) {
                return switch (column) {
                    default -> String.class;

                };
            }
        };
        JScrollPane paneStudent = new JScrollPane(tableStudent);
        paneStudent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneStudent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cardViewStudent.add(paneStudent);



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
                    case 0 -> String.class;
                    case 1 -> String.class;
                    case 2 -> Date.class;
                    default-> String.class;

                };
            }
        };

        JScrollPane paneAttendance = new JScrollPane(TableStudenAttendance);
        paneAttendance.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAttendance.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        AttendancePanel.add(paneAttendance);


        cardSearch.setLayout(new BorderLayout());
        JPanel panelSearch = new JPanel();
        panelSearch.add(SearchBar);
        panelSearch.add(btnGo);
        JPanel panelSearchTables = new JPanel();
        panelSearchTables.setLayout(new BoxLayout(panelSearchTables,BoxLayout.X_AXIS));
        panelSearchTables.add(ResultPanel);
        panelSearchTables.add(AttendancePanel);
        JScrollPane searchTablesScroll = new JScrollPane(panelSearchTables,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        cardSearch.add(panelSearch,BorderLayout.NORTH);
        cardSearch.add(searchTablesScroll,BorderLayout.CENTER);

        TblModelLecturers = new DefaultTableModel();
        tableLecturers = new AASTable(TblModelLecturers){
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0 -> String.class;
                    case 1 -> String.class;
                    case 2 -> String.class;
                    default->String.class;
                };
            }
        };
        JScrollPane PaneLecturer = new JScrollPane(tableLecturers);
        PaneLecturer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        PaneLecturer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cardViewLecturer.add(PaneLecturer);




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


        lblName =new AASLabel("Name");
        lblUserName =new AASLabel("Username");
        lblPass=new AASLabel("Password");

        txtName =new JTextField(10);

        txtUserName =new JTextField(10);
        txtPassword =new JTextField(10);

        cardAddLecturer.setLayout(new GridLayout(5,2,50,50));
        cardAddLecturer.setBorder(new EmptyBorder(50,20,20,20) );

        cardAddLecturer.add(lblName);
        cardAddLecturer.add(txtName);
        cardAddLecturer.add(lblUserName);
        cardAddLecturer.add(txtUserName);
        cardAddLecturer.add(lblPass);
        cardAddLecturer.add(txtPassword);
        cardAddLecturer.add(Box.createGlue());
        cardAddLecturer.add(btnAddLecturer);




        //Adding panels to frame
        frame.add(panelNavbar,BorderLayout.WEST);
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

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }

    public UtilDateModel getModelDate() {
        return modelDate;
    }

    public DefaultTableModel getTblModelReport() {
        return TblModelReport;
    }

    public DefaultTableModel getTblModelStats() {
        return TblModelStats;
    }
    public JPanel getPanelCenter() {
        return panelCenter;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public AASButton getBtnLogout() {
        return btnLogout;
    }

    public AASRadioButton getRdBtnReportGeneration() {
        return rdBtnReportGeneration;
    }

    public AASRadioButton getRdBtnSearch() {
        return rdBtnSearch;
    }

    public DefaultTableModel getTblModelLecturers() {
        return TblModelLecturers;
    }

    public AASRadioButton getRdBtnDefaulter() {
        return rdBtnDefaulter;
    }

    public AASRadioButton getRdBtnStatistics() {
        return rdBtnStatistics;
    }

    public AASRadioButton getRdBtnAdd(){return rdBtnAdd;}

    public AASLabel getLblUser() {
        return lblUser;
    }

    public JComboBox getComboBoxModule() {
        return comboBoxModule;
    }

    public void setComboBoxModule(JComboBox comboBoxModule) {
        this.comboBoxModule = comboBoxModule;
    }

    public AASButton getBtnAddLecturer(){return btnAddLecturer;}

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

    public JDateChooser getDateChooserTo() {
        return dateChooserTo;
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

    public AASRadioButton getRdBtnAllAttendance() {
        return rdBtnAllAttendance;
    }

    public AASRadioButton getRdBtnViewStudent() {
        return rdBtnViewStudent;
    }

    public DefaultTableModel getTblModelStudent() {
        return TblModelStudent;
    }

    public AASLabel getLblFor() {
        return lblFor;
    }

    public JDateChooser getDateChooserFrom() {
        return dateChooserFrom;
    }

    public AASButton getBtnGenerateReport() {
        return btnGenerateReport;
    }

}
