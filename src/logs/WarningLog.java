package logs;

import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;  

/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:41:53 
 * @version V1.0.0
 */
public class WarningLog {  
    private static Logger logger = LogManager.getLogger("WarningLog");  
    public static void logWarning(String log) { 
    	logger.info(log);
    }  
} 