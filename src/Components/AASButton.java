package Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AASButton extends JButton{

	public AASButton(String text){
		super(text);
		setBorder(null);
		setFocusPainted(false);
		setPreferredSize(new Dimension(100,40));
		setBackground(Color.decode("#00adb5"));
		setForeground(Color.decode("#f5f5f5"));

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

	public void setTextColor(String color){
		setForeground(Color.decode(color));
	};

	public void setBackColor(String color) {
		setBackground(Color.decode(color));
	};
}
