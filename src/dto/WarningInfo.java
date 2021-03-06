package dto;

import java.util.Date;
import logs.WarningLog;
import utils.DateUtil;
/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:40:28 
 * @version V1.0.0
 */
public class WarningInfo {
	
	public int addr;

	//报警信息对应的下标
	public String indexStr;
	
	public String infoStr;
	
	public Date time;

	public WarningInfo(int addr, String indexStr, String infoStr) {
		super();
		this.addr = addr;
		this.indexStr = indexStr;
		this.infoStr = infoStr;
		this.time = new Date();
	}
	
	public  boolean equal(WarningInfo info){
		return this.indexStr.equals(info.indexStr);
	}
	
	
	public String toString(){
		return "设备"+addr+"->"+infoStr;
	}
	
	//记录警报
	public void record(){
		WarningLog.logWarning("[警报记录] "+DateUtil.formatDate(time)+" "+this.toString());
	}
	
	//记录警报结束
	public void over(){
		WarningLog.logWarning("[警报停止] "+DateUtil.formatDate(new Date())+" "+this.toString()+" 警报结束");
	}
}
