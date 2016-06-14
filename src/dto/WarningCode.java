package dto;

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
