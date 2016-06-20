package dto;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:40:05 
 * @version V1.0.0
 */
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
