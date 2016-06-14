package dto;

import exception.IllegalDeviceAddrException;
import servise.InstructionQueen;
import staticVal.PropKey;
import swingView.AddressPanel;
import swingView.LightPanel;
import swingView.NumberPanel;
import swingView.StatusPanel;
import utils.ButterProperties;

/**
 * Created by Administrator on 2016/4/21.
 */
public class DeviceData {
	/**
	 * ���������������豸
	 */
	public static DeviceAddress[] addressArray = null;
	/**
	 * �豸��ַ,��ʾ��ǰ�����豸
	 */
	private static byte selectedDeviceAddr = -1;

	/**
	 * ģʽ���Զ�/�ֶ�, 0:�ֶ���1���Զ�
	 */
	public static byte selectedMode = 1;

	/**
	 * ģʽ���Զ�/�ֶ�, 0:�ֶ���1���Զ�
	 */
	public static int request_period = 100;

	/**
	 * �Ƿ���豸����ͨ�ţ��յ�ָ����ַ���豸�Ĵ�����Ӧ��
	 */
	public static boolean hasLinkOn = false;

	/**
	 * �ж��Ƿ�ǰ��ַ
	 * 
	 * @param addr
	 * @return
	 */
	public synchronized static boolean isCurrentAddr(byte addr) {
		return addr == selectedDeviceAddr;
	}

	public synchronized static void linkOn() {
		hasLinkOn = true;
		AddressPanel.linkOn();
	}

	public synchronized static void linkOff() {

		hasLinkOn = false;
		NumberPanel.mask();
		LightPanel.mask();
		AddressPanel.linkOff();
	}

	public synchronized static boolean getLinkStatus() {
		return hasLinkOn;
	}

	/**
	 * ��ȡ��ǰ�����豸��ַ
	 *
	 * @return
	 */
	public synchronized static byte getSelectedDeviceAddr() {
		
		return selectedDeviceAddr;
	}
	
	/**
	 * �жϵ�ַ�Ƿ�Ϸ�
	 * @param addr
	 * @return
	 */
	public static boolean isValidAddr(byte addr){
		DeviceAddress deviceAddress = getDeviceByAddr(addr);
		if(deviceAddress == null){
			return false;
		}
		return true;
	}

	/**
	 * ���õ�ǰ�����豸��ַ
	 *
	 * @param selectedDeviceAddr
	 */
	public synchronized static void setSelectedDeviceAddr(byte selectedDeviceAddr) throws IllegalDeviceAddrException {

		DeviceAddress deviceAddress = getDeviceByAddr(selectedDeviceAddr);
		if(deviceAddress == null){
			return;
		}
		
		if (selectedDeviceAddr > 127 || selectedDeviceAddr < -128) {
			throw new IllegalDeviceAddrException("�豸��ַ����");
		}

		DeviceData.selectedDeviceAddr = selectedDeviceAddr;

		InstructionQueen.getInstance().refreshPollingInstruction();
	}

	/**
	 * ˢ�»����������豸
	 */
	public static void refreshAllAddress() {
		Integer size = ButterProperties.GetIntegerValByKey(PropKey.PUMP_NUM);

		if (size == null) {
			size = 0;
		}

		addressArray = new DeviceAddress[size];
		for (int i = 0; i < size; i++) {
			addressArray[i] = new DeviceAddress(i);
		}

		// ���µ�ַ��������
		AddressPanel.refreshAddrCombox();

		// ��ַĬ��Ϊ0
		try {
			DeviceData.linkOff();
			DeviceData.setSelectedDeviceAddr((byte) 0);
		} catch (IllegalDeviceAddrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ָ�����ҲҪˢ��
		InstructionQueen.getInstance().refresh();
	}

	/**
	 * ��ȡ�����豸��ַ
	 * 
	 * @return
	 */
	public static DeviceAddress[] getAllDeviceAdress() {
		if (addressArray == null) {
			refreshAllAddress();
		}

		return addressArray;
	}

	/**
	 * ��ȡ�豸
	 * 
	 * @param addr
	 * @return
	 */
	public static DeviceAddress getDeviceByAddr(byte addr) {
		DeviceAddress[] addrs = getAllDeviceAdress();
		for (int i = 0; i < addrs.length; i++) {
			if (addrs[i].realAddr == addr) {
				return addrs[i];
			}
		}
		return null;
	}

	/**
	 * ͨѶʧ��
	 * 
	 * @param addr
	 */
	public static void connectionFailed(byte addr) {
		DeviceAddress deviceAddress = getDeviceByAddr(addr);
		if (deviceAddress != null) {
			deviceAddress.connectFailed();
		}
	}
	
	/**
	 * ͨѶ�ɹ�
	 * 
	 * @param addr
	 */
	public static void connectionSucceed(byte addr) {
		DeviceAddress deviceAddress = getDeviceByAddr(addr);
		if (deviceAddress != null) {
			deviceAddress.connectSuccess();
		}
	}


}
