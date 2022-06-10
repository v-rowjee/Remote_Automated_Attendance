import javax.swing.*;

public class View {
    private JFrame frame;
    private JButton btn1;
    private JTextField txt1;

    public View(String title){
        frame = new JFrame(title);
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);




        // Create UI elements
        btn1=new JButton("yolo");
        txt1=new JTextField("baboo");


        // Add UI element to frame
        frame.add(btn1);
        frame.add(txt1);

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
}
