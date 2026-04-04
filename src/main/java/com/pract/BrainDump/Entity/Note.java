package com.pract.BrainDump.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    private String id;
    @NonNull
    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


}

//#Document --> Maps the class to a MongoDB collection(table in mongodb)
//
//By default, the collection name is the class name (note → note collection).
//
//You can override it with @Document(collection = "notes").

//@AllArgsConstructor generates:
//A constructor with all fields as parameters
//@AllArgsConstructor prevents you from writing:
// public Note(String id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }

//@NoArgsConstructor generates:
//A default (empty) constructor

//@Data generated getters and setters