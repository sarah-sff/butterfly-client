package swingView;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import swingView.define.WarningPanel;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:46:15 
 * @version V1.0.0
 */
public class StatusPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	static WarningPanel warningPanel = new WarningPanel();

	public StatusPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		setOpaque(false);
		

		add("West",warningPanel);
	}

	
	public static void warning(String content) {
		if(content == null || content.length()<=0){
			warningPanel.cancelWarning();
		}else{
			warningPanel.warning(content);
		}
		
	}

}
