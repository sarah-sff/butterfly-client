package swingView.define;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:47:10 
 * @version V1.0.0
 */
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
