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
 * @date 2016年6月20日 上午11:45:55 
 * @version V1.0.0
 */
public class NumberPanel extends JPanel  {
	
	static NumberItem [] items = {new  NumberItem("电压（V）"),new  NumberItem("1泵电流（A）"),new  NumberItem("2泵电流（A）")};
	
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
