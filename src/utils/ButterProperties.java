package utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:47:46 
 * @version V1.0.0
 */
public class ButterProperties {
	static String filePath = "butterfly.properties";

	// 根据Key读取Value
	public static String GetValueByKey(String key) {
		Properties pps = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			pps.load(in);
			String value = pps.getProperty(key);
			
			return value;

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static Integer GetIntegerValByKey(String key){
		String value = GetValueByKey(key);
		
		if(value == null || value.equalsIgnoreCase("null") || value.equals("")){
			return 0;
		}
		
		return Integer.parseInt(value);
	}
	// 读取Properties的全部信息
	public static void GetAllProperties() throws IOException {
		Properties pps = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		pps.load(in);
		Enumeration en = pps.propertyNames(); // 得到配置文件的名字

		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}

	}

	// 写入Properties信息
	public static void WriteProperties(String pKey, String pValue) throws IOException {
		Properties pps = new Properties();

		InputStream in = new FileInputStream(filePath);
		pps.load(in);
		OutputStream out = new FileOutputStream(filePath);
		pps.setProperty(pKey, pValue);
		pps.store(out, "Update " + pKey + " name");
	}

	public static void main(String[] args) throws IOException {
		String value = GetValueByKey("name");

		System.out.println(value);
		// System.out.println(value);
		 GetAllProperties();
		WriteProperties("long", "214");
	}
}
