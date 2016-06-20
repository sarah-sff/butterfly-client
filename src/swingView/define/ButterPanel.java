package swingView.define;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:47:24 
 * @version V1.0.0
 */
public class ButterPanel extends JPanel {

	Image image = new ImageIcon(this.getClass().getResource("/images/back_9.jpg")).getImage();

	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public ButterPanel(String imgPath ){
		image = new ImageIcon(this.getClass().getResource(imgPath)).getImage();
	}
	
	public ButterPanel(){}
}
