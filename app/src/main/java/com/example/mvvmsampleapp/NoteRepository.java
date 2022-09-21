package com.example.mvvmsampleapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<NoteModel>> noteModelList;

    public NoteRepository(Application application)
    {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        noteModelList = noteDao.getAllData();
    }

    public void insertData(NoteModel noteModel)
    {
        new InsertTask(noteDao).execute(noteModel);

    }
    public void updateData(NoteModel noteModel)
    {
        new UpdateTask(noteDao).execute(noteModel);
    }
    public void deleteData(NoteModel noteModel)
    {
        new DeleteTask(noteDao).execute(noteModel);

    }

    public LiveData<List<NoteModel>> getAllData()
    {
        return noteModelList;
    }
    private static class InsertTask extends AsyncTask<NoteModel,Void,Void>{

        private NoteDao noteDao;

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {

            noteDao.insert(noteModels[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<NoteModel,Void,Void>{

        private NoteDao noteDao;

        public UpdateTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {

            noteDao.update(noteModels[0]);
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<NoteModel,Void,Void>{

        private NoteDao noteDao;

        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {

            noteDao.delete(noteModels[0]);
            return null;
        }
    }
}
