package swingView;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.DeviceAddress;
import dto.DeviceData;
import exception.IllegalDeviceAddrException;
import servise.SerialService;
import swingView.define.ButterLabel;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:44:17 
 * @version V1.0.0
 */
public class AddressPanel extends JPanel {
	
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 1L;

	static JComboBox addrCombox = null;
	
	static LightBtn statusBtn = new LightBtn(Color.LIGHT_GRAY, 20);
	static JLabel statusText = new ButterLabel("δ��ͨ");

	public AddressPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		setOpaque(false);
		setForeground(Color.white);

		String[] ports = SerialService.getPorts();

		if (ports == null || ports.length == 0) {
			JLabel warningLabel = new JLabel("<�뽫�豸���ӱ����˿ڣ�>");
			warningLabel.setFont(new Font("Dialog", 1, 18));
			warningLabel.setForeground(Color.red);
			add(warningLabel);
		}else{
			JLabel portLabel = new ButterLabel("�˿�");
			add(portLabel);
			SerialService.setPort(ports[0]);
			if(ports.length == 1){
				portLabel.setText("�˿ڣ�"+ports[0]);
			}else{
				JComboBox portComboBox = new JComboBox(ports);				
				portComboBox.addItemListener(new PortComboxItemListener());
				add(portComboBox);
			}
			JLabel label = new ButterLabel("��ѡ���豸��ַ:");
			add(label);
			
			addrCombox = new JComboBox();
			addrCombox.addItemListener(new AddressComboxItemListener());
			
			DeviceData.refreshAllAddress();
			add(addrCombox);
		}
		
		add(statusBtn);
		add(statusText);
	}
	
	public static void linkOn() {
		statusBtn.lightOn();
		statusText.setText("ͨѶ����");
	}

	public static void linkOff() {
		statusBtn.lightOff();
		statusText.setText("δ��ͨ");
	}

	
	public static void refreshAddrCombox(){
		
		if(addrCombox != null){
			//��������Ԫ��
			addrCombox.removeAllItems();		
			DeviceAddress[] addrs= DeviceData.getAllDeviceAdress();
			for(int i=0;i<addrs.length;i++){
				addrCombox.addItem(addrs[i]);
			}
		}
	}
}

class PortComboxItemListener implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		int state = e.getStateChange();

		if (state == ItemEvent.SELECTED) {

			String portName = (String) e.getItem();

			SerialService.setPort(portName);

		}

	}
};

class AddressComboxItemListener implements ItemListener {
	@Override
	public void itemStateChanged(ItemEvent e) {
		int state = e.getStateChange();
		if (state == ItemEvent.SELECTED) {
			DeviceAddress addr = (DeviceAddress) e.getItem();
			try {
				DeviceData.linkOff();
				DeviceData.setSelectedDeviceAddr(addr.realAddr);

			} catch (IllegalDeviceAddrException e1) {
				e1.printStackTrace();
			}

		}

	}
};
