package com.example.mongojava.nativejava.models;


import lombok.*;
import org.bson.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Geo {
    private String lat;
    private String lng;

    public Geo(Document document) {
        this.lat = document.getString("lat");
        this.lng = document.getString("lng");
    }
}
