package swingView.define;

import java.awt.Color;

import javax.swing.JPanel;

import swingView.LightBtn;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:47:37 
 * @version V1.0.0
 */
public class WarningPanel extends JPanel {
	static LightBtn warningBtn = new LightBtn(Color.gray, 30);
	AutoScrollLabel warningText = new AutoScrollLabel("");
	
	
	public WarningPanel(){
		setOpaque(false);
		Thread thread = new Thread(warningText);
		thread.start();
		
		add(warningBtn);
		add(warningText);
		
		setVisible(false);
		
	}
	
	public void warning(String content) {
		warningText.setContent(content);
		warningBtn.setBackground(Color.red);
		setVisible(true);
	}

	public void cancelWarning() {
		warningText.setContent("");
		warningBtn.setBackground(Color.gray);
		setVisible(false);
	}

}





