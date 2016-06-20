package swingView.define;


import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:47:06 
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
		setIcon(icon);// 为按钮增加图标
		setText(text);
		setForeground(color);
		setOpaque(false);
		setVerticalTextPosition(JButton.BOTTOM);
		setHorizontalTextPosition(JButton.CENTER);
	}

}
