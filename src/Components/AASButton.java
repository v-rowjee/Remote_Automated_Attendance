package Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AASButton extends JButton{

	public Color primary = Color.decode("#00adb5");
	public Color dark = Color.darkGray;
	public Color light = Color.decode("#f5f5f5");

	public AASButton(String text){
		super(text);
		setBorder(null);
		setFocusPainted(false);
		setPreferredSize(new Dimension(100,40));
		setBackground(primary);
		setForeground(light);

		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setBackground(primary.darker());
				setForeground(light);
			}

			public void mouseExited(MouseEvent e) {
				setBackground(primary);
				setForeground(light);
			}
		});
		
		
	};

	public void setDarkPrimary(boolean isDark){
		primary = primary.darker();
		setBackground(primary);
	}

	public void setDark(boolean isDark){
		primary = dark;
		setBackground(primary);
	}

	public void setTextColor(String color){
		setForeground(Color.decode(color));
	};

	public void setBackColor(String color) {
		setBackground(Color.decode(color));
	};
}
