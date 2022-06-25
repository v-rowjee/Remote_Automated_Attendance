package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AASRadioButton extends JRadioButton {

    public AASRadioButton(String text,boolean value){
        super(text);
        setSelected(value);
        setBackground(Color.decode("#00adb5"));
        setForeground(Color.decode("#f5f5f5"));
        setBorder(null);
        setBorderPainted(false);
        setFocusPainted( false );
        setHorizontalAlignment(SwingConstants.CENTER);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.decode("#00adb5").darker());
                setForeground(Color.decode("#f5f5f5"));
            }

            public void mouseExited(MouseEvent e) {
                setBackground(Color.decode("#00adb5"));
                setForeground(Color.decode("#f5f5f5"));
            }
        });
    };
}
