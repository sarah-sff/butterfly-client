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
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:45:30 
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
