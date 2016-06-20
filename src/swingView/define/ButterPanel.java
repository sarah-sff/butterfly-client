package swingView.define;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:47:24 
 * @version V1.0.0
 */
public class ButterPanel extends JPanel {

	Image image = new ImageIcon(this.getClass().getResource("/images/back_9.jpg")).getImage();

	// �̶�����ͼƬ���������JPanel������ͼƬ������������
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public ButterPanel(String imgPath ){
		image = new ImageIcon(this.getClass().getResource(imgPath)).getImage();
	}
	
	public ButterPanel(){}
}
