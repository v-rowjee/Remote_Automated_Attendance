package Views;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LecturerView {
    private JFrame frame;
    private JButton  btnLogout;
    private JRadioButton rdBtnAttendance, rdBtnStatistics;

    private ButtonGroup radioGroup;
    private JLabel lblNav, lblAttendance, lblStats, lblUser, lblDate, lblTime;
    private JPanel panelcenter ,panelnavbar, panelbutton;
    private JPanel cardattendance, cardstats;

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

        lblNav = new JLabel("navbar");
        lblAttendance = new JLabel("card attendance");
        lblStats = new JLabel("card stats");
        lblUser= new JLabel("Welcome User");
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate= new JLabel();
        lblTime= new JLabel();

        //Creating panels
        panelnavbar = new JPanel();
        panelbutton = new JPanel();
        panelcenter = new JPanel();
        cardattendance = new JPanel();
        cardstats = new JPanel();

        panelcenter.setLayout(cl);

        panelbutton.setLayout(new GridLayout(1,6,10,0));
        panelbutton.setBorder(new EmptyBorder(20,20,20,20));

        // Add UI element to panels
        panelbutton.add(lblUser);
        panelbutton.add(Box.createVerticalGlue());
        panelbutton.add(Box.createVerticalGlue());
        panelbutton.add(rdBtnAttendance);
        panelbutton.add(rdBtnStatistics);

        panelbutton.add(btnLogout);

        panelnavbar.add(lblNav);

        cardattendance.add(lblAttendance);
        cardattendance.add(lblDate);
        cardattendance.add(lblTime);
        cardstats.add(lblStats);


        //add colour to panels
        panelnavbar.setBackground(Color.green);
        panelbutton.setBackground(Color.red);
        cardattendance.setBackground(Color.blue);
        cardstats.setBackground(Color.black);


        //adding card to panel
        panelcenter.add(cardattendance, "attendance");
        panelcenter.add(cardstats,"stats");


        //Adding panels to frame
        frame.add(panelnavbar,BorderLayout.WEST);
        frame.add(panelbutton,BorderLayout.NORTH);
        frame.add(panelcenter,BorderLayout.CENTER);

    }

    public JFrame getFrame() {
        return frame;
    }



    public JLabel getLblNav() {
        return lblNav;
    }

    public JLabel getLblAttendance() {
        return lblAttendance;
    }

    public JLabel getLblStats() {
        return lblStats;
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
}
