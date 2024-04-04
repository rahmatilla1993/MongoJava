package com.example.mongojava.nativejava.models;

import lombok.*;
import org.bson.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address(Document document) {
        this.street = document.getString("street");
        this.suite = document.getString("suite");
        this.city = document.getString("city");
        this.zipcode = document.getString("zipcode");
        this.geo = new Geo(document.get("geo", Document.class));
    }
}
