package logs;

import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;  

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:41:53 
 * @version V1.0.0
 */
public class WarningLog {  
    private static Logger logger = LogManager.getLogger("WarningLog");  
    public static void logWarning(String log) { 
    	logger.info(log);
    }  
} 