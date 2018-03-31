/**
 * Scdl_sms_send_wsSoap_PortType.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package main.com.handu.scada.business.message.client.send;

public interface Scdl_sms_send_wsSoap_PortType extends java.rmi.Remote {
    public java.lang.String scdl_sms_send_ws1(java.lang.String userId, java.lang.String pwd, int count, java.lang.String XMLInfo) throws java.rmi.RemoteException;

    public java.lang.String scdl_sms_send_wsN(java.lang.String userId, java.lang.String pwd, int count, java.lang.String XMLInfo) throws java.rmi.RemoteException;

    public java.lang.String scdl_sms_send_ws_Single(java.lang.String userId, java.lang.String pwd, java.lang.String phoneNum, java.lang.String content) throws java.rmi.RemoteException;
}
