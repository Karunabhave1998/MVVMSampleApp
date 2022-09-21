package com.example.mvvmsampleapp;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public void insert(NoteModel noteModel);

    @Update
    public void update(NoteModel noteModel);

    @Delete
    public void delete(NoteModel noteModel);

    @Query("SELECT * FROM my_notes")
    public LiveData<List<NoteModel>> getAllData();


}
