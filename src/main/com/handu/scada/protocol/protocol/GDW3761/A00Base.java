package main.com.handu.scada.protocol.protocol.GDW3761;

/**
 * Created by 柳梦 on 2018/04/26.
 */
public abstract class A00Base {

    //当收到的数据不是正确的BCD码，给value超过所有GDW3761所定义的最大数据，现有版本最大是A13,千亿
    //定义一万亿，显示模块收到这个数值，将显示空串。
    protected double MaxDoubleVal = 1000000000000d;

    protected abstract void encode();

    protected abstract void decode();

    protected Object value;

    protected String name;

    protected String unit = "";

    protected String itemName;

    protected byte[] code;

    public void setDataOfAFN(byte[] dataOfAFN) {
        if (dataOfAFN != null) {
            code = dataOfAFN;
            decode();
        }
    }

    public void setCode(double value) {
        this.value = value;
        encode();
    }

    public byte[] getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "A00Base{" +
                "value=" + value +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
