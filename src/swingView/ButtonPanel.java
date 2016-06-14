package swingView;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dto.DeviceData;
import servise.InstructionQueen;
import swingView.define.ButterButton;
import swingView.define.WarningPanel;

public class ButtonPanel extends JPanel {

	static JButton modeSwitchBtn = new ButterButton("手动/自动");
	static JButton inspectBtn = new ButterButton("巡检");
	static JButton pump1Btn = new ButterButton("泵1");
	static JButton pump2Btn = new ButterButton("泵2");
	static JButton setBtn = new ButterButton("设置");
	static JButton warningBtn = new ButterButton("报警记录");
	// JButton saveBtn= new Button("保存/复位");

	SettingFrame settFrame = new SettingFrame();
	WarningRecordFrame warningRecordFrame = null;

	public JButton getModeSwitchBtn() {
		return modeSwitchBtn;
	}

	public ButtonPanel() {
		setLayout(new GridLayout(1, 6));

		setBorder(BorderFactory.createEmptyBorder(10, 10, 25, 10));
		
		setOpaque(false);

		setBtn.addActionListener(new SetBtnListener(settFrame));
		modeSwitchBtn.addActionListener(new ModeListener());
		inspectBtn.addActionListener(new InspectListener());
		pump1Btn.addActionListener(new Pump1Listener());
		pump2Btn.addActionListener(new Pump2Listener());
		warningBtn.addActionListener(new ShowDetailListener(warningRecordFrame));

		add(modeSwitchBtn);
		add(inspectBtn);
		add(pump1Btn);
		add(pump2Btn);
		add(setBtn);
		add(warningBtn);
	}

	public static void mask() {

		modeSwitchBtn.setBackground(Color.gray);
		inspectBtn.setBackground(Color.gray);
		pump1Btn.setBackground(Color.gray);
		pump2Btn.setBackground(Color.gray);
		setBtn.setBackground(Color.gray);
	}

}

class ModeListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 自动换手动
		if (DeviceData.selectedMode == 1) {
			DeviceData.selectedMode = 0;
			InstructionQueen.getInstance().addUrgent(new byte[] { DeviceData.getSelectedDeviceAddr(), 5, 0, 1, -1, 0 });
		} else {
			DeviceData.selectedMode = 1;
			InstructionQueen.getInstance().addUrgent(new byte[] { DeviceData.getSelectedDeviceAddr(), 5, 0, 0, -1, 0 });
		}
	}
}

class InspectListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("点击了巡检");
		InstructionQueen.getInstance().addUrgent(new byte[] { DeviceData.getSelectedDeviceAddr(), 5, 0, 4, -1, 0 });
	}
}

class Pump1Listener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("点击了泵1");
		InstructionQueen.getInstance().addUrgent(new byte[] { DeviceData.getSelectedDeviceAddr(), 5, 0, 2, -1, 0 });
	}
}

class Pump2Listener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("点击了泵2");
		InstructionQueen.getInstance().addUrgent(new byte[] { DeviceData.getSelectedDeviceAddr(), 5, 0, 3, -1, 0 });
	}
}

class SetBtnListener implements ActionListener {
	SettingFrame settingFrame;

	public SetBtnListener(SettingFrame settFrame) {
		this.settingFrame = settFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("点击了设置");
		if (DeviceData.hasLinkOn) {
			InstructionQueen.getInstance().addUrgent(new byte[] { DeviceData.getSelectedDeviceAddr(), 3, 0, 0, 0, 32 });
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {

					if (settingFrame == null) {
						settingFrame = new SettingFrame();
					}
					settingFrame.setVisible(true);

				}
			});
		}
	}
}

class ShowDetailListener implements ActionListener {
	WarningRecordFrame warningRecordFrame;
	
	public ShowDetailListener(WarningRecordFrame frame){
		warningRecordFrame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				if(warningRecordFrame == null ){
					warningRecordFrame = new WarningRecordFrame();
					warningRecordFrame.setVisible(true);
				}else{
					warningRecordFrame.refresh();
					warningRecordFrame.setVisible(true);
				}
				

			}
		});
	}
}
