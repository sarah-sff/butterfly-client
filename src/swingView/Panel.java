package swingView;

import java.awt.Color;
import java.awt.GridLayout;
import swingView.define.ButterPanel;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:46:01 
 * @version V1.0.0
 */
public class Panel extends ButterPanel {
	TopPanel topPanel = new TopPanel();
	LightPanel lightPanel = new LightPanel();
	NumberPanel numberPanel = new NumberPanel();
	ButtonPanel buttonPanel = new ButtonPanel();

	public Panel() {
		setBackground(Color.red);
		setLayout(new GridLayout(4, 1));// ���񲼾֣�3��1��
		add(topPanel);
		add(lightPanel);
		add(numberPanel);
		add(buttonPanel);
	}
	
}
