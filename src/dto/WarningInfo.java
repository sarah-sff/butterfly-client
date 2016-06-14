package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import logs.WarningLog;
import utils.DateUtil;

public class WarningInfo {
	
	public int addr;

	//������Ϣ��Ӧ���±�
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
		return "�豸"+addr+"->"+infoStr;
	}
	
	//��¼����
	public void record(){
		WarningLog.logWarning("[������¼] "+DateUtil.formatDate(time)+" "+this.toString());
	}
	
	//��¼��������
	public void over(){
		WarningLog.logWarning("[����ֹͣ] "+DateUtil.formatDate(new Date())+" "+this.toString()+" ��������");
	}
}
