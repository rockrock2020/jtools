package com.rock.jtools.net;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetUtil {
    public static List<String> getLocalIPv4List() {
        List<String> ipList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (interfaceEnumeration.hasMoreElements()) {
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();
                // filter out  localhost and inactive interfaces
                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
                while (inetAddressEnumeration.hasMoreElements()) {
                    InetAddress inetAddress = inetAddressEnumeration.nextElement();
                    // filter ipv6
                    if (inetAddress instanceof Inet6Address) {
                        continue;
                    }
                    String ipAddress = inetAddress.getHostAddress();
                    if (ipAddress != null && ipAddress.length() > 0) {
                        ipList.add(ipAddress);
                    }
                }
            }
        } catch (SocketException e) {
            throw new IllegalStateException(e);
        }
        return ipList;
    }

    public static String getLocalIPv4First(){
        List<String> ipList = getLocalIPv4List();
        if(ipList==null || ipList.isEmpty()){
            return null;
        }
        return ipList.get(0);
    }
}
