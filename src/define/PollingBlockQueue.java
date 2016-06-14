package define;

import java.util.concurrent.ArrayBlockingQueue;

public class PollingBlockQueue extends ArrayBlockingQueue {

	public PollingBlockQueue(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	
	/*
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
	
	/*
	 * ��дtake����
	 */
	public Object take(){			
		return poll();
	}
	
	
	

}
