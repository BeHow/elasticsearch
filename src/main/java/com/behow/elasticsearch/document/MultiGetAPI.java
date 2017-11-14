package com.behow.elasticsearch.document;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;

import com.behow.elasticsearch.utils.ClientUtils;

public class MultiGetAPI {

	/**
	 * multi get API
	 * @throws Exception 
	 */
	@Test
	public void test01() throws Exception{
		
		TransportClient client = ClientUtils.getClient();
		
		MultiGetResponse multiGetResponse = client.prepareMultiGet()
		.add("behow","behow","1","AV-7MDexZ2lx9dueX67r")
		.add("fendo","fendo","AV-7v_DRZ2lx9dueX67u")
		.get();
		
		for (MultiGetItemResponse multiGetItemResponse : multiGetResponse) {
			GetResponse response = multiGetItemResponse.getResponse();
			if(response.isExists()){
				String json = response.getSourceAsString();
				System.out.println("json:"+json);
			}
		}
		
	}
}
