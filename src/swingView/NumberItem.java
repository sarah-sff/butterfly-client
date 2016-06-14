package swingView;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingView.define.ButterLabel;

public class NumberItem extends JPanel{
	
	JLabel numberLable = new ButterLabel("0",JLabel.CENTER);
	JLabel textLabel = new ButterLabel("µçÁ÷",JLabel.CENTER);
	
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
	}
	
	public void updateNumber(String numStr){
		numberLable.setText(numStr);
	}
	
	public void clear(){
		numberLable.setText("--");
//		numberLable.setForeground(Color.lightGray);
	}

}
