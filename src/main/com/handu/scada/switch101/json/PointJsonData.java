package main.com.handu.scada.switch101.json;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class PointJsonData {

    private Key Key;
    private Value Value;

    public void setKey(Key Key) {
        this.Key = Key;
    }

    public Key getKey() {
        return Key;
    }

    public void setValue(Value Value) {
        this.Value = Value;
    }

    public Value getValue() {
        return Value;
    }
}
