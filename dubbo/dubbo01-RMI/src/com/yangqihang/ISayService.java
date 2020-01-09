package com.yangqihang;

import java.rmi.Remote;

public interface ISayService extends Remote {
    String say(String name) throws Exception;
}
