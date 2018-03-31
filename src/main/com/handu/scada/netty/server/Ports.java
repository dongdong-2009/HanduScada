package main.com.handu.scada.netty.server;

/**
 * Created by 柳梦 on 2018/03/13.
 */
public class Ports {
    String[] ports;
    PortType portType;

    public Ports(String[] ports, PortType portType) {
        this.ports = ports;
        this.portType = portType;
    }
}