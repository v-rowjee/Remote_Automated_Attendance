package Components;

import javax.swing.*;
import java.awt.*;

public class AASLabel extends JLabel {

	private Font regular = new Font("Roboto",Font.PLAIN,13);
	private Font heading = new Font("Roboto",Font.BOLD,16);
	private Color white = Color.decode("#eeeeee");
	private Color dark = Color.decode("#222222");

	public AASLabel(String text){
		super(text);
		setFont(regular);
		setForeground(white);
	}

	public AASLabel(){
		setForeground(white);
		setFont(regular);
	}

	public void setTitle(boolean isTitle) {
		if(isTitle){
			setFont(heading);
		}
	}

	public void setDark(boolean isDark){
		if(isDark){
			setForeground(dark);
		}
	}

	public void center(){
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}
