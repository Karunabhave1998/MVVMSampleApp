package com.example.mvvmsampleapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<NoteModel>> noteModelList;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        noteModelList =noteRepository.getAllData();
    }
    public void insert(NoteModel noteModel)
    {
        noteRepository.insertData(noteModel);
    }

    public void update(NoteModel noteModel)
    {
        noteRepository.updateData(noteModel);
    }

    public void delete(NoteModel noteModel)
    {
        noteRepository.deleteData(noteModel);
    }

    public LiveData<List<NoteModel>> getAllNotes()
    {
        return noteModelList;
    }
}
