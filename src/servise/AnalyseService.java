package servise;

import dto.DeviceData;
import staticVal.WarningVals;
import swingView.LightPanel;
import swingView.NumberPanel;
import swingView.StatusPanel;
import utils.ByteUtil;

/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:42:00 
 * @version V1.0.0
 */
public class AnalyseService {
	public static StringBuffer msg = new StringBuffer();
	static int t = 0;

	/**
	 * ������2�Ľ���
	 * 
	 * 1x6: ��ˮָʾ 1x7: ����ˮָʾ 1x8: ˮλ����ָʾ 1x9: ���1�������ƽ�����ȱ�ࣩ
	 * 1x10:���2�������ƽ�����ȱ�ࣩ 1x11: ���1���� 1x12: ���2���� 1x13: ���1Ƿ�� 1x14: ���2Ƿ��
	 * 1x15: ���1��ʱ�޴��� 1x16: ���2��ʱ�޴��� 1x17: ��ѹ���� 1x18: Ƿѹ����
	 */
	public static void analyseForCodeTwo(byte[] message) {
		byte addr = message[0];

		if (DeviceData.isCurrentAddr(addr)) {
			
			byte[] b = ByteUtil.byteTo8BitArray(message[3]);
			
			DeviceData.selectedMode = b[0];
			LightPanel.updateStatus(b);
		}else{
			
		}

		byte b1 = message[4];
		byte b2 = message[5];

		if(WarningVals.decodeWarnings(addr, b1, b2)){
			StatusPanel.warning(WarningVals.getWarningInfo());
		}
		
	}
	
	/**
	 * ������4�Ľ���
	 * @param message
	 */
	public static void analyseForCodeFour(byte[] msg){
		
		 //��ѹֵ
        int voltage = ByteUtil.getIntWith2Byte(msg[3], msg[4]);
        //����1
        int current1 = ByteUtil.getIntWith2Byte(msg[5], msg[6]);
        //����2
        int current2 = ByteUtil.getIntWith2Byte(msg[7], msg[8]);
        

        String volStr = voltage+"";
        //����ֵ�����λĬ��Ϊ��С����
        String curStr1 = current1/100+"."+current1%100;
        String curStr2 = current2/100+"."+current2%100;
        
		NumberPanel.updateStatus(volStr,curStr1,curStr2);
	}
}
