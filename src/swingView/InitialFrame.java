package swingView;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dto.DeviceData;
import staticVal.PropKey;
import swingView.define.ButterButton;
import swingView.define.ButterFrame;
import swingView.define.ButterLabel;
import swingView.define.ButterPanel;
import swingView.define.JNumberField;
import utils.ButterProperties;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:45:25 
 * @version V1.0.0
 */
public class InitialFrame extends ButterFrame {
	JLabel label = new ButterLabel("设备最大地址",100,20);
	JNumberField numberField = new JNumberField(10, 0, 255);
	JButton btn = new ButterButton("保存");

	public InitialFrame() {
		super(DeviceData.proName+"-初始化设置");
		setSize(400, 130);
		setLocationRelativeTo(null);

		JPanel p = new ButterPanel();
		p.setOpaque(false);
		add(p);

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		JPanel item = new JPanel();
		item.setLayout(new FlowLayout());
		item.setOpaque(false);
		
		item.add(label);
		item.add(numberField);
		item.add(btn);
		
		p.add(item);

		btn.addActionListener(new SaveInitialDataListener(numberField,PropKey.PUMP_NUM));

	}

	public void setValue(int val) {
		numberField.setText(val + "");
	}

}

class SaveInitialDataListener implements ActionListener {
	JNumberField numberField;
	String key;

	public SaveInitialDataListener(JNumberField numField,String keyStr) {
		numberField = numField;
		key = keyStr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (numberField.valid()) {
					try {
						ButterProperties.WriteProperties(key, numberField.getValue() + "");
						Application.showMain();

						DeviceData.refreshAllAddress();
					} catch (IOException e) {

					}
				}

			}
		});
	}
}