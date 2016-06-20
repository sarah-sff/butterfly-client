package swingView.define;


import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:47:06 
 * @version V1.0.0
 */
public class ButterButton extends JButton{
	
	Color color = new Color(105,105,105);
	private static final long serialVersionUID = 1L;

	public ButterButton (String text){
		setText(text);
		setForeground(color);
	}
	
	
	public ButterButton (String text,Icon icon){
		setIcon(icon);// Ϊ��ť����ͼ��
		setText(text);
		setForeground(color);
		setOpaque(false);
		setVerticalTextPosition(JButton.BOTTOM);
		setHorizontalTextPosition(JButton.CENTER);
	}

}
