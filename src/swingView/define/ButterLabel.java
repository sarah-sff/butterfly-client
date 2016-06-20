package swingView.define;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:47:19 
 * @version V1.0.0
 */
public class ButterLabel extends JLabel {
	Color color = Color.white;
	
	 public ButterLabel(String text){
		 setForeground(color);
		 setText(text);
	 }
	 
	 public ButterLabel(String text,int weight,int height){
		 setPreferredSize(new Dimension(weight,height));
		 setForeground(color);
		 setText(text);
	 }
	 
	 public ButterLabel(String text, int horizontalAlignment) {
	        super(text, horizontalAlignment);
	        setForeground(color);
	    }
	 
	 
	 public ButterLabel(Color col,String text, int horizontalAlignment) {
	        super(text, horizontalAlignment);
	        setForeground(col);
	    }

}
