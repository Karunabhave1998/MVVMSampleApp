package com.example.mvvmsampleapp;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmsampleapp.databinding.NoteRvBinding;

public class RecyclerAdapter extends ListAdapter<NoteModel,RecyclerAdapter.ViewHolder> {

    public RecyclerAdapter()
    {

     super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<NoteModel> CALLBACK=new DiffUtil.ItemCallback<NoteModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull NoteModel oldItem, @NonNull NoteModel newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NoteModel oldItem, @NonNull NoteModel newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription());
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        NoteModel noteModel = getItem(position);
        holder.binding.titleId.setText(noteModel.getTitle());
       holder.binding.descriptionId.setText(noteModel.getDescription());

    }

    public NoteModel getNoteModel(int position)
    {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        NoteRvBinding binding;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding=NoteRvBinding.bind(itemView);
        }
    }
}
