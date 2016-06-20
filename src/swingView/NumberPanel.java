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
 * @date 2016��6��20�� ����11:45:55 
 * @version V1.0.0
 */
public class NumberPanel extends JPanel  {
	
	static NumberItem [] items = {new  NumberItem("��ѹ��V��"),new  NumberItem("1�õ�����A��"),new  NumberItem("2�õ�����A��")};
	
	public NumberPanel(){
		setLayout(new GridLayout(1, 3));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setOpaque(false);
		for(int i=0;i<items.length;i++){
			add(items[i]);
		}
	}
	
	public static void updateStatus(String vol,String cur1,String cur2){
        items[0].updateNumber(vol);        
        items[1].updateNumber(cur1);
        items[2].updateNumber(cur2);
	}
	
	
	public static void mask(){
		
		for(NumberItem e:items){
			e.clear();
		}
		
	}

}
