package com.yangqihang;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            InetAddress inetAddr = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddr);
            System.out.println(inetAddr.getHostName());
            System.out.println(inetAddr.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
