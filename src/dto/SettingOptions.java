package dto;

public class SettingOptions {

	/*
	 * 下拉列表位置指标
	 */
	public int index;
	/*
	 * 显示文字
	 */
	public String text;
	/*
	 * 值
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
	 * 根据值获取下标
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
