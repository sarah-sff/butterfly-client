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
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:44:04 
 * @version V1.0.0
 */
public class WarningVals {

	// ������Ϣ
	public static Map<Integer, WarningInfo> warningDataMap = new HashMap<Integer, WarningInfo>();

	public static String getWarningInfo() {
		StringBuffer sb = new StringBuffer();
		for (Integer key : warningDataMap.keySet()) {
			sb.append(warningDataMap.get(key).toString() + "  ");
		}
		return sb.toString();
	}
	/*
	 * 1x9: ���1�������ƽ�����ȱ�ࣩ 1x10:���2�������ƽ�����ȱ�ࣩ 1x11: ���1���� 1x12: ���2���� 1x13:
	 * ���1Ƿ�� 1x14: ���2Ƿ�� 1x15: ���1��ʱ�޴��� 1x16: ���2��ʱ�޴��� 1x17: ��ѹ���� 1x18: Ƿѹ����
	 */

	public final static WarningCode[] warning1s = { new WarningCode(0, "���1�������ƽ�����ȱ�ࣩ"),
			new WarningCode(1, "���2�������ƽ�����ȱ�ࣩ"), new WarningCode(2, "���1����"), new WarningCode(3, "���2����"),
			new WarningCode(4, "���1Ƿ��"), new WarningCode(5, "���2Ƿ��"), new WarningCode(6, "���1��ʱ�޴��� "),
			new WarningCode(7, "���2��ʱ�޴��� ") };

	public final static WarningCode[] warning2s = { new WarningCode(0, "��ѹ���� "), new WarningCode(1, "Ƿѹ����") };

	/**
	 * decode warning
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static boolean decodeWarnings(byte addr, byte b1, byte b2) {
		StringBuffer infoStr = new StringBuffer();
		// addrΪʵ�ʵ�ַ������addr����DeviceAddress����
		DeviceAddress deviceAddress = new DeviceAddress(addr);
		byte[] bit1 = ByteUtil.byteTo8BitArray(b1);
		// ��¼�±�
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
		
		//��������
		if(indexStr.length()<=0 && lastInfo!= null){
			lastInfo.over();
			WarningVals.warningDataMap.remove(deviceAddress.addr);
			return true;
		}

		if (indexStr.length() > 0) {
			WarningInfo warningInfo = new WarningInfo(deviceAddress.addr, indexStr.toString(), infoStr.toString());

			// ��һ�γ��־��� �� �������ݱ�� 
			if (lastInfo == null || !warningInfo.equal(lastInfo)) {
				warningInfo.record();
				WarningVals.warningDataMap.put(deviceAddress.addr, warningInfo);
				return true;
			}
		}
		
		return false;

	}

}
