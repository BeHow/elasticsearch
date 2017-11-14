package com.behow.elasticsearch.document;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;

import com.behow.elasticsearch.utils.ClientUtils;

public class DeleteAPI {

	@Test
	public void test() throws Exception{
		
		GetResponse response = ClientUtils.getClient().prepareGet("behow", "behow", "AV-7J9dLZ2lx9dueX67o")
		.setOperationThreaded(false)
		.get();
		
		System.out.println(response);
		
		DeleteResponse delResponse = ClientUtils.getClient().prepareDelete("behow", "behow", "AV-7J9dLZ2lx9dueX67o").get();
	    
		System.out.println(delResponse.getResult());
		
	}
}
