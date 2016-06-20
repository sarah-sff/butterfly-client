package swingView.define;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:47:10 
 * @version V1.0.0
 */
public class ButterFrame extends JFrame {
	/** 
     * 设置窗口图标 
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
