package com.example.home.electronic_port.service;/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

import com.alibaba.fastjson.JSON;
import org.java_websocket.enums.ReadyState;
import sun.misc.BASE64Decoder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/1 12:39 上午
 * @since 1.0
 **/
public class WbSocketUtil {

    public static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

    public static WbSocket wbSocket;

    static {
        try {
            wbSocket = new WbSocket(new URI("wss://wss.singlewindow.cn:61231"), linkedBlockingQueue);
            connect(wbSocket);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static String sign(String wd, String data) throws InterruptedException{
        Param param = new Param();
        param.set_method("security_signData");
        param.set_id(1L);
        Map<String, Object> stringList = new HashMap<>();
        stringList.put("passwd", wd);
        stringList.put("inputData", data);
        param.setArgs(stringList);
        connect(wbSocket);
        int i=1;
        while (!wbSocket.isOpen()){
            if (i>15){
                break;
            }
            Thread.sleep(1000);
            i++;
        }
        wbSocket.send(JSON.toJSONString(param));
        return linkedBlockingQueue.take();
    }


    public static void connect(WbSocket client){
        if (client == null) {
            return;
        }
        if (!client.isOpen()) {
            if (client.getReadyState().equals(ReadyState.NOT_YET_CONNECTED)) {
                try {
                    client.connect();
                } catch (IllegalStateException e) {
                }
            } else if (client.getReadyState().equals(ReadyState.CLOSED) || client.getReadyState().equals(ReadyState.CLOSING)) {
                client.reconnect();
            }
        }
    }

    public LinkedBlockingQueue<String> getLinkedBlockingQueue() {
        return linkedBlockingQueue;
    }
}
