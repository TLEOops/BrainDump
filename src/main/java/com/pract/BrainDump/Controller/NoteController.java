package com.pract.BrainDump.Controller;


import com.pract.BrainDump.DTO.CreateNoteRequest;
import com.pract.BrainDump.DTO.NoteResponse;
import com.pract.BrainDump.Entity.Note;
import com.pract.BrainDump.Entity.User;
import com.pract.BrainDump.Mapper.NoteMapper;
import com.pract.BrainDump.Service.NoteService;
import com.pract.BrainDump.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
@Tag(name = "Note APIs")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllNotesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<Note> all = user.getNotes();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> findNoteById(String myId){
        Optional<User> user = userService.findUserById(myId);
        if(!user.isEmpty() && user != null){

        }
        if(noteService.findNoteById(myId).isPresent()){ //since we used Optional in service method which may or may not contain a non-null object so we use isPresent()
            return new ResponseEntity<>(findNoteById(myId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<NoteResponse> createNote(@RequestBody CreateNoteRequest request, @PathVariable String userName){

        try{
            Note note = NoteMapper.toEntity(request);
            Note savedNote = noteService.saveNote(note, userName);
            NoteResponse response = NoteMapper.toResponse(savedNote);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable String id, String userName){
        User user = userService.findByUserName(userName);
        user.getNotes().removeIf(x -> x.getId().equals(id));
        return new ResponseEntity<>(noteService.deleteNote(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> editNote(@PathVariable String id, @RequestBody Note editedNote){
        Note oldNote = noteService.findNoteById(id).orElse(null);
        if(oldNote != null){
            oldNote.setTitle(editedNote.getTitle() != null && !editedNote.getTitle().equals("") ? editedNote.getTitle() : oldNote.getTitle() );
            oldNote.setTitle(editedNote.getContent() != null && !editedNote.getContent().equals("") ? editedNote.getContent() : oldNote.getContent() );
            oldNote.setUpdatedAt(LocalDateTime.now());
        }
        return new ResponseEntity<>(noteService.editNote(id, editedNote), HttpStatus.CREATED);
    }


}
