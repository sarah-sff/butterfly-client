package swingView;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import swingView.define.WarningPanel;

public class StatusPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static WarningPanel warningPanel = new WarningPanel();

	public StatusPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		setOpaque(false);
		

		add("West",warningPanel);
	}

	
	public static void warning(String content) {
		if(content == null || content.length()<=0){
			warningPanel.cancelWarning();
		}else{
			warningPanel.warning(content);
		}
		
	}

}
