package main.com.handu.scada.protocol.enums;

/**
 * Created by 柳梦 on 2017/12/29.
 */
public enum TrialResultType {

    //[Description("试跳下发")]
    down(0),

    //[Description("跳闸成功")]
    trip(1),

    //[Description("合闸成功")]
    on(2);


    private int type;

    TrialResultType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
