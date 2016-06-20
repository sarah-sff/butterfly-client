package dto;

import exception.IllegalDeviceAddrException;
import servise.InstructionQueen;
import staticVal.PropKey;
import swingView.AddressPanel;
import swingView.LightPanel;
import swingView.NumberPanel;
import utils.ButterProperties;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:38:20 
 * @version V1.0.0
 */
public class DeviceData {
	
	public static String proName = "昌运butterfly";
	/**
	 * 本机所连的所有设备
	 */
	public static DeviceAddress[] addressArray = null;
	/**
	 * 设备地址,表示当前操作设备
	 */
	private static byte selectedDeviceAddr = -1;

	/**
	 * 模式，自动/手动, 0:手动，1：自动
	 */
	public static byte selectedMode = 1;

	/**
	 * 模式，自动/手动, 0:手动，1：自动
	 */
	public static int request_period = 100;

	/**
	 * 是否和设备监理通信（收到指定地址的设备的串口响应）
	 */
	public static boolean hasLinkOn = false;

	/**
	 * 判断是否当前地址
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
	 * 读取当前操作设备地址
	 *
	 * @return
	 */
	public synchronized static byte getSelectedDeviceAddr() {
		
		return selectedDeviceAddr;
	}
	
	/**
	 * 判断地址是否合法
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
	 * 设置当前操作设备地址
	 *
	 * @param selectedDeviceAddr
	 */
	public synchronized static void setSelectedDeviceAddr(byte selectedDeviceAddr) throws IllegalDeviceAddrException {

		DeviceAddress deviceAddress = getDeviceByAddr(selectedDeviceAddr);
		if(deviceAddress == null){
			return;
		}
		
		if (selectedDeviceAddr > 127 || selectedDeviceAddr < -128) {
			throw new IllegalDeviceAddrException("设备地址错误");
		}

		DeviceData.selectedDeviceAddr = selectedDeviceAddr;

		InstructionQueen.getInstance().refreshPollingInstruction();
	}

	/**
	 * 刷新机上所连的设备
	 */
	public static void refreshAllAddress() {
		Integer size = ButterProperties.GetIntegerValByKey(PropKey.PUMP_NUM);

		if (size == null) {
			size = 0;
		}

		addressArray = new DeviceAddress[size+1];
		for (int i = 0; i <=size; i++) {
			addressArray[i] = new DeviceAddress(i);
		}

		// 更新地址下拉内容
		AddressPanel.refreshAddrCombox();

		// 地址默认为0
		try {
			DeviceData.linkOff();
			DeviceData.setSelectedDeviceAddr((byte) 0);
		} catch (IllegalDeviceAddrException e) {
			e.printStackTrace();
		}

		// 指令队列也要刷新
		InstructionQueen.getInstance().refresh();
	}

	/**
	 * 获取所有设备地址
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
	 * 获取设备
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
	 * 通讯失败
	 * @param addr
	 */
	public static void connectionFailed(byte addr) {
		DeviceAddress deviceAddress = getDeviceByAddr(addr);
		if (deviceAddress != null) {
			deviceAddress.connectFailed();
		}
	}
	
	/**
	 * 通讯成功
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
