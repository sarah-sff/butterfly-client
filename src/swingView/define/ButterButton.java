package swingView.define;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ButterButton extends JButton{
	
	Color color = new Color(210, 105, 30);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButterButton (String text){
		setText(text);
		setForeground(color);
		
		Font f = new Font("ËÎÌו", Font.BOLD, 12);
		setFont(f);
	}

}
