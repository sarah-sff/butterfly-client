package dto;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:37:44 
 * @version V1.0.0
 */
public class DeviceAddress {

	/**
	 * byte 类型
	 */
	public byte realAddr;

	/**
	 * int 类型 realAddr 和 id 之前存在对应关系
	 */
	public int addr;

	public String name;
	/**
	 * 通讯失败次数
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
	 * 根据真实地址来构造
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
			// 归零
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
