package com.tl.mongdb.crud;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import com.tl.mongdb.util.ConnectionUtil;

public class TestUpdate {

	public static void main(String[] args) {
		update();
		FindIterable<Document> it = ConnectionUtil.testDB().getCollection("restaurants")
				.find(Filters.and(Filters.eq("address.street", "2 Avenue"), Filters.eq("restaurant_id", "41704620")));
		ConnectionUtil.printDocuments(it);
	}

	static void update() {
		UpdateResult result = ConnectionUtil.testDB().getCollection("restaurants").updateOne(new Document("_id", new ObjectId("55ccbf31d7b9ae332757b3f2")),
				new Document("$set", new Document("restaurant_id", "41704621")));
		System.out.println(result.getModifiedCount());
	}
}
