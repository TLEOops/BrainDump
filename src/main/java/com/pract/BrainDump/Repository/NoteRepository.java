package com.pract.BrainDump.Repository;

import com.pract.BrainDump.Entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
}


//MongoRepository is a generic interface that takes two type parameters:
//The entity class (the type of object you’re saving in MongoDB) AND The ID type of that entity (@Id field in your class)