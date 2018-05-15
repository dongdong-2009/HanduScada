package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceControlword;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceRcdControlWordDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_controlword (deviceId,flagAllAlarm,flagLightAlarm,flagAudioAlarm,flagTimelyTrial,flagLevelReturn,flagReclosing," +
                "flagUnderVoltageAlarm,flagUnderVoltageControl,flagOverVoltageAlarm,flagOverVoltageControl,flagMissPhaseAlarm,flagMissPhaseControl," +
                "flagOverCurrentAlarm,flagOverCurrentControl,flagTrialSource,flagMissEarthLineAlarm,flagMissEarthLineControl,residualThresholdLevel," +
                "delayTimeLevel,residualAlarmTimeLevel,recordTime) values ";
    }

    @Override
    protected String endSql() {
        return " on duplicate key update deviceId=values(deviceId),flagAllAlarm=values(flagAllAlarm)," +
                "flagLightAlarm=values(flagLightAlarm),flagAudioAlarm=values(flagAudioAlarm)," +
                "flagTimelyTrial=values(flagTimelyTrial),flagLevelReturn=values(flagLevelReturn)," +
                "flagReclosing=values(flagReclosing),flagUnderVoltageAlarm=values(flagUnderVoltageAlarm)," +
                "flagUnderVoltageControl=values(flagUnderVoltageControl),flagOverVoltageAlarm=values(flagOverVoltageAlarm)," +
                "flagOverVoltageControl=values(flagOverVoltageControl),flagMissPhaseAlarm=values(flagMissPhaseAlarm)," +
                "flagMissPhaseControl=values(flagMissPhaseControl),flagOverCurrentAlarm=values(flagOverCurrentAlarm)," +
                "flagOverCurrentControl=values(flagOverCurrentControl),flagTrialSource=values(flagTrialSource)," +
                "flagMissEarthLineAlarm=values(flagMissEarthLineAlarm),flagMissEarthLineControl=values(flagMissEarthLineControl)," +
                "residualThresholdLevel=values(residualThresholdLevel),delayTimeLevel=values(delayTimeLevel)," +
                "residualAlarmTimeLevel=values(residualAlarmTimeLevel),recordTime=values(recordTime)";
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceControlword controlword = (DeviceControlword) deviceData.getData();
                if (controlword != null) {
                    sb.append("('")
                            .append(controlword.getDeviceid())
                            .append("',")
                            .append(controlword.getFlagallalarm())
                            .append(",")
                            .append(controlword.getFlaglightalarm())
                            .append(",")
                            .append(controlword.getFlagaudioalarm())
                            .append(",")
                            .append(controlword.getFlagtimelytrial())
                            .append(",")
                            .append(controlword.getFlaglevelreturn())
                            .append(",")
                            .append(controlword.getFlagreclosing())
                            .append(",")
                            .append(controlword.getFlagundervoltagealarm())
                            .append(",")
                            .append(controlword.getFlagundervoltagecontrol())
                            .append(",")
                            .append(controlword.getFlagovervoltagealarm())
                            .append(",")
                            .append(controlword.getFlagovervoltagecontrol())
                            .append(",")
                            .append(controlword.getFlagmissphasealarm())
                            .append(",")
                            .append(controlword.getFlagmissphasecontrol())
                            .append(",")
                            .append(controlword.getFlagovercurrentalarm())
                            .append(",")
                            .append(controlword.getFlagovercurrentcontrol())
                            .append(",")
                            .append(controlword.getFlagtrialsource())
                            .append(",")
                            .append(controlword.getFlagmissearthlinealarm())
                            .append(",")
                            .append(controlword.getFlagmissearthlinecontrol())
                            .append(",")
                            .append(controlword.getResidualthresholdlevel())
                            .append(",")
                            .append(controlword.getDelaytimelevel())
                            .append(",")
                            .append(controlword.getResidualalarmtimelevel())
                            .append(",'")
                            .append(controlword.getRecordtime())
                            .append("')")
                            .append(",");
                    num++;
                }
            }
        }
    }
}
