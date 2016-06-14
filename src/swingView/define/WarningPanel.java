package swingView.define;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import dto.DeviceData;
import dto.SettingOptions;
import servise.InstructionQueen;
import staticVal.SettingType;
import swingView.LightBtn;
import swingView.SettingFrame;
import swingView.WarningRecordFrame;

public class WarningPanel extends JPanel {
	static LightBtn warningBtn = new LightBtn(Color.gray, 30);
	AutoScrollLabel warningText = new AutoScrollLabel("");
	
	
	public WarningPanel(){
//		setBackground(Color.lightGray);
		setOpaque(false);
		Thread thread = new Thread(warningText);
		thread.start();
		
		add(warningBtn);
		add(warningText);
		
		setVisible(false);
		
	}
	
	public void warning(String content) {
		warningText.setContent(content);
		warningBtn.setBackground(Color.red);
		setVisible(true);
	}

	public void cancelWarning() {
		warningText.setContent("");
		warningBtn.setBackground(Color.gray);
		setVisible(false);
	}

}





