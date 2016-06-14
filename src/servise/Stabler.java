package servise;

import java.util.concurrent.ArrayBlockingQueue;

import dto.DeviceData;
import dto.InsRecord;

/**
 * ������Ϣ�����ȶ��Լ��
 */
public class Stabler extends Thread {
	private static final Stabler single = new Stabler();
	// ����ʱ�����ʱ��
	public static int MAX_TIME_PERIOD = 400;
	// ��¼������ָ��Ķ���
	public ArrayBlockingQueue<InsRecord> insRecordQueen = new ArrayBlockingQueue<InsRecord>(512);

	// ����
	private Stabler() {
	}

	public static Stabler getInstance() {
		return single;
	}

	/**
	 * �����������ָ��
	 */
	public void clear() {
		insRecordQueen.clear();
	}

	public void run(){
		while (true) {
			try {
				Thread.sleep(1000);
				check();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * ��Ϣ����������
	 */
	public void check() {
		try {

			System.out.println("-----�ѷ��͵���δ�յ��ظ���ָ�����----" + insRecordQueen.size());

			if (insRecordQueen.size() <= 0) {
				return;
			}

			InsRecord last = insRecordQueen.take();

			if (last == null)
				return;
			long passedTime = last.hasSendOutTimeLong();

			// �����ȶ�ʱ��,���������ڷ�����Ϣ���������·Żض���
			if (passedTime > MAX_TIME_PERIOD) {
				DeviceData.connectionFailed(last.getAddr());
				System.out.println("����" + MAX_TIME_PERIOD + "����û���յ����Դ��ڵ���Ϣ�������ٷ���һ��");
				SerialService.sendNextInstruction();
				
			} else {
				insRecordQueen.add(last);
			}
		} catch (InterruptedException e) {

			System.err.println(e);
			
		} finally {
			
		}
	}

	/**
	 * ������Ϣ
	 */
	public void removeFromInsRecordQueue() {
		try {
			insRecordQueen.take();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ������Ϣ
	 */
	public void sendMsg(byte[] bytes) {
		insRecordQueen.add(new InsRecord(bytes));
	}

}
