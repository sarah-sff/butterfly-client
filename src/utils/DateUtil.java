package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:48:43 
 * @version V1.0.0
 */
public class DateUtil {
	static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formatDate(Date date){
		return sf.format(date);
	}
}
