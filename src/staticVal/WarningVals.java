package staticVal;

import java.util.HashMap;
import java.util.Map;
import dto.DeviceAddress;
import dto.WarningCode;
import dto.WarningInfo;
import utils.ByteUtil;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:44:04 
 * @version V1.0.0
 */
public class WarningVals {

	// 报警信息
	public static Map<Integer, WarningInfo> warningDataMap = new HashMap<Integer, WarningInfo>();

	public static String getWarningInfo() {
		StringBuffer sb = new StringBuffer();
		for (Integer key : warningDataMap.keySet()) {
			sb.append(warningDataMap.get(key).toString() + "  ");
		}
		return sb.toString();
	}
	/*
	 * 1x9: 电机1相电流不平衡错误（缺相） 1x10:电机2相电流不平衡错误（缺相） 1x11: 电机1过流 1x12: 电机2过流 1x13:
	 * 电机1欠流 1x14: 电机2欠流 1x15: 电机1反时限错误 1x16: 电机2反时限错误 1x17: 过压错误 1x18: 欠压错误
	 */

	public final static WarningCode[] warning1s = { new WarningCode(0, "电机1相电流不平衡错误（缺相）"),
			new WarningCode(1, "电机2相电流不平衡错误（缺相）"), new WarningCode(2, "电机1过流"), new WarningCode(3, "电机2过流"),
			new WarningCode(4, "电机1欠流"), new WarningCode(5, "电机2欠流"), new WarningCode(6, "电机1反时限错误 "),
			new WarningCode(7, "电机2反时限错误 ") };

	public final static WarningCode[] warning2s = { new WarningCode(0, "过压错误 "), new WarningCode(1, "欠压错误") };

	/**
	 * decode warning
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static boolean decodeWarnings(byte addr, byte b1, byte b2) {
		StringBuffer infoStr = new StringBuffer();
		// addr为实际地址，根据addr生成DeviceAddress对象
		DeviceAddress deviceAddress = new DeviceAddress(addr);
		byte[] bit1 = ByteUtil.byteTo8BitArray(b1);
		// 记录下标
		StringBuffer indexStr = new StringBuffer();
		for (int i = 0; i < bit1.length && i < warning1s.length; i++) {
			if (bit1[i] == 1) {
				indexStr.append(i);
				infoStr.append(warning1s[i].text + ",");
			}
		}

		byte[] bit2 = ByteUtil.byteTo8BitArray(b1);
		for (int i = 0; i < bit2.length && i < warning2s.length; i++) {
			if (bit2[i] == 1) {
				indexStr.append(i);
				infoStr.append(warning2s[i].text + ",");
			}
		}

		WarningInfo lastInfo = WarningVals.warningDataMap.get(deviceAddress.addr);
		
		//警报消除
		if(indexStr.length()<=0 && lastInfo!= null){
			lastInfo.over();
			WarningVals.warningDataMap.remove(deviceAddress.addr);
			return true;
		}

		if (indexStr.length() > 0) {
			WarningInfo warningInfo = new WarningInfo(deviceAddress.addr, indexStr.toString(), infoStr.toString());

			// 第一次出现警报 或 警报内容变更 
			if (lastInfo == null || !warningInfo.equal(lastInfo)) {
				warningInfo.record();
				WarningVals.warningDataMap.put(deviceAddress.addr, warningInfo);
				return true;
			}
		}
		
		return false;

	}

}
