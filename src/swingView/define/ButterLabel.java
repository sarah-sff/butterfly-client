package swingView.define;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:47:19 
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
