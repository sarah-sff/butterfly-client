package dto;

public class SettingOptions {

	/*
	 * �����б�λ��ָ��
	 */
	public int index;
	/*
	 * ��ʾ����
	 */
	public String text;
	/*
	 * ֵ
	 */
	public int value;
	public SettingOptions(int index,int value, String text) {
		super();
		this.index = index;
		this.text = text;
		this.value = value;
	}
	
	public String toString(){
		return text;
	}
	
	/**
	 * ����ֵ��ȡ�±�
	 * @param options
	 * @param val
	 * @return
	 */
	public static int getIndex(SettingOptions[] options ,int val){
		if(options==null || options.length ==0){
			return 0;
		}
		for(SettingOptions item:options){
			if(item.value == val){
				return item.index;
			}
		}
		return 0;
	}
	
	
}
