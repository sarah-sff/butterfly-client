package swingView;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import servise.WarningService;
import swingView.define.ButterFrame;

public class WarningRecordFrame extends ButterFrame {
	JPanel contentPanel;

	public WarningRecordFrame() {		
		super("butterfly-报警记录");
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
	 * 刷新
	 */
	public void refresh(){
		contentPanel.removeAll();

		Vector<String> warnings = new Vector<String>(); 
	    JList<String> list = new JList<String>(warnings); 
	    
		List<String> records = WarningService.readHistoryWarnings();
		if(records == null|| records.size()==0){
			contentPanel.add(new JLabel("暂无报警记录"));
		}else{
			for(String s:records){
				warnings.addElement(s);
			}
			contentPanel.add(list);
		}
		
	}
}

