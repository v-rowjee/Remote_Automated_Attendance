package Components;

import javax.swing.*;
import java.awt.*;

public class AASLabel extends JLabel {
	public AASLabel(String text){
		super(text);
		setFont(new Font("Bodini",Font.BOLD,14));
		setForeground(Color.decode("#eeeeee"));
	};

	public AASLabel(){

		setForeground(Color.decode("#eeeeee"));
		setFont(new Font("Bodini",Font.BOLD,14));
	};
	AASLabel(String text, String color){
		super(text);
		setForeground(Color.decode(color));
		setFont(new Font("Bodini",Font.BOLD,14));
		setHorizontalAlignment(SwingConstants.CENTER);
	};

	public void setTextColor(String color){
		setForeground(Color.decode(color));
	}
	public void center(){
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}
