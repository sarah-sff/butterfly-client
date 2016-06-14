package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public class ByteUtil {
	
	/**
	 * 灏嗕袱涓瓧鑺傜殑byte杞垚int绫诲瀷
	 *
	 * @param high
	 * @param low
	 * @return
	 */
	public static int getIntWith2Byte(byte high, byte low) {

		return getUnsignedByte(high) * 256 + getUnsignedByte(low);
	}
	
	
	/**
	 * 将整型转为两个字节的byte类型
	 * @return
	 */
	public static byte[] intTo2Byte(int p){
		
		byte[] result = new byte[2];
		
		int high = p/256;
		
		int low = p%256;
		
		result[0] = getSignedByte(high);
		result[1] = getSignedByte(low);
		
		return result;
	}

	/**
	 * 灏嗚繛涓瓧鑺傜殑byte杞垚浜岃繘鍒跺瓧绗︿覆
	 *
	 * @param bytes
	 * @return
	 */
	public static String getBinaryStringWith2Byte(byte[] bytes) {

		if (bytes.length < 2)
			return "";

		if (bytes[0] == 0) {
			return Integer.toBinaryString(bytes[1]);
		}

		return Integer.toBinaryString(bytes[0]) + Integer.toBinaryString(bytes[1]);

	}
	
	/**
	 * byte转成位（用数组表示）
	 * @param b
	 * @return
	 */
	public static byte[] byteTo8BitArray(byte b){
		byte[] r = new byte[8];
		int param =getUnsignedByte(b);
		int i =0;
		while(param>0){
			r[i] = (byte) (param%2);
			param = param/2;
			i++;
		}
		
		return r;
		
	}

	/**
	 * 将byte转成二进制表达，且0->false,1->true
	 * @param b
	 * @return
	 */
	public static List<Boolean> byteToBoolList(byte b) {
		String binaryStr = Integer.toBinaryString(b);
		List<Boolean> boolList = new ArrayList<Boolean>();
		
		//补足8位
		int len = binaryStr.length();
        int i = 0;
        while (i < 7 - len) {
            binaryStr = "0" + binaryStr;
            i++;
        }
        
		String[] status = binaryStr.split("");
		for (int index = status.length-1; index >=0; index--) {
			if(status[index].equals("0")){
				boolList.add(false);
			}else if(status[index].equals("1")){
				boolList.add(true);
			}
		}
		return boolList;
	}

	/**
	 * 鏈夌鍙疯浆鏃犵鍙�
	 * 
	 * @param b
	 * @return
	 */
	public static int getUnsignedByte(byte b) {

		int r = b;
		if (r < 0) {
			r += 256;
		}

		return r;

	}
	
	
	/**
	 * 鏈夌鍙疯浆鏃犵鍙�
	 * 
	 * @param b
	 * @return
	 */
	public static byte getSignedByte(int b) {

		
		if (b >127) {
			return (byte)(b-256);
		}

		return (byte)b;

	}




}
