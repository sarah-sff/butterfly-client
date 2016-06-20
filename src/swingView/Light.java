package swingView;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

import swingView.define.ButterLabel;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:45:30 
 * @version V1.0.0
 */
public class Light extends JPanel{
	LightBtn lightBtn = new LightBtn(Color.LIGHT_GRAY);
	public Light(String text){
		setLayout(null);
		add(lightBtn);
		JLabel l= new ButterLabel(text);
		l.setBounds(new Rectangle(20, 80, 50, 50));
		add(l);
		
		setOpaque(false);
		
	}
	
	public void lightOff(){
		
		lightBtn.lightOff();
	}
	
	public void lightOn(){
		lightBtn.lightOn();
	}
	
	
}
