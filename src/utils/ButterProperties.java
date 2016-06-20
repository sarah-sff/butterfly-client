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
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:47:46 
 * @version V1.0.0
 */
public class ButterProperties {
	static String filePath = "butterfly.properties";

	// ����Key��ȡValue
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
	// ��ȡProperties��ȫ����Ϣ
	public static void GetAllProperties() throws IOException {
		Properties pps = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		pps.load(in);
		Enumeration en = pps.propertyNames(); // �õ������ļ�������

		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}

	}

	// д��Properties��Ϣ
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
