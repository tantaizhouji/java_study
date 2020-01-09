package com.yangqihang;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CustomMain {
    public static void main(String[] args) {
        try {
            //实例化注册中心
            Registry registry = LocateRegistry.getRegistry(999);
            //发现服务
            ISayService sayService = (ISayService) registry.lookup("sayService");
            //调起服务
            String str = sayService.say("马老师");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
