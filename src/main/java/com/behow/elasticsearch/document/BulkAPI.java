package com.behow.elasticsearch.document;

import java.util.Date;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import com.behow.elasticsearch.utils.ClientUtils;

public class BulkAPI {

	@Test
	public void test01() throws Exception{
		
		TransportClient client = ClientUtils.getClient();
		BulkRequestBuilder bulkRequestBuilder =client.prepareBulk();
		bulkRequestBuilder.add(client.prepareIndex("behow", "behow", "2")
				.setSource(XContentFactory.jsonBuilder()
						.startObject()
						.field("user","hyf")
						.field("postDate",new Date())
						.field("message","trying out elasticsearch")
			             .endObject()
				         ));
		bulkRequestBuilder.add(client.prepareIndex("behow", "behow", "3")
				.setSource(XContentFactory.jsonBuilder()
						.startObject()
						.field("user","Kimchy")
						.field("postDate",new Date())
						.field("message","hello elasticsearch")
				        .endObject()));
		BulkResponse bulkResponse = bulkRequestBuilder.get();
		if(bulkResponse.hasFailures()){
			System.out.println("error.");
		}else{
			System.out.println("success.");
		}
	}
}
