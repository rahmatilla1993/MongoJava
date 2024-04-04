package com.example.mongojava.nativejava;

import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class MongoRunner {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/?directConnection=true")) {
            MongoDatabase db = mongoClient.getDatabase("mongo-test");
            MongoCollection<Document> collection = db.getCollection("users");

            Bson sort = Sorts.descending("name");
            FindIterable<Document> documents = collection.find().sort(sort);
            documents.forEach(document -> {
                ObjectId _id = document.getObjectId("_id");
                Integer id = document.getInteger("id");
                String name = document.getString("name");
                String username = document.getString("username");
                System.out.printf("_id: %s, id: %d, name: %s, username: %s%n", _id.toString(), id, name, username);
            });
        }
    }
}
