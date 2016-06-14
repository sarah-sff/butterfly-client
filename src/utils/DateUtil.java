package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formatDate(Date date){
		return sf.format(date);
	}
}
