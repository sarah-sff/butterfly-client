package swingView;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utils.ByteUtil;

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
	 * 1表示第一盏灯（0000001）
	 * 2表示第二盏两（0000010）
	 */
	public static void updateStatus(byte[] msg){
		
		byte[] b = ByteUtil.byteTo8BitArray(msg[3]);
       
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
