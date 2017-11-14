package com.behow.elasticsearch.index;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import com.behow.elasticsearch.entity.CsdnBlog;
import com.behow.elasticsearch.utils.ClientUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IndexAPI {

	@Test
	public void test01() throws Exception{
		
		//手动方式创建json
		/**
		 * user fendo
		 * postDate 2013-01-30
		 * message Hell word 
		 */
		String json = "{"
		              +"\"user\" : \"fendo\","
				      +"\"postDtae\" : \"2013-01-30\","
		              +"\"message\"  : \"Hell word\""
		              +"}";
		TransportClient client = ClientUtils.getClient();
		IndexResponse indexResponse = client.prepareIndex("fendo", "fendo").setSource(json).get();
		System.out.println(indexResponse.getResult());
	}
	
	@Test
	public void test02() throws Exception{
		//使用map集合
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("user", "kimchy");
		json.put("postDate","2013-01-30");
		json.put("message", "trying out Elasticsearch");
		TransportClient client = ClientUtils.getClient();
		IndexResponse indexResponse = client.prepareIndex("fendo", "fendo").setSource(json).get();
		System.out.println(indexResponse.getResult());
	}
	/**
	 * 使用jackson序列化
	 * @throws Exception 
	 */
	@Test
	public void test03() throws Exception{
		CsdnBlog blog = new CsdnBlog();
		blog.setAuthor("behow");
		blog.setContent("behow");
		blog.setTag("C");
		blog.setTitle("编程");
		blog.setDate(new Date().toString());
		
		ObjectMapper objectMapper =  new ObjectMapper();
		byte[] json = objectMapper.writeValueAsBytes(blog);
		IndexResponse response = ClientUtils.getClient().prepareIndex("behow", "behow").setSource(json).get();
		System.out.println(response.getResult());
		
	}
	
	/**
	 * 使用XContentBuilder帮助类方式
	 * @throws Exception 
	 */
	@Test
	public void test04() throws Exception{
		XContentBuilder builder = XContentFactory.jsonBuilder()
		.startObject()
		.field("user","behow")
		.field("postDate",new Date())
		.field("message","this is Elasticsearch")
		.endObject();
		ClientUtils.getClient().prepareIndex("behow","behow").setSource(builder).get();
		System.out.println("创建成功！");
		
	}
}
