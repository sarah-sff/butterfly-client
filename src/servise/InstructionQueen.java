package servise;

import java.util.concurrent.ArrayBlockingQueue;

import define.PollingBlockQueue;
import dto.DeviceAddress;
import dto.DeviceData;

/**
 * 
 * @Description 指令队列 urgentQueen：存放紧急指令 pollingQueen: 存放轮询指令
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:42:09 
 * @version V1.0.0
 */
public class InstructionQueen {

	private static final InstructionQueen single = new InstructionQueen();

	/**
	 * 紧急队列,用于设置和配置信息(写操作)
	 */
	public ArrayBlockingQueue<byte[]> urgentQueen = new ArrayBlockingQueue<byte[]>(256);
	/**
	 * 常规循环队列,用于读取指示灯和电源/电压
	 */
	public PollingBlockQueue commonPollingQueen = new PollingBlockQueue(2);

	/**
	 * 警报循环队列,用于读取指示灯和电源/电压
	 */
	public PollingBlockQueue warningPollingQueen = new PollingBlockQueue(256);

	private int commonTimes = 0;

	private int maxCommonPollingNum = 3;

	// 单例
	private InstructionQueen() {
	}

	public static InstructionQueen getInstance() {
		return single;
	}

	/**
	 * 队列大小
	 *
	 * @return
	 */
	public int size() {
		return urgentQueen.size() + commonPollingQueen.size()+ warningPollingQueen.size();
	}

	/**
	 * 增加紧急element
	 *
	 * @param element
	 */
	public void addUrgent(byte[] element) {

		if (urgentQueen.size() < 256) {
			urgentQueen.add(element);
		} else {
			System.out.println("紧急队列元素过多,直接抛弃新指令... %s " + element.toString());
		}
	}

	/**
	 * 增加普通element
	 *
	 * @param element
	 */
	public void addCommon(Object element) {

		if (commonPollingQueen.size() < 2) {
			commonPollingQueen.add(element);
		}

	}
	
	
	/**
	 * 增加普通element
	 *
	 * @param element
	 */
	public void addWarn(Object element) {
		if (warningPollingQueen.size() <= 256) {
			warningPollingQueen.add(element);
		}

	}

	/**
	 * 获取元素 如果紧急队列里面有，就拿紧急队列的 否则，拿普通队列的
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public Object poll() {
		Object e = null;
		if (urgentQueen.isEmpty()) {
			if (commonTimes < maxCommonPollingNum ) {
				commonTimes++;
				e = commonPollingQueen.poll();
			} else {
				commonTimes = 0;
				e = warningPollingQueen.poll();
			}
		} else {
			e = urgentQueen.poll();
		}
		return e;
	}

	/**
	 * 刷新循环指令队列
	 */
	public void refreshPollingInstruction() {
		commonPollingQueen.clear();
		
		byte addr = DeviceData.getSelectedDeviceAddr();
		
		if(DeviceData.isValidAddr(addr)){
			byte[] cycleInstruction1 = new byte[] { addr, 2, 0, 0, 0, 18 };
			byte[] cycleInstruction2 = new byte[] { addr, 4, 0, 0, 0, 3 };

			addCommon(cycleInstruction1);
			addCommon(cycleInstruction2);
		}
	}
	
	/**
	 * 清除指令
	 */
	public void clearInstruction(){
		commonPollingQueen.clear();
		urgentQueen.clear();
	}
	
	
	/*
	 * 初始化警告轮询队列
	 */
	public void refreshWarningPollingInstruction(){
		DeviceAddress[] deviceAddresses = DeviceData.getAllDeviceAdress();
		for( DeviceAddress addr:deviceAddresses ){
			byte[] queryCode = new byte[] {addr.realAddr, 2, 0, 0, 0, 18 };
			addWarn(queryCode);
		}
	}
	
	
	/**
	 * 指令队列刷新（设备数量发生变更的时候）
	 * 1、更改警报轮询指令队列
	 * 2、清空现有common合urgent指令队列
	 * 3、检查队列清空
	 * 4、发送指令
	 */
	public void refresh(){
		refreshWarningPollingInstruction();
		clearInstruction();
		
		Stabler.getInstance().clear();
		
		SerialService.sendout();
	}


}
