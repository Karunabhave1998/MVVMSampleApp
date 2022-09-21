package com.example.mvvmsampleapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmsampleapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NoteViewModel noteViewModel;

    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = new ViewModelProvider(MainActivity.this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);

        binding.floatingBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,InsertDataActivity.class);
                intent.putExtra("type","Add Note");
                 startActivityForResult(intent,1);

            }
        });

        binding.recyclerId.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerId.setHasFixedSize(true);

//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
//        binding.recyclerId.setAdapter(recyclerAdapter);

        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(List<NoteModel> noteModels) {

                 recyclerAdapter = new RecyclerAdapter();
                recyclerAdapter.submitList(noteModels);
                binding.recyclerId.setAdapter(recyclerAdapter);






            }
        });

       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               if(direction==ItemTouchHelper.RIGHT)
               {
                   noteViewModel.delete(recyclerAdapter.getNoteModel(viewHolder.getAdapterPosition()));
                   Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();

               }
               else {

                   Intent intent = new Intent(MainActivity.this,InsertDataActivity.class);
                   intent.putExtra("type","update");
                   intent.putExtra("title",recyclerAdapter.getNoteModel(viewHolder.getAdapterPosition()).getTitle());
                   intent.putExtra("description",recyclerAdapter.getNoteModel(viewHolder.getAdapterPosition()).getDescription());
                   intent.putExtra("id",recyclerAdapter.getNoteModel(viewHolder.getAdapterPosition()).getId());
                   startActivityForResult(intent,2);

                //   Toast.makeText(MainActivity.this, "Updating", Toast.LENGTH_SHORT).show();

               }

           }
       }).attachToRecyclerView(binding.recyclerId);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1) {


            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            NoteModel noteModel = new NoteModel(title, description);
            noteViewModel.insert(noteModel);
            Toast.makeText(this, "note added", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==2)
        {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            NoteModel noteModel = new NoteModel(title, description);
            noteModel.setId(data.getIntExtra("id",0));
            noteViewModel.update(noteModel);
            Toast.makeText(this, "note updated", Toast.LENGTH_SHORT).show();

        }

    }


}