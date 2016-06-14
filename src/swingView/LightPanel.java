package swingView;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utils.ByteUtil;

public class LightPanel extends JPanel {
	
	static Light [] lights = {new Light("�Զ�")
			,new Light("�ֶ�"),new Light("��1"), new Light("��2")
			,new Light("Ѳ��"),new Light("��ˮ"),new Light("����ˮ"),new Light("ˮλ����")};
	
	public LightPanel(){
		
		setLayout(new GridLayout(1,8));//���񲼾֣�3��1��
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setOpaque(false);
		for(int i=0;i<lights.length;i++){
			add(lights[i]);
		}
	}
	
	/**
	 * ���µ���״̬
	 * 1��ʾ��һյ�ƣ�0000001��
	 * 2��ʾ�ڶ�յ����0000010��
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
