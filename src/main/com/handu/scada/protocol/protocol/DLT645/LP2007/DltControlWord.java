package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

public class DltControlWord {

    public boolean flagAllAlarm;//数据总告警开关

    public boolean flagLightAlarm;//报警灯光

    public boolean flagAudioAlarm;//报警声音

    public boolean flagTimelyTrial;//定时试跳

    public boolean flagLevelReturn;//档位返回

    public boolean flagReclosing;//重合闸

    public boolean flagUnderVoltageAlarm;//欠压告警

    public boolean flagUnderVoltageControl;//欠压控制

    public boolean flagOverVoltageAlarm;//过压告警

    public boolean flagOverVoltageControl;//过压控制

    public boolean flagMissPhaseAlarm;//缺相告警

    public boolean flagMissPhaseControl;//缺相控制

    public boolean flagOverCurrentAlarm;//过流告警

    public boolean flagOverCurrentControl;//过流控制

    public boolean flagTrialSource;//试跳源

    public boolean flagMissEarthLineAlarm;//缺零告警

    public boolean flagMissEarthLineControl;//缺零控制

    public int ResidualThresholdLevel;//额定剩余电流动作值档位

    public int DelayTimeLevel;//额定极限不驱动时间档位

    public int ResidualAlarmTimeLevel;//剩余电流报警时间

    public DltControlWord(byte[] word) {

        byte controlWord;
        controlWord = word[0];

        flagAllAlarm = (controlWord & 0x40) != 0;
        flagLightAlarm = (controlWord & 0x20) != 0;
        flagAudioAlarm = (controlWord & 0x10) != 0;
        flagTimelyTrial = (controlWord & 0x08) != 0;
        flagLevelReturn = (controlWord & 0x04) != 0;
        flagReclosing = (controlWord & 0x02) != 0;

        controlWord = word[1];
        flagUnderVoltageAlarm = (controlWord & 0x80) != 0;
        flagUnderVoltageControl = (controlWord & 0x40) != 0;
        flagOverVoltageAlarm = (controlWord & 0x20) != 0;
        flagOverVoltageControl = (controlWord & 0x10) != 0;
        flagMissPhaseAlarm = (controlWord & 0x08) != 0;
        flagMissPhaseControl = (controlWord & 0x04) != 0;
        flagOverCurrentAlarm = (controlWord & 0x02) != 0;
        flagOverCurrentControl = (controlWord & 0x01) != 0;

        controlWord = word[2];
        flagOverCurrentAlarm = (controlWord & 0x80) != 0;
        flagMissEarthLineAlarm = (controlWord & 0x02) != 0;
        flagMissEarthLineControl = (controlWord & 0x01) != 0;

        controlWord = word[3];
        ResidualThresholdLevel = (controlWord & 0xf0) >> 4;
        DelayTimeLevel = (controlWord & 0x0c) >> 2;
        ResidualAlarmTimeLevel = controlWord & 0x03;

    }
}
