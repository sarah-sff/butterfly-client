package dto;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:40:12 
 * @version V1.0.0
 */
public class SettingValDto {
	
	public int type;
	public String key;
	public int index;
	public String name;
	public Object options;

	public SettingValDto(String key,int type, int index, String name, Object options) {
		super();
		this.key = key;
		this.type = type;
		this.index = index;
		this.name = name;
		this.options = options;
	}
	

}
