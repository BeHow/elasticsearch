package com.behow.elasticsearch.document;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.junit.Test;

import com.behow.elasticsearch.utils.ClientUtils;

public class UpdateAPI {

	/**
	 * 创建updateRequest,通过client发送
	 * @throws Exception 
	 */
	@Test
	public void test01() throws Exception{
		TransportClient client = ClientUtils.getClient();
		UpdateRequest updateRequest =  new UpdateRequest();
		updateRequest.index("behow");
		updateRequest.type("behow");
		updateRequest.id("AV-7MDexZ2lx9dueX67r");
		updateRequest.doc(XContentFactory.jsonBuilder()
				.startObject()
				.field("message","behow this is elasticsearch")
				.endObject());
		
		UpdateResponse response = client.update(updateRequest).get();
		
		System.out.println(response.getResult());
		
	}
	
	/**
	 * 使用prepareUpdate
	 * @throws Exception 
	 */
	@Test
	public void test02() throws Exception{
		
		TransportClient client = ClientUtils.getClient();
		client.prepareUpdate("behow", "behow", "AV-7MDexZ2lx9dueX67r")
		.setScript(new Script(ScriptType.INLINE,null,"ctx._source.message = \"hyf\"",null)).get();
		
		
	}
	
	/**
	 * 使用XContentFactory
	 * @throws Exception 
	 */
	@Test
	public void test03() throws Exception{
		TransportClient client = ClientUtils.getClient();
		client.prepareUpdate("behow", "behow", "AV-7MDexZ2lx9dueX67r")
		.setDoc(XContentFactory.jsonBuilder()
				.startObject()
				.field("message","hyf")
				.endObject())
		.get();
	}
	
	/**
	 * update by script
	 * @throws Exception 
	 */
	@Test
	public void test04() throws Exception{
		TransportClient client = ClientUtils.getClient();
		UpdateRequest request =  new UpdateRequest("behow","behow","AV-7MDexZ2lx9dueX67r")
				.script(new Script("ctx._source.message=\"hyf&zbh\""));
		
		UpdateResponse response = client.update(request).get();
		System.out.println(response.getResult());
	}
	
	@Test
	public void test05() throws Exception{
		
		TransportClient client = ClientUtils.getClient();
		IndexRequest indexRequest = new IndexRequest("behow","behow","1")
				.source(XContentFactory.jsonBuilder()
						.startObject()
						.field("name", "hyf")
						.field("gender","male")
						.endObject()
						);
		UpdateRequest updateRequest = new UpdateRequest("behow","behow","1")
				.doc(XContentFactory.jsonBuilder()
						.startObject()
						.field("name","hyf")
						.endObject())
				.upsert(indexRequest);
		
		UpdateResponse response = client.update(updateRequest).get();
		
		System.out.println(response.getResult());
		
	}
}
