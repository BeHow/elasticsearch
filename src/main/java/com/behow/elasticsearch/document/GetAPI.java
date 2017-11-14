package com.behow.elasticsearch.document;

import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;

import com.behow.elasticsearch.utils.ClientUtils;

public class GetAPI {

	@Test
	public void test() throws Exception{
		GetResponse response = ClientUtils.getClient()
		.prepareGet("behow","behow","AV-7MDexZ2lx9dueX67r")
		.setOperationThreaded(false)//operrationThread 设置为true表示在不同的线程中执行此操作
		.get();
		
		System.out.println(response.getSourceAsString());
	}
}
