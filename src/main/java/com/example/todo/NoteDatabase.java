package com.example.todo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase
{
    // static es lye byna rhy single object rhy h throughout the program that not consume much memory, we don't create it's object again and again
    private static NoteDatabase instance;
    public abstract NoteDao noteDao();

    // synchronized used because program dont affect to main thread and it is architecture component
    public static synchronized NoteDatabase getInstance(Context context)
    {

        // create database instance(object), if not present, therefor i do following work
        if (instance == null)

        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , NoteDatabase.class, "note_database").fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
