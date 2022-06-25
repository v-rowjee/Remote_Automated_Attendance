package Components;

import javax.swing.*;
import java.awt.*;

public class AASRadioButton extends JRadioButton {

    public AASRadioButton(String text,boolean value){
        super(text);
        setSelected(value);
        setBackground(Color.darkGray);
        setForeground(Color.decode("#aaaaaa"));
        setBorder(null);
        setBorderPainted(false);
        setFocusPainted( false );
    };
}
