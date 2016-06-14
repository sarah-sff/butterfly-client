package servise;

import java.util.concurrent.ArrayBlockingQueue;

import dto.DeviceData;
import dto.InsRecord;

/**
 * 串口消息接收稳定性监控
 */
public class Stabler extends Thread {
	private static final Stabler single = new Stabler();
	// 允许超时的最大时间
	public static int MAX_TIME_PERIOD = 400;
	// 记录发送已指令的队列
	public ArrayBlockingQueue<InsRecord> insRecordQueen = new ArrayBlockingQueue<InsRecord>(512);

	// 单例
	private Stabler() {
	}

	public static Stabler getInstance() {
		return single;
	}

	/**
	 * 清除队列所有指令
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
	 * 消息接收情况检测
	 */
	public void check() {
		try {

			System.out.println("-----已发送但是未收到回复的指令个数----" + insRecordQueen.size());

			if (insRecordQueen.size() <= 0) {
				return;
			}

			InsRecord last = insRecordQueen.take();

			if (last == null)
				return;
			long passedTime = last.hasSendOutTimeLong();

			// 超过既定时间,重新往串口发起消息；否则重新放回队列
			if (passedTime > MAX_TIME_PERIOD) {
				DeviceData.connectionFailed(last.getAddr());
				System.out.println("超过" + MAX_TIME_PERIOD + "毫秒没有收到来自串口的消息，尝试再发送一次");
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
	 * 接收消息
	 */
	public void removeFromInsRecordQueue() {
		try {
			insRecordQueen.take();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 发送消息
	 */
	public void sendMsg(byte[] bytes) {
		insRecordQueen.add(new InsRecord(bytes));
	}

}
