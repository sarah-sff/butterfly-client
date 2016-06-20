package define;

import java.util.concurrent.ArrayBlockingQueue;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:37:17 
 * @version V1.0.0
 */
public class PollingBlockQueue extends ArrayBlockingQueue {

	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 1L;

	public PollingBlockQueue(int capacity) {
		super(capacity);
	}
	
	/**
	 * ��дpoll����
	 */
	public Object poll(){	
		if(this.size()>0){
			Object object= super.poll();		
			boolean reOffer = offer(object);		
			byte times = 0 ;
			//��������
			while(!reOffer && times<=3){
				reOffer = offer(object);
				times++;
			}
			
			return object;
		}
		
		return null;
		
	}
	
	/**
	 * ��дtake����
	 */
	public Object take(){			
		return poll();
	}
}
