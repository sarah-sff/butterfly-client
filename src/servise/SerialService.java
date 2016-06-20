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
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:42:22 
 * @version V1.0.0
 */
public class SerialService {

	private static SerialReader sr = new SerialReader();
	private static String portName = "";

	public static void setPort(String portNameString) {
		portName = portNameString;
		
		SerialService observer = new SerialService();
		observer.openPort();
		
		// ���ָ�������ָ��򴮿ڷ�����Ϣ
		if (InstructionQueen.getInstance().size() > 0) {
			sendout();
		}

	}

	/**
	 * ��ȡ��ǰ���õĶ˿�
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
	 * �����ڷ�������,ʵ��˫��ͨѶ.
	 */
	private static void send(byte[] message) {
		SerialService observer = new SerialService();
		observer.sendMsg(message);

		// ��¼����ʱ��
		Stabler.getInstance().sendMsg(message);
	}

	/**
	 * ��ʼ�򴮿ڷ��Ͷ���
	 */
	public static void sendout() {
		if (portName != null && portName.length() > 0) {
			InstructionQueen.getInstance().refreshPollingInstruction();
			// ��ʼ����ָ��
			SerialService.sendNextInstruction();
		}
	}

	/**
	 * ������һ��ָ��
	 */
	public static void sendNextInstruction() {

		// ָ�����Ϊ�գ�������
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
	 * �������ؽ��
	 *
	 * @param message
	 */
	public static void doAnalyse(byte[] message) {

		byte addrCode = message[0];

		DeviceData.connectionSucceed(addrCode);
		// ���ݳ���У��
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
	 * ���ʹ�����Ϣ
	 * @param message
	 */
	public void sendMsg(byte[] message){
		if (message != null && message.length != 0) {
			sr.start();
			sr.run(CRC16.addCRCChecker(message));
		}
	}
	
	/**
	 * ���ʹ�����Ϣ
	 * @param message
	 */
	public void openPort(){
		openSerialPort(null);
	}

	/**
	 * �򿪴���
	 */
	public void openSerialPort(byte[] message) {
		HashMap<String, Comparable> params = new HashMap<String, Comparable>();
		String port = portName;
		String rate = "9600";
		String dataBit = "" + SerialPort.DATABITS_8;
		String stopBit = "" + SerialPort.STOPBITS_1;
		String parity = "" + SerialPort.PARITY_NONE;
		int parityInt = SerialPort.PARITY_NONE;
		params.put(SerialReader.PARAMS_PORT, port); // �˿�����
		params.put(SerialReader.PARAMS_RATE, rate); // ������
		params.put(SerialReader.PARAMS_DATABITS, dataBit); // ����λ
		params.put(SerialReader.PARAMS_STOPBITS, stopBit); // ֹͣλ
		params.put(SerialReader.PARAMS_PARITY, parityInt); // ����żУ��
		params.put(SerialReader.PARAMS_TIMEOUT, 100); // �豸��ʱʱ�� 1��
		params.put(SerialReader.PARAMS_DELAY, 100); // �˿�����׼��ʱ�� 1��
		try {
			sr.open(params);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
