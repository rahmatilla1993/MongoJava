package com.example.mongojava.nativejava.repo;

import com.example.mongojava.nativejava.models.User;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.*;
import java.util.regex.Pattern;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class UserRepositoryImpl implements UserRepository {
    private static final MongoClient CLIENT = MongoClients.create("mongodb://localhost:27017/?directConnection=true");
    private static final MongoDatabase DB;
    private static final MongoCollection<Document> COLLECTION;

    static {
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder()
                        .automatic(true)
                        .build())
        );
        DB = CLIENT.getDatabase("mongo-test").withCodecRegistry(codecRegistry);
        COLLECTION = DB.getCollection("users");
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        FindIterable<Document> documents = COLLECTION.find();
        documents.forEach(document -> {
            users.add(new User(document));
        });
        return users;
    }

    @Override
    public Optional<User> findById(String id) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        Document document = COLLECTION.find(filter).first();
        if (document != null) {
            return Optional.of(new User(document));
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllByZipCodeEndWith(String a, String b) {
        List<User> users = new ArrayList<>();
        Bson filter = Filters.or(
                Filters.regex("address.zipcode", "^.*%s$".formatted(a)),
                Filters.regex("address.zipcode", "^.*%s$".formatted(b))
        );
        FindIterable<Document> documents = COLLECTION.find(filter);
        documents.forEach(document -> users.add(new User(document)));
        return users;
    }

    @Override
    public List<User> findAllByLatitudeWithNegative() {
        List<User> users = new ArrayList<>();
        FindIterable<Document> documents = COLLECTION.find(
                Filters.where("parseFloat(this.address.geo.lat) < 0")
        );
        documents.forEach(document -> users.add(new User(document)));
        return users;
    }

    @Override
    public List<User> findAllByWebSiteEndWithDomain(String domain) {
        List<User> users = new ArrayList<>();
        FindIterable<Document> documents = COLLECTION.find(
                Filters.regex("website", Pattern.compile("^.*%s$".formatted(domain)))
        );
        documents.forEach(document -> users.add(new User(document)));
        return users;
    }

    @Override
    public User save(User user) {
        Map<String, Object> map = Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "address", user.getAddress(),
                "phone", user.getPhone(),
                "website", user.getWebsite(),
                "company", user.getCompany()
        );
        Document document = new Document(map);
        InsertOneResult insertOneResult = COLLECTION.insertOne(document);
        if (insertOneResult.wasAcknowledged()) {
            ObjectId mongoId = Objects.requireNonNull(insertOneResult.getInsertedId()).asObjectId().getValue();
            user.setMongoId(mongoId);
            return user;
        }
        return null;
    }

    @Override
    public boolean edit(User user) {
        Bson filter = Filters.eq("_id", user.getMongoId());
        UpdateResult updateResult = COLLECTION.updateOne(filter, Updates.combine(
                Updates.set("name", user.getName()),
                Updates.set("username", user.getUsername()),
                Updates.set("email", user.getEmail()),
                Updates.set("address", user.getAddress()),
                Updates.set("phone", user.getPhone()),
                Updates.set("website", user.getWebsite()),
                Updates.set("company", user.getCompany())
        ));
        return updateResult.wasAcknowledged();
    }

    @Override
    public boolean editOfCatchPhraseAndLongitude(String id, String phrase, String lng) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        return COLLECTION.updateOne(filter, Updates.combine(
                Updates.set("company.catchPhrase", phrase),
                Updates.set("address.geo.lng", lng)
        )).wasAcknowledged();

    }

    @Override
    public boolean delete(String id) {
        return COLLECTION.deleteOne(
                Filters.eq("_id", new ObjectId(id))
        ).wasAcknowledged();
    }
}
