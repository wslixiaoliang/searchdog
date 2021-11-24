/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.web.component.elastic;

import com.art.elastic.util.SearchConstans;
import com.art.elastic.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取es客户端连接
 * @author wslixiaoliang
 */
@Component
@Slf4j
public class EngineClient {
    private static TransportClient client = null;

    /**
     * 获取客户端连接
     * @return
     */
    public static TransportClient getConnection(){
        return innitClient();
    }

    /**
     * 初始化客户端
     * @return
     */
    private static TransportClient innitClient() {
        String clusterName = SearchConstans.CLUSTER_NAME;
        String clusterAddress = SearchConstans.CLUSTER_ADDRESS;
        //指定es集群
        Settings settings = Settings.builder().put("cluster.name",clusterName).put("client.transport.sniff", true).build();
        client = new PreBuiltTransportClient(settings);
        if(StringUtil.isNotEmpty(clusterAddress)){
            String[] addStr = StringUtil.splitStr(clusterAddress,",");
            for(String str:addStr) {
                String[] addressAndPort = StringUtil.splitStr(str,":");
                String address = addressAndPort[0];
                int port = Integer.parseInt(addressAndPort[1]);
                try{
                    //创建访问es服务器的客户端
                    client.addTransportAddresses(new TransportAddress(InetAddress.getByName(address),port));
                    log.info("客户端：{} "+str+"连接成功");
                }catch(UnknownHostException e){
                    log.error("客户端连接失败:{} "+e.getMessage());
                }
            }
        }
        return client;
    }

    /**
     * 关闭客户端
     * 此方法系统默认自动调用（静态方法，在类初始化是就会被初始化，在获取连接方法后自动执行）
     */
    public static synchronized void close(){
        if(null!=client){
            client.close();
        }
    }

}
