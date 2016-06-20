package define;

import java.util.concurrent.ArrayBlockingQueue;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:37:17 
 * @version V1.0.0
 */
public class PollingBlockQueue extends ArrayBlockingQueue {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	public PollingBlockQueue(int capacity) {
		super(capacity);
	}
	
	/**
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
	
	/**
	 * 重写take方法
	 */
	public Object take(){			
		return poll();
	}
}
