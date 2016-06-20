package swingView.define;

import java.awt.Color;

import javax.swing.JPanel;

import swingView.LightBtn;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:47:37 
 * @version V1.0.0
 */
public class WarningPanel extends JPanel {
	static LightBtn warningBtn = new LightBtn(Color.gray, 30);
	AutoScrollLabel warningText = new AutoScrollLabel("");
	
	
	public WarningPanel(){
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





