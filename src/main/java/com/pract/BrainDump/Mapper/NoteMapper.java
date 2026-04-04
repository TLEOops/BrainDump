package com.pract.BrainDump.Mapper;

import com.pract.BrainDump.DTO.CreateNoteRequest;
import com.pract.BrainDump.DTO.NoteResponse;
import com.pract.BrainDump.Entity.Note;

import java.time.LocalDateTime;

public class NoteMapper {

    public static Note toEntity(CreateNoteRequest request){

        Note note = new Note();

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());

        return note;
    }

    public static NoteResponse toResponse(Note note){

        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
