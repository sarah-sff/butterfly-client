package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:48:43 
 * @version V1.0.0
 */
public class DateUtil {
	static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formatDate(Date date){
		return sf.format(date);
	}
}
