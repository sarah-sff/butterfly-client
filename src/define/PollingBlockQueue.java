package define;

import java.util.concurrent.ArrayBlockingQueue;

public class PollingBlockQueue extends ArrayBlockingQueue {

	public PollingBlockQueue(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 重写poll方法
	 */
	public Object poll(){	
		if(this.size()>0){
			Object object= super.poll();		
			boolean reOffer = offer(object);		
			byte times = 0 ;
			//重试三次
			while(!reOffer && times<=3){
				reOffer = offer(object);
				times++;
			}
			
			return object;
		}
		
		return null;
		
	}
	
	/*
	 * 重写take方法
	 */
	public Object take(){			
		return poll();
	}
	
	
	

}
