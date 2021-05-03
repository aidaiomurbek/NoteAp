package com.example.noteapp21.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.noteapp21.ui.models.Note;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> getAll();
    @Insert
    void insert(Note note);
    @Delete
    void delete(Note note);

    @Query("SELECT * FROM note ORDER BY title ASC")
    List<Note> getsortedList();

}
