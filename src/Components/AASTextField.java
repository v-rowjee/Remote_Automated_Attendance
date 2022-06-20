package Components;
import javax.swing.*;
import java.awt.*;

public class AASTextField extends JTextField{
	public AASTextField(){
		setBackground(Color.decode("#eeeeee"));
		setBorder(null);
	};
	public AASTextField(String text){
		super(text);
		setBackground(Color.decode("#eeeeee"));
		setBorder(null);
	};
	public AASTextField(String text, String color){
		super(text);
		setBackground(Color.decode(color));
		setBorder(null);
	};
	public void setBackColor(String color) {
		setBackground(Color.decode(color));
	};
	public void setTextColor(String color) {
		setForeground(Color.decode(color));
	};
}
