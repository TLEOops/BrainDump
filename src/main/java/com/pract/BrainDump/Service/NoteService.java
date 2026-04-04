package com.pract.BrainDump.Service;

import com.pract.BrainDump.Entity.Note;
import com.pract.BrainDump.Entity.User;
import com.pract.BrainDump.Repository.NoteRepository;
import com.pract.BrainDump.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserService userService;

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Note saveNote(Note note, String userName){
        User user = userService.findByUserName(userName);
        note.setCreatedAt(LocalDateTime.now());
        Note savedNote = noteRepository.save(note);
        user.getNotes().add(savedNote);
        userService.saveUser(user);
        return noteRepository.save(note);
    }


    public boolean deleteNote(String id) {
        noteRepository.deleteById(id);
        return true;
    }

    public Note editNote(String id, Note editedNote) {
        Note existingNote = noteRepository.findById(id).orElse(null);
        if(existingNote == null){
            return null;
        }
        existingNote.setTitle(editedNote.getTitle()); //Fetches the title from the incoming note (getter) and Updates the title in the existing note (setter)
        existingNote.setContent(editedNote.getContent());
        existingNote.setUpdatedAt(LocalDateTime.now());
        return noteRepository.save(existingNote);
    }

    //why Note existingNote = noteRepository.findById(id).orElse(null); and not Note existingNote = noteRepository.findById(id);
    //Because noteRepository.findById(id) doesn’t actually return a Note object — it returns an Optional<Note>. Optional<T> is a wrapper class introduced in Java 8.
    //It’s used to represent the idea that “There may or may not be a value here.”
    //🧱 So, this line:
    //Note existingNote = noteRepository.findById(id);
    //❌ Doesn’t compile, because the types don’t match:
    //
    //Optional<Note> cannot be directly assigned to a Note.
    //✅ Correct ways to handle it
    //✅ Option 1: Use .orElse(null) (your example)
    //Note existingNote = noteRepository.findById(id).orElse(null);
    //If the note exists, existingNote gets the Note object.
    //
    //If it doesn’t exist, it becomes null.
    //
    //This is convenient for small projects, but you must handle null carefully.
    //
    //✅ Option 2: Use .orElseThrow() (better for production)
    //Note existingNote = noteRepository.findById(id)
    //    .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
    //This throws a clear exception instead of returning null.
    //
    //✅ Option 3: Use .ifPresent() or .isPresent()
    //
    //If you only want to check whether the note exists:
    //
    //Optional<Note> noteOptional = noteRepository.findById(id);
    //
    //if (noteOptional.isPresent()) {
    //    Note existingNote = noteOptional.get();
    //    // update or do something
    //}


    public Optional<Note> findNoteById(String id) {    //Optional<T> is a container object that may or may not contain a non-null value.
        //Instead of returning null when a note isn’t found, you return an empty Optional. Avoids NullPointerException.
        return noteRepository.findById(id);
    }
}
