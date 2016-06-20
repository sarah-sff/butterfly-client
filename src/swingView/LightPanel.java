package swingView;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:45:43 
 * @version V1.0.0
 */
public class LightPanel extends JPanel {
	
	static Light [] lights = {new Light("自动")
			,new Light("手动"),new Light("泵1"), new Light("泵2")
			,new Light("巡检"),new Light("无水"),new Light("半满水"),new Light("水位超高")};
	
	public LightPanel(){
		
		setLayout(new GridLayout(1,8));//网格布局，3行1列
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setOpaque(false);
		for(int i=0;i<lights.length;i++){
			add(lights[i]);
		}
	}
	
	/**
	 * 更新灯亮状态
	 */
	public static void updateStatus(byte[] b){
        for(int i = 0; i<b.length;i++){
        	if(b[i] == 1){
        		lights[i].lightOn();
        	}else{
        		lights[i].lightOff();
        	}
        }
		
	}
	
	public static void mask(){
		for(Light item:lights){
			item.lightOff();
		}
	}

}
