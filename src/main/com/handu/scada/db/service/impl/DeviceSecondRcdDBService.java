package main.com.handu.scada.db.service.impl;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.bean.DeviceRcd;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.service.BaseDBService;
import main.com.handu.scada.db.service.IDBService;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public class DeviceSecondRcdDBService extends BaseDBService implements IDBService {

    @Override
    public int submit(CommonMapper commonMapper, List<DeviceData> dataList) {
        return super.submit(commonMapper, dataList);
    }

    @Override
    protected String startSql() {
        return " insert into device_rcd (Oid, DaId, Name, Address, BaudRate, Checkdigit," +
                " TerminalId, Level, DtuId ,Ua,Ub,Uc,Ia,Ib,Ic,Io) values ";
    }

    @Override
    protected String endSql() {
        return " on duplicate key update Oid =values(Oid ) , DaId =values(DaId ) , Name =values(Name ) ," +
                " Address =values(Address ) ,BaudRate =values(BaudRate), Checkdigit =values (Checkdigit)," +
                "TerminalId =values(TerminalId ) , Level =values(Level ) ," +
                " DtuId =values(DtuId ),Ua=values(Ua),Ub=values(Ub),Uc=values(Uc)," +
                "Ia=values(Ia),Ib=values(Ib),Ic=values(Ic),Io=values(Io) ";
    }

    @Override
    protected void jointSql() {
        if (dataList != null) {
            for (DeviceData deviceData : dataList) {
                DeviceRcd rcd = (DeviceRcd) deviceData.getData();
                if (rcd != null) {
                    sb.append("('")
                            .append(rcd.getOid())
                            .append("','")
                            .append(rcd.getDaid())
                            .append("','")
                            .append(rcd.getName())
                            .append("','")
                            .append(rcd.getAddress())
                            .append("','")
                            .append(rcd.getBaudrate())
                            .append("','")
                            .append(rcd.getCheckdigit())
                            .append("','")
                            .append(rcd.getTerminalid())
                            .append("',")
                            .append(Integer.parseInt(rcd.getLevel()))
                            .append(",'")
                            .append(rcd.getDtuid())
                            .append("',")
                            .append(rcd.getUa())
                            .append(",")
                            .append(rcd.getUb())
                            .append(",")
                            .append(rcd.getUc())
                            .append(",")
                            .append(rcd.getIa())
                            .append(",")
                            .append(rcd.getIb())
                            .append(",")
                            .append(rcd.getIc())
                            .append(",")
                            .append(rcd.getIo())
                            .append(")")
                            .append(",");
                    num++;
                }
            }
        }
    }
}
