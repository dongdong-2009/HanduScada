package main.com.handu.scada.protocol.protocol.GDW3761;

import main.com.handu.scada.protocol.protocol.Data.DataAttr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/04/26.
 */
public class BaseAFNData {
    int dataLength;
    int phaseNumber = 3;
    public List<DataAttr> dataAttrs = new ArrayList<>();
}
