package swingView;

import java.awt.Color;
import java.awt.GridLayout;
import swingView.define.ButterPanel;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:46:01 
 * @version V1.0.0
 */
public class Panel extends ButterPanel {
	TopPanel topPanel = new TopPanel();
	LightPanel lightPanel = new LightPanel();
	NumberPanel numberPanel = new NumberPanel();
	ButtonPanel buttonPanel = new ButtonPanel();

	public Panel() {
		setBackground(Color.red);
		setLayout(new GridLayout(4, 1));// 网格布局，3行1列
		add(topPanel);
		add(lightPanel);
		add(numberPanel);
		add(buttonPanel);
	}
	
}
