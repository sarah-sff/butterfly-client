package swingView;

import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:46:34 
 * @version V1.0.0
 */
public class TopPanel extends JPanel {
	TipPanel tipPanel = new TipPanel();
	public TopPanel() {
		setLayout(new GridLayout(3, 1));
		setOpaque(false);
		add(tipPanel);
		
		add(new AddressPanel());
		
		add( new StatusPanel());
	}
	
}
