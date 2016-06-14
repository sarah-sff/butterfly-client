package swingView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dto.DeviceAddress;
import dto.DeviceData;
import exception.IllegalDeviceAddrException;
import servise.SerialService;
import staticVal.PropKey;
import utils.ButterProperties;

public class TipPanel extends JPanel {

	static JLabel label = new JLabel("欢迎进入水泵控制桌面");

	public TipPanel() {
		
		setLayout(new BorderLayout());
		setOpaque(false);
		
		JPanel p1 = new JPanel();
		label.setFont(new Font("Dialog", 1, 24));
		label.setForeground(Color.white);
		
		p1.add(label);
		p1.setOpaque(false);
		add("Center",p1);
		
		
		
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/setting10.png"));  
		JButton jb = new JButton();
		
		jb.addActionListener(new ShowInitialSettingFrame());
		
//		jb.setBounds(20, 20, 20, 20);
		jb.setIcon(imageIcon);
//		jb.setRolloverIcon(imageIcon);
//		jb.setBorderPainted( false );

//		jb.setFocusPainted( false );

		jb.setContentAreaFilled( false );

//		jb.setFocusable( true );

//		jb.setMargin( new Insets(0, 0, 0, 0));
//		add(jb);
		
		add("East",jb);
//		
//		add("West",new JPanel());
		
		
		
		
		
	}
}

class ShowInitialSettingFrame implements ActionListener {
//	InitialFrame initialFrame;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				Integer val = ButterProperties.GetIntegerValByKey(PropKey.PUMP_NUM);
				Application.showInitial(val);

			}
		});
	}
}


