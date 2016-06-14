package dto;

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
