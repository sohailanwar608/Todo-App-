package com.example.todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// data access object in mvvm
@Dao
public interface NoteDao
{
    @Insert
    public void insert(Note note);

   @Update
    public void update(Note note);

   @Delete
    public void delete(Note note);

   @Query("SELECT * FROM my_notes")
   // LiveData like link_list but much fast than link_list.
   public LiveData<List<Note>> getAllData();
}
