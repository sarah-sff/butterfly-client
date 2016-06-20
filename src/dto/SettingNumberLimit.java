package dto;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:39:52 
 * @version V1.0.0
 */
public class SettingNumberLimit {

	/**
	 * 最小值
	 */
	public int minVal;
	
	/**
	 * 最大值
	 */
	public int maxVal;

	public SettingNumberLimit(int minVal, int maxVal) {
		super();
		this.minVal = minVal;
		this.maxVal = maxVal;
	}
	
}
