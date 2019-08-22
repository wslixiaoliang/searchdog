package com.art.web.component.elastic;

import com.art.util.famous.Constans;
import com.art.util.famous.LiangUtil;
import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取es客户端连接
 */
@Component
public class EngineClient {

    private static final Logger LOGGER = Logger.getLogger(EngineClient.class);
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
    private  static TransportClient innitClient()
    {
        String clusterName = Constans.CLUSTER_NAME;
        String clusterAddress = Constans.CLUSTER_ADDRESS;

        //指定es集群
        Settings settings = Settings.builder().put("cluster.name",clusterName).put("client.transport.sniff", true).build();
        client = new PreBuiltTransportClient(settings);
        if(LiangUtil.isNotEmpty(clusterAddress)){

            String[] addStr = LiangUtil.splitStr(clusterAddress,",");

            for(String str:addStr)
            {
                String[] addressAndPort = LiangUtil.splitStr(str,":");
                String address = addressAndPort[0];
                int port = Integer.parseInt(addressAndPort[1]);

                try{
                    //创建访问es服务器的客户端
                    client.addTransportAddresses(new TransportAddress(InetAddress.getByName(address),port));

                    LOGGER.info("客户端："+str+"连接成功");

                }catch(UnknownHostException e){

                    LOGGER.error("客户端连接失败",e);
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
