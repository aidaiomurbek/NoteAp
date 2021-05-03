package com.example.noteapp21.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.noteapp21.ui.models.Note;

@Database(entities = {Note.class},version = 2)

public abstract class AppDataBase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
