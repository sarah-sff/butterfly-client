package swingView.define;

//import javax.swing.JLabel;

public class AutoScrollLabel extends ButterLabel implements Runnable {
	
	
	volatile String content;
	
	int length = 80;
	
	
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
