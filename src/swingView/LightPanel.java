package swingView;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:45:43 
 * @version V1.0.0
 */
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
