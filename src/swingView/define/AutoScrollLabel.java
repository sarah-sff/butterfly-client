package swingView.define;

import java.awt.Font;

/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:46:57 
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
