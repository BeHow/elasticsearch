package com.behow.elasticsearch.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

public class EsCliset {

	@Test
	public void test1(){
		
		//不設置集群名稱
		TransportClient client =null;
		try {
			client= new PreBuiltTransportClient(Settings.EMPTY)
					                 .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.110"),9200))
					                 .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.120"),9200));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(client);
		client.close();
	}
	
	@Test
	public void test2(){
		//设置集群名称
		Settings settings = Settings.builder().put("cluster.name","behow").build();//设置es实例的名称
		TransportClient client = new PreBuiltTransportClient(settings);
		System.out.println(client);
		client.close();
	}
	
	@Test
	public void test3(){
		//增加自动嗅探配置
		Settings settings = Settings.builder().put("client.transport.sniff",true).build();
		TransportClient client =  new PreBuiltTransportClient(settings);
		System.out.println(client);
		client.close();
	}
	
	@Test
	public void test() throws Exception{
		Settings settings = Settings.builder()
				.put("cluster.name","behow_elasticsearch")//设置es实例的名称
				.put("client.transport.sniff",true)//自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地客户端列表
				.build();
	  TransportClient client = new PreBuiltTransportClient(settings);
	  client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.110"),9200));
	}
}
