/**
 * Scdl_sms_send_wsLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package main.com.handu.scada.business.message.client.send;

public class Scdl_sms_send_wsLocator extends org.apache.axis.client.Service implements Scdl_sms_send_ws {

    public Scdl_sms_send_wsLocator() {
    }


    public Scdl_sms_send_wsLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Scdl_sms_send_wsLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for scdl_sms_send_wsSoap
    private java.lang.String scdl_sms_send_wsSoap_address = "http://10.176.3.194/scdl_sms_send_ws.asmx";

    public java.lang.String getscdl_sms_send_wsSoapAddress() {
        return scdl_sms_send_wsSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String scdl_sms_send_wsSoapWSDDServiceName = "scdl_sms_send_wsSoap";

    public java.lang.String getscdl_sms_send_wsSoapWSDDServiceName() {
        return scdl_sms_send_wsSoapWSDDServiceName;
    }

    public void setscdl_sms_send_wsSoapWSDDServiceName(java.lang.String name) {
        scdl_sms_send_wsSoapWSDDServiceName = name;
    }

    public Scdl_sms_send_wsSoap_PortType getscdl_sms_send_wsSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(scdl_sms_send_wsSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getscdl_sms_send_wsSoap(endpoint);
    }

    public Scdl_sms_send_wsSoap_PortType getscdl_sms_send_wsSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            Scdl_sms_send_wsSoap_BindingStub _stub = new Scdl_sms_send_wsSoap_BindingStub(portAddress, this);
            _stub.setPortName(getscdl_sms_send_wsSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setscdl_sms_send_wsSoapEndpointAddress(java.lang.String address) {
        scdl_sms_send_wsSoap_address = address;
    }


    // Use to get a proxy class for scdl_sms_send_wsSoap12
    private java.lang.String scdl_sms_send_wsSoap12_address = "http://10.176.3.194/scdl_sms_send_ws.asmx";

    public java.lang.String getscdl_sms_send_wsSoap12Address() {
        return scdl_sms_send_wsSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String scdl_sms_send_wsSoap12WSDDServiceName = "scdl_sms_send_wsSoap12";

    public java.lang.String getscdl_sms_send_wsSoap12WSDDServiceName() {
        return scdl_sms_send_wsSoap12WSDDServiceName;
    }

    public void setscdl_sms_send_wsSoap12WSDDServiceName(java.lang.String name) {
        scdl_sms_send_wsSoap12WSDDServiceName = name;
    }

    public Scdl_sms_send_wsSoap_PortType getscdl_sms_send_wsSoap12() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(scdl_sms_send_wsSoap12_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getscdl_sms_send_wsSoap12(endpoint);
    }

    public Scdl_sms_send_wsSoap_PortType getscdl_sms_send_wsSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            Scdl_sms_send_wsSoap12Stub _stub = new Scdl_sms_send_wsSoap12Stub(portAddress, this);
            _stub.setPortName(getscdl_sms_send_wsSoap12WSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setscdl_sms_send_wsSoap12EndpointAddress(java.lang.String address) {
        scdl_sms_send_wsSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (Scdl_sms_send_wsSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                Scdl_sms_send_wsSoap_BindingStub _stub = new Scdl_sms_send_wsSoap_BindingStub(new java.net.URL(scdl_sms_send_wsSoap_address), this);
                _stub.setPortName(getscdl_sms_send_wsSoapWSDDServiceName());
                return _stub;
            }
            if (Scdl_sms_send_wsSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                Scdl_sms_send_wsSoap12Stub _stub = new Scdl_sms_send_wsSoap12Stub(new java.net.URL(scdl_sms_send_wsSoap12_address), this);
                _stub.setPortName(getscdl_sms_send_wsSoap12WSDDServiceName());
                return _stub;
            }
        } catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("scdl_sms_send_wsSoap".equals(inputPortName)) {
            return getscdl_sms_send_wsSoap();
        } else if ("scdl_sms_send_wsSoap12".equals(inputPortName)) {
            return getscdl_sms_send_wsSoap12();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("scdl_sms_interface_ws", "scdl_sms_send_ws");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("scdl_sms_interface_ws", "scdl_sms_send_wsSoap"));
            ports.add(new javax.xml.namespace.QName("scdl_sms_interface_ws", "scdl_sms_send_wsSoap12"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("scdl_sms_send_wsSoap".equals(portName)) {
            setscdl_sms_send_wsSoapEndpointAddress(address);
        } else if ("scdl_sms_send_wsSoap12".equals(portName)) {
            setscdl_sms_send_wsSoap12EndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
