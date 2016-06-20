package swingView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.DeviceData;
import dto.SettingNumberLimit;
import dto.SettingOptions;
import dto.SettingValDto;
import servise.InstructionQueen;
import staticVal.SettingType;
import swingView.define.ButterButton;
import swingView.define.ButterLabel;
import swingView.define.JNumberField;
import utils.ByteUtil;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:45:19 
 * @version V1.0.0
 */
public class EditRow extends JPanel {
	
	//提示
	JLabel label = new ButterLabel("");	
	//编辑空间
	JComponent editComponent;	
	//按钮
	JButton btn = new ButterButton("修改");
	
	int type;	
	SettingValDto settingItem;
	
	public EditRow(SettingValDto settingItem) {	
		this.settingItem = settingItem;
		this.type = settingItem.type;
		
		setLayout(new FlowLayout());
		setOpaque(false);
		
		label.setPreferredSize(new Dimension(100,20));
		label.setText(settingItem.name);
		add(label);
		
		if(type == SettingType.textType){
			JNumberField textField = new JNumberField(10);
			textField.setEnabled(false);
			editComponent= textField;
		}else if(type == SettingType.numberLimitType){
			SettingNumberLimit st = (SettingNumberLimit)settingItem.options;
			JNumberField numberField = new JNumberField(10,st.minVal,st.maxVal);
			numberField.setEnabled(false);
			editComponent= numberField;
		}else if (type == SettingType.numberType){
			JNumberField numberField = new JNumberField(10);
			numberField.setEnabled(false);
			editComponent= numberField;
		}else{
			SettingOptions[] options = (SettingOptions []) settingItem.options;
			JComboBox combox = new JComboBox(options); 
			combox.setEnabled(false);  
			combox.setMaximumRowCount(5);  
			combox.setPrototypeDisplayValue("1234567890123");
			editComponent= combox;
		}
		add(editComponent);
		
		btn.addActionListener(new ModifyBtnListener(editComponent,settingItem.index,settingItem.type));
		
		add(btn);
		
	}
	/**
	 * 根据设备的返回更新显示
	 * @param val
	 */
	public void updateVal(int val){

		if(this.type == SettingType.textType){
			JTextField textField =(JTextField)editComponent;
			textField.setText(val+"");
		}else if(this.type ==SettingType.numberLimitType || this.type ==SettingType.numberType){
			JNumberField numberField = (JNumberField)editComponent;
			numberField.setText(val+"");
		}else{
			SettingOptions[] options = (SettingOptions[])settingItem.options;
			JComboBox<String> combox = (JComboBox<String>) editComponent;
			combox.setSelectedIndex(SettingOptions.getIndex(options, val));
		}
	}
	
	class ModifyBtnListener implements ActionListener {
		
		JComponent item;
		int index;
		int type;
		
		public ModifyBtnListener(JComponent item,int index,int type){
			this.item = item;
			this.index = index;
			this.type = type;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			b.setText("保存");
			item.setEnabled(true);
			
			b.removeActionListener(this);
			b.addActionListener(new SaveBtnListener(item,index,type));
		}
	}
	
	
	class SaveBtnListener implements ActionListener {
		
		JComponent item;
		int index;
		int type ;
		
		public SaveBtnListener(JComponent item,int index,int type){
			this.item = item;
			this.index= index;
			this.type = type;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int val = 0;
			if(type == SettingType.numberLimitType){
				JNumberField t= (JNumberField)item;				
				if(t.valid()){
					val = t.getValue();
				}else{
					JOptionPane.showMessageDialog(null, "请确保输入值的正确性", "提示", JOptionPane.ERROR_MESSAGE);
					t.setText("");
				}
			}else if(type == SettingType.numberType){
				JNumberField t= (JNumberField)item;			
				val = t.getValue();
			} else{
				JComboBox<String> t = (JComboBox<String>) item;
				SettingOptions select = (SettingOptions)t.getSelectedItem();
				val = select.value;
			}
			byte[] data =ByteUtil.intTo2Byte(val);
			InstructionQueen.getInstance().addUrgent(new byte[]{DeviceData.getSelectedDeviceAddr(), 6, 0, (byte)index, data[0], data[1]});
			
			JButton b = (JButton) e.getSource();
			b.setText("修改");
			item.setEnabled(false);
			
			b.removeActionListener(this);
			b.addActionListener(new ModifyBtnListener(item,index,type));
			
		}
	}
	
	
	
	

}
