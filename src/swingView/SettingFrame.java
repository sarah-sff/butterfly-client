package swingView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dto.DeviceData;
import dto.SettingValDto;
import staticVal.SettingVals;
import swingView.define.ButterFrame;
import swingView.define.ButterPanel;
import utils.ByteUtil;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:46:08 
 * @version V1.0.0
 */
public class SettingFrame extends ButterFrame {

	static List<EditRow> editRows = new ArrayList<EditRow>();

	public SettingFrame() {
		super(DeviceData.proName+"-����");
		setSize(400, 400);
		setLocationRelativeTo(null);

		JPanel p = generateJPanel();

		JScrollPane scrollPane = new JScrollPane(p);
		setContentPane(scrollPane);

		setVisible(false);
	}

	private JPanel generateJPanel() {
		JPanel p = new ButterPanel("/images/back_9_1.jpg");
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		List<SettingValDto> list = SettingVals.getSettingVals();

		for (int i = 0; i < list.size(); i++) {
			EditRow e = new EditRow(list.get(i));
			editRows.add(e);
			p.add(e);

		}

		return p;
	}

	/**
	 * 436400040200000301000101000030011124010112401006010051-
	 * 722602600201013000000000000-67-108
	 * 
	 * @param modbusBytes
	 */
	public static void updataSetVals(byte[] modbusBytes) {

		int dataIndex = 3;
		for (int i = 0; i < editRows.size(); i++) {

			editRows.get(i).updateVal(ByteUtil.getIntWith2Byte(modbusBytes[dataIndex], modbusBytes[dataIndex + 1]));

			dataIndex += 2;
		}

	}

}
