package main.com.handu.scada.protocol.protocol.DLT645.LP1997;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/05/08.
 * 杭州乾隆漏保型号
 */
public class HZQL_Version {
    private int id;
    private String name;
    //额定负载电流
    private int[] ratedLoadCurrent;
    //剩余电流
    private int[] residualCurrent;
    //分段时间延迟
    private int[] delayTime;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRatedLoadCurrent(int b) {
        try {
            return ratedLoadCurrent[b];
        } catch (Exception e) {
            return -1;
        }
    }

    public int getResidualCurrent(int b) {
        try {
            return residualCurrent[b];
        } catch (Exception e) {
            return -1;
        }
    }

    public int getDelayTime(int b) {
        try {
            return delayTime[b];
        } catch (Exception e) {
            return -1;
        }
    }

    public HZQL_Version(int id, String name, int[] ratedLoadCurrent, int[] residualCurrent, int[] delayTime) {
        this.id = id;
        this.name = name;
        if (ratedLoadCurrent != null) {
            if (ratedLoadCurrent.length == 1) {
                this.ratedLoadCurrent = ratedLoadCurrent;
            } else {
                List<Integer> list = new ArrayList<>();
                int start = ratedLoadCurrent[0];
                int end = ratedLoadCurrent[1];
                int i = ratedLoadCurrent[2];
                list.add(start);
                while (start < end) {
                    start += i;
                    list.add(start);
                }
                this.ratedLoadCurrent = list.stream().mapToInt(Integer::valueOf).toArray();
            }
        }
        this.residualCurrent = residualCurrent;
        this.delayTime = delayTime;
    }
}
