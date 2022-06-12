import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

public class View {
    private JFrame frame;
    private JButton btn1, btn2, btn3;
    private JTextField txt1, txt2, txt3, txt4;

    private JPanel panelcenter;

    CardLayout cl = new CardLayout();

    public View(String title){
        frame = new JFrame(title);
        //frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        // Create UI elements
        btn1=new JButton("yolo");
        btn2=new JButton("Attendance");
        btn3=new JButton("Stats");

        txt1=new JTextField("baboo");
        txt2=new JTextField("Center Panel");
        txt3=new JTextField("Attendance card");
        txt4=new JTextField("Stats card");



        //Creating panels
        JPanel panelnavbar = new JPanel();
        JPanel panelbutton = new JPanel();
        JPanel panelcenter = new JPanel();
        JPanel cardattendance = new JPanel();
        JPanel cardstats = new JPanel();

        panelcenter.setLayout(cl);



        // Add UI element to panels
        panelbutton.add(btn1);
        panelbutton.add(btn2);
        panelbutton.add(btn3);
        panelnavbar.add(txt1);
        panelcenter.add(txt2);
        cardattendance.add(txt3);
        cardstats.add(txt4);

        //add colour to panels
        panelnavbar.setBackground(Color.green);
        panelbutton.setBackground(Color.red);
        cardattendance.setBackground(Color.blue);
        cardstats.setBackground(Color.black);



        panelcenter.add(cardattendance, "attendance");
        panelcenter.add(cardstats,"stats");
        cl.show(panelcenter,"stats");
       //cl.show(panelcenter,"attendance");

        //Adding panels to frame
        frame.add(panelnavbar,BorderLayout.WEST);
        frame.add(panelbutton,BorderLayout.NORTH);
        frame.add(panelcenter,BorderLayout.CENTER);




        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getBtn1() {
        return btn1;
    }

    public JTextField getTxt1() {
        return txt1;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setBtn1(JButton btn1) {
        this.btn1 = btn1;
    }

    public void setTxt1(JTextField txt1) {
        this.txt1 = txt1;
    }

    public JButton getBtn2() {
        return btn2;
    }

    public JButton getBtn3() {
        return btn3;
    }

    public JTextField getTxt2() {
        return txt2;
    }

    public JTextField getTxt3() {
        return txt3;
    }

    public JTextField getTxt4() {
        return txt4;
    }

    public JPanel getPanelCenter(){return panelcenter;}

    public void setBtn2(JButton btn2) {
        this.btn2 = btn2;
    }

    public void setBtn3(JButton btn3) {
        this.btn3 = btn3;
    }

    public void setTxt2(JTextField txt2) {
        this.txt2 = txt2;
    }

    public void setTxt3(JTextField txt3) {
        this.txt3 = txt3;
    }

    public void setTxt4(JTextField txt4) {
        this.txt4 = txt4;
    }

    public CardLayout getCl() {
        return cl;
    }

    public void setCl(CardLayout cl) {
        this.cl = cl;
    }

    public void setPanelcenter(JPanel panelcenter) {
        this.panelcenter = panelcenter;
    }

    public void setPanelAttendance(){
       cl.show(panelcenter, "attendance");
    }
}
