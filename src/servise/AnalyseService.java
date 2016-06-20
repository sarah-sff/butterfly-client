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
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:42:00 
 * @version V1.0.0
 */
public class AnalyseService {
	public static StringBuffer msg = new StringBuffer();
	static int t = 0;

	/**
	 * 功能码2的解析
	 * 
	 * 1x6: 无水指示 1x7: 半满水指示 1x8: 水位超高指示 1x9: 电机1相电流不平衡错误（缺相）
	 * 1x10:电机2相电流不平衡错误（缺相） 1x11: 电机1过流 1x12: 电机2过流 1x13: 电机1欠流 1x14: 电机2欠流
	 * 1x15: 电机1反时限错误 1x16: 电机2反时限错误 1x17: 过压错误 1x18: 欠压错误
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
	 * 功能码4的解析
	 * @param message
	 */
	public static void analyseForCodeFour(byte[] msg){
		
		 //电压值
        int voltage = ByteUtil.getIntWith2Byte(msg[3], msg[4]);
        //电流1
        int current1 = ByteUtil.getIntWith2Byte(msg[5], msg[6]);
        //电流2
        int current2 = ByteUtil.getIntWith2Byte(msg[7], msg[8]);
        

        String volStr = voltage+"";
        //电流值最后两位默认为是小数点
        String curStr1 = current1/100+"."+current1%100;
        String curStr2 = current2/100+"."+current2%100;
        
		NumberPanel.updateStatus(volStr,curStr1,curStr2);
	}
}
