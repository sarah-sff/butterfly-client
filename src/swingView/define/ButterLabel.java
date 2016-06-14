package swingView.define;

import java.awt.Color;

import javax.swing.JLabel;

public class ButterLabel extends JLabel {
	
	Color color = new Color(245,245,220);
	
	 public ButterLabel(String text){
		 setForeground(color);		 
		 setText(text);
	 }
	 
	 public ButterLabel(String text, int horizontalAlignment) {
	        super(text, horizontalAlignment);
	        setForeground(color);
	    }

}
