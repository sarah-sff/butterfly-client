package servise;

import dto.DeviceData;
import gnu.io.CommPortIdentifier;
import swingView.LightPanel;
import swingView.NumberPanel;
import swingView.SettingFrame;
import utils.ByteUtil;
import utils.CRC16;
import utils.SerialReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.comm.SerialPort;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:42:22 
 * @version V1.0.0
 */
public class SerialService {

	private static SerialReader sr = new SerialReader();
	private static String portName = "";

	public static void setPort(String portNameString) {
		portName = portNameString;
		
		SerialService observer = new SerialService();
		observer.openPort();
		
		// 如果指令队列有指令，向串口发送消息
		if (InstructionQueen.getInstance().size() > 0) {
			sendout();
		}

	}

	/**
	 * 获取当前可用的端口
	 * 
	 * @return
	 */

	public static String[] getPorts() {
		HashSet<CommPortIdentifier> list = sr.getAvailableSerialPorts();
		int len = list.size();
		String[] result = new String[len];

		int index = 0;
		for (CommPortIdentifier c : list) {
			result[index++] = c.getName();
		}

		return result;
	}

	/**
	 * 往串口发送数据,实现双向通讯.
	 */
	private static void send(byte[] message) {
		SerialService observer = new SerialService();
		observer.sendMsg(message);

		// 记录发送时间
		Stabler.getInstance().sendMsg(message);
	}

	/**
	 * 开始向串口发送短信
	 */
	public static void sendout() {
		if (portName != null && portName.length() > 0) {
			InstructionQueen.getInstance().refreshPollingInstruction();
			// 开始发送指令
			SerialService.sendNextInstruction();
		}
	}

	/**
	 * 发送下一条指令
	 */
	public static void sendNextInstruction() {

		// 指令队列为空，不发送
		if (InstructionQueen.getInstance().size() <= 0) {
			return;
		}
		try {
			Thread.sleep(DeviceData.request_period);
		} catch (Exception e) {
		}

		byte[] nextInstruction = (byte[]) InstructionQueen.getInstance().poll();
		if (nextInstruction == null) {
			return;
		}
		SerialService.send(nextInstruction);
	}

	/**
	 * 解析返回结果
	 *
	 * @param message
	 */
	public static void doAnalyse(byte[] message) {

		byte addrCode = message[0];

		DeviceData.connectionSucceed(addrCode);
		// 数据长度校验
		if (message.length < 3)
			return;

		byte actionCode = message[1];

		if (actionCode == 2) {
			AnalyseService.analyseForCodeTwo(message);
		} else if (actionCode == 4) {
			AnalyseService.analyseForCodeFour(message);
		} else if (actionCode == 3) {
			SettingFrame.updataSetVals(message);
		}

	}
	
	/**
	 * 发送串口消息
	 * @param message
	 */
	public void sendMsg(byte[] message){
		if (message != null && message.length != 0) {
			sr.start();
			sr.run(CRC16.addCRCChecker(message));
		}
	}
	
	/**
	 * 发送串口消息
	 * @param message
	 */
	public void openPort(){
		openSerialPort(null);
	}

	/**
	 * 打开串口
	 */
	public void openSerialPort(byte[] message) {
		HashMap<String, Comparable> params = new HashMap<String, Comparable>();
		String port = portName;
		String rate = "9600";
		String dataBit = "" + SerialPort.DATABITS_8;
		String stopBit = "" + SerialPort.STOPBITS_1;
		String parity = "" + SerialPort.PARITY_NONE;
		int parityInt = SerialPort.PARITY_NONE;
		params.put(SerialReader.PARAMS_PORT, port); // 端口名称
		params.put(SerialReader.PARAMS_RATE, rate); // 波特率
		params.put(SerialReader.PARAMS_DATABITS, dataBit); // 数据位
		params.put(SerialReader.PARAMS_STOPBITS, stopBit); // 停止位
		params.put(SerialReader.PARAMS_PARITY, parityInt); // 无奇偶校验
		params.put(SerialReader.PARAMS_TIMEOUT, 100); // 设备超时时间 1秒
		params.put(SerialReader.PARAMS_DELAY, 100); // 端口数据准备时间 1秒
		try {
			sr.open(params);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
