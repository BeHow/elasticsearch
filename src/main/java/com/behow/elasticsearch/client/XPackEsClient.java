package com.behow.elasticsearch.client;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

public class XPackEsClient {

	@Test
	public void test() throws Exception{
		
		Settings settings = Settings.builder()
				.put("cluster.name","behow_elasticsearch")
				//.put("xpack.security.transport.ssl.enabled",false)
				.put("client.transport.sniff",true).build();
		
		TransportClient client = new PreBuiltTransportClient(settings)
				                 .addTransportAddress( new InetSocketTransportAddress(InetAddress.getByName("192.168.1.110"),9200))
				                 .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.120"),9200));
		
		System.out.println(client);
		client.close();
		
	}
}
