package com.example.home.electronic_port.service;/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.BooleanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/9/30 9:58 下午
 * @since 1.0
 **/
public class WbSocket extends WebSocketClient {
    private LinkedBlockingQueue<String> linkedBlockingQueue;

    public WbSocket(URI serverUri, LinkedBlockingQueue<String> linkedBlockingQueue) {
        super(serverUri,new Draft_6455());
        this.linkedBlockingQueue = linkedBlockingQueue;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("开启");
    }

    /**
     * 消息接收
     * @param s
     */
    @Override
    public void onMessage(String s) {
        System.out.println(s);
        try {
            ParamResponse param = JSON.parseObject(s, ParamResponse.class);
            if (param != null && !MapUtil.isEmpty(param.get_args())) {
                if (!param.get_method().equals("security_signData")) {
                    return;
                }
                Map<String, Object> args = param.get_args();
                if (args.containsKey("Result") && BooleanUtil.isTrue((Boolean) args.get("Result"))) {
                    if (args.get("Data") instanceof JSONArray) {
                        JSONArray objectList = (JSONArray) args.get("Data");
                        //缓存，另外调取
                        System.out.println(objectList.get(0).toString());
                        linkedBlockingQueue.offer(objectList.get(0).toString());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("解析出错");
            linkedBlockingQueue.offer("");
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("关闭");
        linkedBlockingQueue.offer("");
    }

    @Override
    public void onError(Exception e) {
        linkedBlockingQueue.offer("");
    }

}
