package com.example.mongojava.nativejava.models;

import lombok.*;
import org.bson.Document;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    private ObjectId mongoId;
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User(Document document) {
        this.mongoId = document.getObjectId("_id");
        this.id = document.getInteger("id");
        this.name = document.getString("name");
        this.username = document.getString("username");
        this.email = document.getString("email");
        this.address = new Address(document.get("address", Document.class));
        this.phone = document.getString("phone");
        this.website = document.getString("website");
        this.company = new Company(document.get("company", Document.class));
    }
}
