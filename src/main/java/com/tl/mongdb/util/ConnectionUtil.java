package com.tl.mongdb.util;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class ConnectionUtil {

	private static MongoClient mongoClient;

	public static MongoClient getMongoClient() {
		return getMongoClient(null, 0);
	}

	public static MongoClient getMongoClient(String host, int port) {
		if (mongoClient == null) {
			if (host == null) {
				mongoClient = new MongoClient();
			} else {
				mongoClient = new MongoClient(host, port);
			}
		}
		return mongoClient;
	}

	public static MongoDatabase getDB(String dbName) {
		MongoDatabase db = getMongoClient().getDatabase(dbName);
		return db;
	}

	public static MongoDatabase testDB() {
		return getDB("test");
	}

	public static void printDocuments(FindIterable<Document> documents) {
		documents.forEach(new Block<Document>() {
			@Override
			public void apply(Document t) {
				System.out.println(t);
			}
		});
	}
}
