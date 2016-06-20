package swingView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import staticVal.PropKey;
import utils.ButterProperties;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:46:25 
 * @version V1.0.0
 */
public class TipPanel extends JPanel {

	static JLabel label = new JLabel("欢迎进入水泵控制桌面");

	public TipPanel() {
		
		setLayout(new BorderLayout());
		setOpaque(false);
		
		JPanel p1 = new JPanel();
		label.setFont(new Font(Font.DIALOG, 1, 24));
		Color color = new Color(240,255,255);
		label.setForeground(color);
		
		p1.add(label);
		p1.setOpaque(false);
		add("Center",p1);
		
		
		
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/setting10.png"));  
		JButton jb = new JButton();
		
		jb.addActionListener(new ShowInitialSettingFrame());
		jb.setIcon(imageIcon);
		jb.setContentAreaFilled( false );
		add("East",jb);
		
		
		
		
		
	}
}

class ShowInitialSettingFrame implements ActionListener {
//	InitialFrame initialFrame;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				Integer pumpNum = ButterProperties.GetIntegerValByKey(PropKey.PUMP_NUM);
				Application.showInitial(pumpNum);

			}
		});
	}
}


