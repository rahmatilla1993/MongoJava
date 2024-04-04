package com.example.mongojava.nativejava.models;


import lombok.*;
import org.bson.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(Document document) {
        this.name = document.getString("name");
        this.catchPhrase = document.getString("catchPhrase");
        this.bs = document.getString("bs");
    }
}
