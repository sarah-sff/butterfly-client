package exception;

/**
 * 
 * @Description 
 * ��Ȩ���У����˵�����˾
 * δ������˾��ɣ��������κη�ʽ���ƻ���ʹ�ñ������κβ���
 * @author ��
 * @date 2016��6��20�� ����11:40:37 
 * @version V1.0.0
 */
public class IllegalDeviceAddrException extends Exception {

    
	/** @Fields serialVersionUID: */
	  	
	private static final long serialVersionUID = 1L;

	public IllegalDeviceAddrException(String message) {
        super(message);
    }
}