package main.com.handu.scada.protocol.base;

import main.com.handu.scada.protocol.enums.ProtocolTypes;

/**
 * Created by 柳梦 on 2017/12/22.
 */
public class ProtocolFolder {

    public static String getProtocolFolder(ProtocolTypes proType) {
        String str = "BoWei";
        switch (proType) {
            case None:
            case BoWei:
                return "BoWei";

            case GD2002Blance101:
                return "GD2002Blance101";

            case Nonequilibrium101:
                return "Nonequilibrium101";

            case IEC104:
                return "IEC104";

            case GsmEnglish:
                return "英文短信";

            case GDW3761:
                return "GDW3761";

            case NRBalance101:
                return "NRBalance101";

            case BDBalance101:
                return "BDBalance101";

            case BD104:
                return "BD104";

            case KLBalance101:
                return "KL101";
        }
        return str;
    }
}
