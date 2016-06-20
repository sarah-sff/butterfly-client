package swingView;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import swingView.define.WarningPanel;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:46:15 
 * @version V1.0.0
 */
public class StatusPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	static WarningPanel warningPanel = new WarningPanel();

	public StatusPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
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
