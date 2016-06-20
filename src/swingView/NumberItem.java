package swingView;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import swingView.define.ButterLabel;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:45:49 
 * @version V1.0.0
 */
public class NumberItem extends JPanel{
	
	JLabel numberLable = new ButterLabel(Color.red,"0",JLabel.CENTER);
	JLabel textLabel = new ButterLabel("",JLabel.CENTER);
	
	public NumberItem(String text){
		
		setOpaque(false);
		textLabel.setText(text);
		setLayout(new GridLayout(2,1));
		
		numberLable.setFont(new   java.awt.Font("Dialog",   1,   52));
		clear();
		
		add(numberLable);
		add(textLabel);
	}
	
	public void updateNumber(int num){
		numberLable.setText(num+"");
		numberLable.setForeground(Color.red);
	}
	
	public void updateNumber(String numStr){
		numberLable.setText(numStr);
		numberLable.setForeground(Color.red);
	}
	
	public void clear(){
		numberLable.setText("--");
		numberLable.setForeground(Color.gray);
	}

}
