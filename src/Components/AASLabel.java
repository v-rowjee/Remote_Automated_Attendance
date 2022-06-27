package Components;

import javax.swing.*;
import java.awt.*;

public class AASLabel extends JLabel {
	public AASLabel(String text){
		super(text);
		setFont(new Font("Bodini",Font.PLAIN,13));
		setForeground(Color.decode("#eeeeee"));
	};

	public AASLabel(){
		setForeground(Color.decode("#eeeeee"));
		setFont(new Font("Roboto",Font.PLAIN,13));
	};
	AASLabel(String text, String color){
		super(text);
		setForeground(Color.decode(color));
		setFont(new Font("Roboto",Font.PLAIN,13));
		setHorizontalAlignment(SwingConstants.CENTER);
	};

	public void setTitle(Boolean isTitle) {
		if(isTitle){
			setFont(new Font("Roboto",Font.BOLD,16));
		}
	}

	public void setTextColor(String color){
		setForeground(Color.decode(color));
	}
	public void center(){
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}
