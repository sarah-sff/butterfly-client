package staticVal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.SettingNumberLimit;
import dto.SettingOptions;
import dto.SettingValDto;
import utils.ByteUtil;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:43:53 
 * @version V1.0.0
 */
public class SettingVals {

	private static List<SettingValDto> settingList  = new ArrayList<SettingValDto>();

	private static void initSettingValues() {
		SettingValDto password = new SettingValDto("password",SettingType.numberLimitType, 0, "管理员密码", new SettingNumberLimit(0, 999));
		
		SettingValDto rs485address = new SettingValDto("rs485address",SettingType.numberLimitType, 1, "RS485地址", new SettingNumberLimit(1, 255));

		SettingOptions[] options = { 
				new SettingOptions(0,0, "有换泵功能"), 
				new SettingOptions(1,1, "固定1号泵"),
				new SettingOptions(2,2, "固定2号泵") };
		SettingValDto choosePump = new SettingValDto("choosePump",SettingType.comboType, 2, "换泵选择", options);

		SettingOptions[] choosePoolOptions = { 
				new SettingOptions(0,0, "上水池（补水），水位高停低开"), 
				new SettingOptions(1,1, "下水池（排水），水位高开低停")};
		SettingValDto choosePool = new SettingValDto("choosePool",SettingType.comboType, 3, "上下水池选择", choosePoolOptions);

		SettingOptions[] chooseHeadOptions = { 
				new SettingOptions(0,0, "浮球开关"), 
				new SettingOptions(1,1, "电极探头"), 
				new SettingOptions(1,1, "电接点压力表")};
		SettingValDto reserved2 = new SettingValDto("chooseHead",SettingType.comboType, 4, "水位探头选择", chooseHeadOptions);

		SettingOptions[] restartTimeOptions = { 
				new SettingOptions(0,2, "2秒"), 
				new SettingOptions(1,3, "3秒"),
				new SettingOptions(2,4, "4秒"), 
				new SettingOptions(3,5, "5秒"), 
				new SettingOptions(4,6, "6秒") };
		SettingValDto restartTime = new SettingValDto("restartTime",SettingType.comboType, 5, "屏蔽启动时间", restartTimeOptions);

		SettingOptions[] protectModeOptions = { 
				new SettingOptions(0,0, "定时限打开"), 
				new SettingOptions(0,1, "反时限打开") 
				};
		SettingValDto protectMode = new SettingValDto("protectMode",SettingType.comboType, 6, "保护选择", protectModeOptions);

		SettingOptions[] motorPowerOptions = { 
				new SettingOptions(0,0, "0.55kw"),
				new SettingOptions(1,1, "0.75kw"),
				new SettingOptions(2,2, "1.1kw"), 
				new SettingOptions(3, 3,"1.5kw"), 
				new SettingOptions(4,4, "2.2kw"),
				new SettingOptions(5,5, "3.0kw"), 
				new SettingOptions(6,6, "4.0kw"), 
				new SettingOptions(7,7, "5.5kw"),
				new SettingOptions(8, 8,"7.5kw") };
		SettingValDto motorPower = new SettingValDto("motorPower",SettingType.comboType, 7, "电机功率", motorPowerOptions);

		SettingOptions[] overCurrentProtectValOptions = { 
				new SettingOptions(0,0, "关闭过流保护功能"),
				new SettingOptions(1,1, "过流120%"), 
				new SettingOptions(2,2, "过流130%"), 
				new SettingOptions(3, 3,"过流140%") 
				};
		SettingValDto overCurrentProtectVal = new SettingValDto("overCurrentProtectVal",SettingType.comboType, 8, "过流保护值",
				overCurrentProtectValOptions);
		
		SettingNumberLimit st = new SettingNumberLimit(5,120);
		SettingValDto overCurrentProtectDelay = new SettingValDto("overCurrentProtectDelay",SettingType.numberLimitType, 9, "过流保护延时", st);

		SettingOptions[] underCurrentProtectValOptions = { 
				new SettingOptions(0,0, "关闭欠流保护功能"),
				new SettingOptions(1,1, "欠流50%"), 
				new SettingOptions(2,2, "欠流65%"), 
				new SettingOptions(3,4, "欠流80%") };
		SettingValDto underCurrentProtectVal = new SettingValDto("underCurrentProtectVal",SettingType.comboType, 10, "欠流保护值",
				underCurrentProtectValOptions);

		st = new SettingNumberLimit(5,120);
		SettingValDto underCurrentProtectDelay = new SettingValDto("underCurrentProtectDelay",SettingType.numberLimitType, 11, "欠流保护延时", st);

		SettingOptions[] voltageOptions = {
				new SettingOptions(0,0, "220v"), 
				new SettingOptions(1,1, "380v") 
				};
		SettingValDto voltage = new SettingValDto("voltage",SettingType.comboType, 12, "电压选择", voltageOptions);

		SettingValDto overVoltageProtectVal = new SettingValDto("overVoltageProtectVal",SettingType.numberLimitType, 13, "过压保护值",
				new SettingNumberLimit(220, 450));

		st = new SettingNumberLimit(5,120);
		SettingValDto overVoltageProtectDelay = new SettingValDto("overVoltageProtectDelay",SettingType.numberLimitType, 14, "过压保护延时", st);

		SettingValDto underVoltageProtectVal = new SettingValDto("underVoltageProtectVal",SettingType.numberLimitType, 15, "欠压保护值",
				new SettingNumberLimit(180, 380));

		st = new SettingNumberLimit(5,120);
		SettingValDto underVoltageProtectDelay = new SettingValDto("underVoltageProtectDelay",SettingType.numberLimitType, 16, "欠压保护延时",st);

		st = new SettingNumberLimit(6,45);
		SettingValDto inspectPeriod = new SettingValDto("inspectPeriod",SettingType.numberLimitType, 17, "巡检周期", st);

		st = new SettingNumberLimit(3,120);
		SettingValDto inspectTime = new SettingValDto("inspectTime",SettingType.numberLimitType, 18, "巡检时间", st);

		st = new SettingNumberLimit(3,10);
		SettingValDto switchDelay = new SettingValDto("switchDelay",SettingType.numberLimitType, 19, "切换延时", st);

		st = new SettingNumberLimit(0,9999);
		SettingValDto voltageCalibration = new SettingValDto("voltageCalibration",SettingType.numberLimitType, 20, "电压校准", st);

		st = new SettingNumberLimit(0,9999);
		SettingValDto current1Calibration = new SettingValDto("current1Calibration",SettingType.numberLimitType, 21, "电流1校准", st);

		st = new SettingNumberLimit(0,9999);
		SettingValDto current2Calibration = new SettingValDto("current2Calibration",SettingType.numberLimitType, 22, "电流2校准", st);

		SettingOptions[] ledBrightnessOptions = { 
				new SettingOptions(0,1, "1"), 
				new SettingOptions(1,2, "2"),
				new SettingOptions(2,3, "3"), 
				new SettingOptions(3,4, "4"), 
				new SettingOptions(4,5, "5"),
				new SettingOptions(5,6, "6"), 
				new SettingOptions(6,7, "7") 
				};
		SettingValDto ledBrightness = new SettingValDto("ledBrightness",SettingType.comboType, 23, "LED亮度调整", ledBrightnessOptions);

		SettingOptions[] currentCalibrationModeOptions = { 
				new SettingOptions(0,0, "线性校准"),
				new SettingOptions(1,1, "查表校准") };
		SettingValDto currentCalibrationMode = new SettingValDto("currentCalibrationMode",SettingType.comboType, 24, "电流校准方式",
				currentCalibrationModeOptions);

		SettingValDto version = new SettingValDto("version",SettingType.numberType, 25, "软件版本", "");

		settingList.add(password);
		settingList.add(rs485address);
		settingList.add(choosePump);
		settingList.add(choosePool);
		settingList.add(reserved2);
		settingList.add(restartTime);
		settingList.add(protectMode);
		settingList.add(motorPower);
		settingList.add(overCurrentProtectVal);
		settingList.add(overCurrentProtectDelay);
		settingList.add(underCurrentProtectVal);
		settingList.add(underCurrentProtectDelay);
		settingList.add(voltage);
		settingList.add(overVoltageProtectVal);
		settingList.add(overVoltageProtectDelay);
		settingList.add(underVoltageProtectVal);
		settingList.add(underVoltageProtectDelay);
		settingList.add(inspectPeriod);
		settingList.add(inspectTime);
		settingList.add(switchDelay);
		settingList.add(voltageCalibration);
		settingList.add(current1Calibration);
		settingList.add(current2Calibration);
		settingList.add(ledBrightness);
		settingList.add(currentCalibrationMode);
		settingList.add(version);
	}

	
	
	public static List<SettingValDto> getSettingVals(){
		if(settingList.size() == 0){
			initSettingValues();
		}
		return settingList;
		
	}
}
