package swingView.define;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ButterFrame extends JFrame {
	/** 
     * ���ô���ͼ�� 
     */  
    public void setWindowIcon()  
    {  
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/butter.png"));  
        this.setIconImage(imageIcon.getImage());  
    } 
    
    public ButterFrame(){
    	setWindowIcon() ;
    }
    
    public ButterFrame(String title){
    	setTitle(title);
    	setWindowIcon() ;
    }
    
}
