package main.com.handu.scada.log;

import main.com.handu.scada.utils.DateUtils;

import java.io.File;
import java.util.Date;

/**
 * Created by 柳梦 on 2018/03/26.
 */
public class Log {

    private String name;
    //日志内容
    private String content;
    //日志类型
    private LogType logType;
    //写入路径
    private String path;
    //时间
    private Date date;

    private String suffix;


    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Log(String content, LogType logType) {
        this.content = content + "--" + DateUtils.getNowSqlDateTime() + "\n";
        this.logType = logType;
        this.date = DateUtils.getNowSqlDateTime();
        this.name = logType.name().toLowerCase();
        this.path = "log" + File.separator + logType.name().toLowerCase() + File.separator;
        this.suffix = ".txt";
    }
}
