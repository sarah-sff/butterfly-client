package swingView;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dto.DeviceData;
import servise.WarningService;
import swingView.define.ButterFrame;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:46:45 
 * @version V1.0.0
 */
public class WarningRecordFrame extends ButterFrame {
	JPanel contentPanel;

	public WarningRecordFrame() {		
		super(DeviceData.proName+"-������¼");
		setSize(400, 400);
		setLocationRelativeTo(null);

		contentPanel = generateJPanel();

		JScrollPane scrollPane = new JScrollPane(contentPanel);
		setContentPane(scrollPane);
		setVisible(false);
		
		refresh();
		
		
	}

	private JPanel generateJPanel() {
		JPanel p = new JPanel();		
		p.setBackground(Color.white);
		return p;
	}
	
	/**
	 * ˢ��
	 */
	public void refresh(){
		contentPanel.removeAll();

		Vector<String> warnings = new Vector<String>(); 
	    JList<String> list = new JList<String>(warnings); 
	    
		List<String> records = WarningService.readHistoryWarnings();
		if(records == null|| records.size()==0){
			contentPanel.add(new JLabel("���ޱ�����¼"));
		}else{
			for(String s:records){
				warnings.addElement(s);
			}
			contentPanel.add(list);
		}
		
	}
}

