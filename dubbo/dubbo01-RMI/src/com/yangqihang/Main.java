package com.yangqihang;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {
    public static void main(String[] args) {
        //实例化要暴露给远程的方法/接口
        ISayService impl = new SayServiceImpl();

        try {
            //开启本地服务
            ISayService sayService = (ISayService) UnicastRemoteObject.exportObject(impl, 666);
            //服务注册中心
            Registry registry = LocateRegistry.createRegistry(999);
            //注册服务
            registry.rebind("sayService", sayService);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
