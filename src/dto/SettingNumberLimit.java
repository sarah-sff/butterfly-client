package dto;

/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:39:52 
 * @version V1.0.0
 */
public class SettingNumberLimit {

	/**
	 * ��Сֵ
	 */
	public int minVal;
	
	/**
	 * ���ֵ
	 */
	public int maxVal;

	public SettingNumberLimit(int minVal, int maxVal) {
		super();
		this.minVal = minVal;
		this.maxVal = maxVal;
	}
	
}
