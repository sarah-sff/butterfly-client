package dto;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:40:17 
 * @version V1.0.0
 */
public class WarningCode {
	
	//字节里对应位的下标
	public int index;
	
	//告警说明
	public String text;
	
	public WarningCode(int index, String text) {
		super();
		this.index = index;
		this.text = text;
	}
	
	

}
