package swingView;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dto.DeviceAddress;
import dto.DeviceData;
import staticVal.PropKey;
import swingView.define.ButterButton;
import swingView.define.ButterFrame;
import swingView.define.ButterLabel;
import swingView.define.ButterPanel;
import swingView.define.JNumberField;
import utils.ButterProperties;

public class InitialFrame extends ButterFrame {
	JLabel label = new ButterLabel("设备数量(设备最大地址)");
	JNumberField numberField = new JNumberField(10, 0, 255);
	JButton btn = new ButterButton("保存");

	public InitialFrame() {
		super("butterfly-初始化设置");
		setSize(400, 100);
		setLocationRelativeTo(null);

		JPanel p = new ButterPanel();
		p.setOpaque(false);
		add(p);

		p.setLayout(new FlowLayout());
		p.add(label);
		p.add(numberField);
		p.add(btn);

		btn.addActionListener(new SaveInitialDataListener(numberField));

	}

	public void setValue(int val) {
		numberField.setText(val + "");
	}

}

class SaveInitialDataListener implements ActionListener {
	JNumberField numberField;

	public SaveInitialDataListener(JNumberField numField) {
		numberField = numField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (numberField.valid()) {
					try {
						ButterProperties.WriteProperties(PropKey.PUMP_NUM, numberField.getValue() + "");
						Application.showMain();

						DeviceData.refreshAllAddress();
					} catch (IOException e) {

					}
				}

			}
		});
	}
}