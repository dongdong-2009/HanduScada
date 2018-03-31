package main.com.handu.scada.switch101.protocol.enums;

/**
 * Created by 柳梦 on 2018/03/19.
 */
public enum COT {
    COT00((byte) 0x00),//<0> ∶＝未用
    COT01((byte) 0x01),//<1> ∶＝周期、循环
    COT02((byte) 0x02),//<2> ∶＝背景扫描
    COT03((byte) 0x03),//<3> ∶＝ 突发(自发)
    COT04((byte) 0x04),//<4> ∶＝初始化
    COT05((byte) 0x05),//<5> ∶＝请求或者被请求
    COT06((byte) 0x06),//<6> ∶＝激活
    COT07((byte) 0x07),//<7> ∶＝激活确认
    COT08((byte) 0x08),//<8> ∶＝停止激活
    COT09((byte) 0x09),//<9> ∶＝停止激活确认
    COT0a((byte) 0x0a),//<10> ∶＝激活终止
    COT0d((byte) 0x0d),//<13> ∶＝文件传输
    COT14((byte) 0x14),//<20> ∶＝响应站召唤
    COT25((byte) 0x25),//<37> ：＝响应电能量总召唤
    COT2c((byte) 0x2c),//<44>：＝未知的类型标识
    COT2d((byte) 0x2d),//<45>：＝未知的传送原因
    COT2e((byte) 0x2e),//<46>：＝未知的应用服务数据单元公共地址
    COT2f((byte) 0x2f),//<47>：＝未知的信息对象地址
    COT30((byte) 0x30),//<48> ∶＝遥控执行软压板状态错误
    COT31((byte) 0x31),//<49> ∶＝遥控执行时间戳错误
    COT32((byte) 0x32);//<50> ∶＝遥控执行数字签名认证错误

    private byte cot;

    public byte getCot() {
        return cot;
    }

    COT(byte cot) {
        this.cot = cot;
    }

    public static COT getCOT(int cot) {
        for (COT c : COT.values()) {
            if (c.cot == cot) return c;
        }
        return COT00;
    }
}
