package swingView;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class TopPanel extends JPanel {
	TipPanel tipPanel = new TipPanel();
	public TopPanel() {
		setLayout(new GridLayout(3, 1));
		
//		setBackground(Color.gray);
		setOpaque(false);
		add(tipPanel);
		
		add(new AddressPanel());
		
		add( new StatusPanel());
	}
	
}
