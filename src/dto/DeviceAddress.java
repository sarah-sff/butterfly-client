package dto;

/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:37:44 
 * @version V1.0.0
 */
public class DeviceAddress {

	/**
	 * byte ����
	 */
	public byte realAddr;

	/**
	 * int ���� realAddr �� id ֮ǰ���ڶ�Ӧ��ϵ
	 */
	public int addr;

	public String name;
	/**
	 * ͨѶʧ�ܴ���
	 */
	public int failTimes = 0;

	public DeviceAddress(int id, String name) {
		super();
		this.addr = id;
		this.realAddr = getRealAddr();
		this.name = name;
	}

	public DeviceAddress(int id) {
		super();
		this.addr = id;
		this.realAddr = getRealAddr();
		this.name = id + "";
	}

	/**
	 * ������ʵ��ַ������
	 * 
	 * @param id
	 */
	public DeviceAddress(byte id) {
		super();
		this.realAddr = id;
		this.addr = getAddr();

		this.name = id + "";
	}

	public byte getRealAddr() {
		if (addr > 127) {
			realAddr = (byte) (addr - 256);
		} else {
			realAddr = (byte) addr;
		}
		return realAddr;
	}

	public byte getAddr() {
		if (realAddr < 0) {
			addr = realAddr + 256;
		} else {
			addr = realAddr;
		}
		return realAddr;
	}

	public String toString() {
		return name;
	}

	public void connectFailed() {
		this.failTimes++;
		if (this.failTimes > 3 && this.realAddr == DeviceData.getSelectedDeviceAddr()) {
			DeviceData.linkOff();
			// ����
			this.failTimes = 0;
		}
	}

	public void connectSuccess() {
		this.failTimes = 0;
		if (this.realAddr == DeviceData.getSelectedDeviceAddr()) {
			DeviceData.linkOn();
		}
	}
}
