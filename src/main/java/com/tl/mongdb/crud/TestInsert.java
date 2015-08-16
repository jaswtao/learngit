package com.tl.mongdb.crud;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.tl.mongdb.util.ConnectionUtil;

public class TestInsert {

	public static void main(String[] args) throws Exception {
		FindIterable<Document> it = ConnectionUtil.testDB().getCollection("restaurants").find(Filters.and(Filters.eq("address.street", "2 Avenue"), Filters.eq("restaurant_id", "41704620")));
		ConnectionUtil.printDocuments(it);
	}

	static void insert() throws Exception {
		final DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		ConnectionUtil.testDB().getCollection("restaurants")
				.insertOne(new Document("address",
						new Document().append("street", "2 Avenue").append("zipcode", "10075")
								.append("building", "1480").append("coord", Arrays.asList(-73.9557413, 40.7720266)))
										.append("borough",
												"Manhattan")
										.append("cuisine", "Italian")
										.append("grades",
												Arrays.asList(new Document()
														.append("date", format.parse("2014-10-01T00:00:00Z"))
														.append("grade",
																"A")
														.append("score", 11),
												new Document().append("date", format.parse("2014-01-16T00:00:00Z"))
														.append("grade", "B").append("score", 17)))
								.append("name", "Vella").append("restaurant_id", "41704620"));

	}
}
