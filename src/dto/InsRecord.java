package dto;

/**
 * 璁板綍鎸囦护鍙戦�鏃堕棿
 * Created by Administrator on 2016/4/29.
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
     * 鍒ゆ柇鐩哥瓑锛堟寚浠ょ浉绛夊嵆鍙級
     *
     * @param insRecord
     * @return
     */
    public boolean equals(InsRecord insRecord) {
        return insRecord.instruction[0] == this.instruction[0]
                && insRecord.instruction[1] == this.instruction[1];
    }

    /**
     * 宸茬粡鍙戦�澶氫箙锛堟绉掞級
     *
     * @return
     */
    public long hasSendOutTimeLong() {

        Long now = System.currentTimeMillis();
        return now - this.sendTime;
    }
}
