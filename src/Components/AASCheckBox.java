package Components;

import javax.swing.*;
import java.awt.*;

public class AASCheckBox extends JCheckBox {
    public AASCheckBox(String text){
        super(text);
        setBackground(Color.darkGray);
        setForeground(Color.decode("#aaaaaa"));
        setBorder(null);
        setBorderPainted(false);
        setFocusPainted( false );
    };
}
