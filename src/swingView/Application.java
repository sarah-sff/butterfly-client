package swingView;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import dto.DeviceData;
import servise.InstructionQueen;
import servise.Stabler;
import staticVal.PropKey;
import swingView.define.ButterFrame;
import utils.ButterProperties;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:44:51 
 * @version V1.0.0
 */
public class Application extends ButterFrame {
	
	
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 1L;
	
	
	static InitialFrame initialFrame = null;
	static Application mainFrame = null;
	
	Panel panel = new Panel();

	Application() {
		super(DeviceData.proName+"-水泵控制桌面");
		initGUI();
		
		if(ButterProperties.GetIntegerValByKey(PropKey.PUMP_NUM) == 0){
			showInitial();
		}else{
			InstructionQueen.getInstance().refresh();
		}
	}
	
	public static void showMain(){
		mainFrame.setVisible(true);
		initialFrame.setVisible(false);
	}
	

	public static void showInitial(){
		if(initialFrame == null){
			initialFrame = new InitialFrame();
		}		
		initialFrame.setVisible(true);
	}
	
	
	public static void showInitial(int val){
		if(initialFrame == null){
			initialFrame = new InitialFrame();
		}
		
		initialFrame.setVisible(true);
		initialFrame.setValue(val);
	}

	private void initGUI() {
		setVisible(true);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		add(panel);		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				 Stabler.getInstance().start();
				 Application.mainFrame = new Application();
				 
			}
		});
	}
}
