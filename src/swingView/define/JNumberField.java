package swingView.define;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class JNumberField extends JTextField {
	
	int minValue = 0;
	int maxValue = 0;
	public JNumberField(int columns,int minValue, int maxValue) {
		super(columns);
		this.minValue = minValue;
		this.maxValue = maxValue;
		addKeyListener(new NumberFieldKeyListener());
	}
	public JNumberField(int columns) {
		super(columns);
		addKeyListener(new NumberFieldKeyListener());
		
	}
	
	public void showError(){
		setForeground(Color.RED);
	}
	
	public boolean valid(){
		String str = getText();
		if("".equals(str)){
			return false;
		}
		int val = getValue();
		if(minValue>=maxValue){
			return true;
		}
		return minValue< maxValue &&( val>= minValue && val <= maxValue);
	}
	
	public void noError(){
		setForeground(Color.BLACK);
	}
	
	public int getValue(){
		String str = getText();
		int val =Integer.parseInt(str);
		return val;
		
	}
	
	

}

class NumberFieldKeyListener implements KeyListener{
	@Override
	public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar(); // 获取键盘输入的字符
        if (Character.isDigit(c)) // 判断输入是否是数字
        	{
             return; // true,返回
        	}
       
        e.consume(); // false,消毁不匹配的输入
        
        
        
		
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JNumberField t = (JNumberField)e.getSource();		
		try{
			//有设置最小值和最大值
			if(!t.valid()){
				t.showError();
			}else{
				t.noError();
			}
		}catch(Exception ex){
			System.err.println(ex);
		}
		
	}
}


