package swingView;

import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:46:34 
 * @version V1.0.0
 */
public class TopPanel extends JPanel {
	TipPanel tipPanel = new TipPanel();
	public TopPanel() {
		setLayout(new GridLayout(3, 1));
		setOpaque(false);
		add(tipPanel);
		
		add(new AddressPanel());
		
		add( new StatusPanel());
	}
	
}
