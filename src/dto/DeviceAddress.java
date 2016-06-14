package dto;

import staticVal.PropKey;
import utils.ButterProperties;

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
		this.name = id+"";
	}
	
	/**
	 * 根据真实地址来构造
	 * @param id
	 */
	public DeviceAddress(byte id) {
		super();
		this.realAddr = id;
		this.addr = getAddr();
		
		this.name = id+"";
	}
	
	
	public byte getRealAddr(){
		if(addr>127){
			realAddr = (byte)(addr-256);
		}else{
			realAddr = (byte)addr;
		}
		return realAddr;
	}
	
	
	public byte getAddr(){
		if(realAddr<0){
			addr = realAddr+256;
		}else{
			addr = realAddr;
		}
		return realAddr;
	}
	
	public String toString(){
		return name;
	}
	
	public void connectFailed(){
		this.failTimes++;
		System.out.println("【连接失败】设备地址："+this.realAddr+"失败次数："+ this.failTimes);
		if(this.failTimes>3 && this.realAddr== DeviceData.getSelectedDeviceAddr()){
			System.out.println("<<<<<<<断开连接");
			DeviceData.linkOff();
			//归零
			this.failTimes=0;
		}
	}
	
	public void connectSuccess(){
		this.failTimes=0;
		
		if(this.realAddr== DeviceData.getSelectedDeviceAddr()){
			DeviceData.linkOn();
		}
	}
}




