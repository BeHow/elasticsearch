package com.behow.elasticsearch.utils;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ClientUtils {

	private static TransportClient transportClient = null;
	public static synchronized TransportClient getClient() throws Exception{
		if(transportClient == null ){
			createClient();
		}
		return transportClient;
	}
	
	private static void createClient() throws Exception {
		
		//设置es集群的名称  增加自动嗅探机制
		Settings settings =  Settings.builder()
				.put("cluster.name","behow_elasticsearch")
				.put("client.transport.sniff",true).build();
		transportClient = new PreBuiltTransportClient(settings)
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress.getByName("192.168.1.110"),9300))
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.120"),9300));
		
	}
}
