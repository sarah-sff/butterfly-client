package exception;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:40:37 
 * @version V1.0.0
 */
public class IllegalDeviceAddrException extends Exception {

    
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 1L;

	public IllegalDeviceAddrException(String message) {
        super(message);
    }
}