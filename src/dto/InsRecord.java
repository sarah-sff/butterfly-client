package dto;

/**
 * 
 * @Description 
 * 版权所有：昌运电器公司
 * 未经本公司许可，不得以任何方式复制或者使用本程序任何部分
 * @author 粟
 * @date 2016年6月20日 上午11:39:00 
 * @version V1.0.0
 */
public class InsRecord {

    //modbus
    byte[] instruction = new byte[2];

    //发送时间
    Long sendTime = System.currentTimeMillis();
    
    int failTimes = 0;


    public InsRecord(byte[] message) {
        if (message == null || message.length < 2) {
            return;
        }

        for (int i = 0; i < 2; i++) {
            this.instruction[i] = message[i];
        }
    }
    
    /**
     * 返回地址
     * @return
     */
    public byte getAddr(){
    	return instruction[0];
    }

    /**
     * 判断相等
     *
     * @param insRecord
     * @return
     */
    public boolean equals(InsRecord insRecord) {
        return insRecord.instruction[0] == this.instruction[0]
                && insRecord.instruction[1] == this.instruction[1];
    }

    /**
     * 是否超时
     *
     * @return
     */
    public long hasSendOutTimeLong() {

        Long now = System.currentTimeMillis();
        return now - this.sendTime;
    }
}
