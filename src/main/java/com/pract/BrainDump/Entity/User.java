package com.pract.BrainDump.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Users")
@Data
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    private List<Note> notes = new ArrayList<>();
    //list of all the notes of this user
    //new ArrayList<>() <- as soon as the user is initialized, there will be an empty list of notes instead of null


}

//this entity is basically created to implement the login functionality such that every user must be having various notes

//@DBRef <- for creating the reference of notes, in this field (notes), there won't be complete note but a reference of notes (e.g., IDs of notes of this particular user will be shown)