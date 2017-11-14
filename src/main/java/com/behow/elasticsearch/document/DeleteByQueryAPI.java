package com.behow.elasticsearch.document;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkIndexByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.junit.Test;

import com.behow.elasticsearch.utils.ClientUtils;

public class DeleteByQueryAPI {

	/**
	 * 通过查询条件删除
	 * @throws Exception 
	 */
	@Test
	public void test01() throws Exception{
		TransportClient client = ClientUtils.getClient();
		BulkIndexByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
		.filter(QueryBuilders.matchQuery("user", "fendo")).source("fendo").get();
		System.out.println("删除文档的数量:"+response.getDeleted());
	}
	
	/**
	 * 如果需要执行的时间比较长，可以使用异步的方式处理，结果在回调结果里面获取
	 * @throws Exception 
	 */
	@Test
	public void test02() throws Exception{
		TransportClient client = ClientUtils.getClient();
		DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
		.filter(QueryBuilders.matchQuery("user","kimchy"))
		.source("fendo")
		.execute(new ActionListener<BulkIndexByScrollResponse>(){

			public void onFailure(Exception arg0) {
				System.out.println("delete by query error.");
			}

			public void onResponse(BulkIndexByScrollResponse response) {
				System.out.println("删除的文档数量:"+response.getDeleted());
			}
			
		});
	}
}
