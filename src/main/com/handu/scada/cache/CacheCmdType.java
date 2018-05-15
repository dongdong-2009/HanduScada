package main.com.handu.scada.cache;

import java.util.Objects;

/**
 * Created by 柳梦 on 2018/04/23.
 */
public enum CacheCmdType {

    DEFAULT("default"), CREATE("create"), DELETE("delete"), UPDATE("update"), CREATE_OR_UPDATE("create_or_update");

    private String type;

    CacheCmdType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static CacheCmdType get(String type) {
        for (CacheCmdType cmdType : CacheCmdType.values()) {
            if (Objects.equals(cmdType.getType(), type.toLowerCase())) {
                return cmdType;
            }
        }
        return CREATE_OR_UPDATE;
    }
}
