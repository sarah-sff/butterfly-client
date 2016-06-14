package logs;

import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;  
  
public class WarningLog {  
    private static Logger logger = LogManager.getLogger("WarningLog");  
    public static void logWarning(String log) { 
    	logger.info(log);
    }  
} 