package com.rock.jtools.net;

import org.junit.Test;
import sun.nio.ch.Net;


public class NetUtilTest {


    @Test
    public void getLocalIPv4First() {
        String ip = NetUtil.getLocalIPv4First();
        System.out.println(ip);
    }
}