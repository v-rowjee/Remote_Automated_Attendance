package Components;

import javax.swing.*;
import java.awt.*;

public class AASLabel extends JLabel {
	public AASLabel(String text){
		super(text);
		setForeground(Color.decode("#eeeeee"));
	};
	AASLabel(String text, String color){
		super(text);
		setForeground(Color.decode(color));
		setFont(new Font("Bodini",Font.BOLD,16));
		setHorizontalAlignment(SwingConstants.CENTER);
	};
	public void setTextColor(String color){
		setForeground(Color.decode(color));
	}
	public void center(){
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}
