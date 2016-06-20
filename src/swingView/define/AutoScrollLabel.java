package swingView.define;

import java.awt.Font;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:46:57 
 * @version V1.0.0
 */
public class AutoScrollLabel extends ButterLabel implements Runnable {
	
	
	volatile String content;
	
	int length = 30;
	
	
	boolean scroll = true;
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public AutoScrollLabel(String content) {
		super(content);
		this.content = content;
		setFont(new Font(Font.MONOSPACED,Font.LAYOUT_LEFT_TO_RIGHT,14));
//		setForeground(new Color(255 ,140 ,105));
	}
	
	
	
	@Override
	public void run() {		
		while(scroll){
			if(content.length()<=length){
				setText(content);
//				return;
			}else{
				for(int i=0;i<content.length();i++){
					String show = content.substring(i);
					
					setText(show);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
			
		}
		
	}
}
