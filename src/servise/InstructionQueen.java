package servise;

import java.util.concurrent.ArrayBlockingQueue;

import define.PollingBlockQueue;
import dto.DeviceAddress;
import dto.DeviceData;

/**
 * 
 * @Description ָ����� urgentQueen����Ž���ָ�� pollingQueen: �����ѯָ��
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:42:09 
 * @version V1.0.0
 */
public class InstructionQueen {

	private static final InstructionQueen single = new InstructionQueen();

	/**
	 * ��������,�������ú�������Ϣ(д����)
	 */
	public ArrayBlockingQueue<byte[]> urgentQueen = new ArrayBlockingQueue<byte[]>(256);
	/**
	 * ����ѭ������,���ڶ�ȡָʾ�ƺ͵�Դ/��ѹ
	 */
	public PollingBlockQueue commonPollingQueen = new PollingBlockQueue(2);

	/**
	 * ����ѭ������,���ڶ�ȡָʾ�ƺ͵�Դ/��ѹ
	 */
	public PollingBlockQueue warningPollingQueen = new PollingBlockQueue(256);

	private int commonTimes = 0;

	private int maxCommonPollingNum = 3;

	// ����
	private InstructionQueen() {
	}

	public static InstructionQueen getInstance() {
		return single;
	}

	/**
	 * ���д�С
	 *
	 * @return
	 */
	public int size() {
		return urgentQueen.size() + commonPollingQueen.size()+ warningPollingQueen.size();
	}

	/**
	 * ���ӽ���element
	 *
	 * @param element
	 */
	public void addUrgent(byte[] element) {

		if (urgentQueen.size() < 256) {
			urgentQueen.add(element);
		} else {
			System.out.println("��������Ԫ�ع���,ֱ��������ָ��... %s " + element.toString());
		}
	}

	/**
	 * ������ͨelement
	 *
	 * @param element
	 */
	public void addCommon(Object element) {

		if (commonPollingQueen.size() < 2) {
			commonPollingQueen.add(element);
		}

	}
	
	
	/**
	 * ������ͨelement
	 *
	 * @param element
	 */
	public void addWarn(Object element) {
		if (warningPollingQueen.size() <= 256) {
			warningPollingQueen.add(element);
		}

	}

	/**
	 * ��ȡԪ�� ����������������У����ý������е� ��������ͨ���е�
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
	 * ˢ��ѭ��ָ�����
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
	 * ���ָ��
	 */
	public void clearInstruction(){
		commonPollingQueen.clear();
		urgentQueen.clear();
	}
	
	
	/*
	 * ��ʼ��������ѯ����
	 */
	public void refreshWarningPollingInstruction(){
		DeviceAddress[] deviceAddresses = DeviceData.getAllDeviceAdress();
		for( DeviceAddress addr:deviceAddresses ){
			byte[] queryCode = new byte[] {addr.realAddr, 2, 0, 0, 0, 18 };
			addWarn(queryCode);
		}
	}
	
	
	/**
	 * ָ�����ˢ�£��豸�������������ʱ��
	 * 1�����ľ�����ѯָ�����
	 * 2���������common��urgentָ�����
	 * 3�����������
	 * 4������ָ��
	 */
	public void refresh(){
		refreshWarningPollingInstruction();
		clearInstruction();
		
		Stabler.getInstance().clear();
		
		SerialService.sendout();
	}


}
