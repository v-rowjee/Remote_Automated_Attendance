package Views;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

public class AppView {
    private JFrame frame;
    private JButton btnAttendance, btnStatistic;
    private JLabel lblNav, lblAttendance, lblStats;
    private JPanel panelcenter ,panelnavbar, panelbutton;
    private JPanel cardattendance, cardstats;

    CardLayout cl = new CardLayout();

    public AppView(String title){
        frame = new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        // Create UI elements
        btnAttendance = new JButton("Attendance");
        btnStatistic = new JButton("Stats");

        lblNav = new JLabel("navbar");
        lblAttendance = new JLabel("card attendance");
        lblStats = new JLabel("card stats");

        //Creating panels
        panelnavbar = new JPanel();
        panelbutton = new JPanel();
        panelcenter = new JPanel();
        cardattendance = new JPanel();
        cardstats = new JPanel();

        panelcenter.setLayout(cl);

        // Add UI element to panels
        panelbutton.add(btnAttendance);
        panelbutton.add(btnStatistic);

        panelnavbar.add(lblNav);

        cardattendance.add(lblAttendance);
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

    public JButton getBtnAttendance() {
        return btnAttendance;
    }

    public JButton getBtnStatistic() {
        return btnStatistic;
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
}
