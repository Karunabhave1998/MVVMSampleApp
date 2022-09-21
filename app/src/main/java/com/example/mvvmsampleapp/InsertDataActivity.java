package com.example.mvvmsampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmsampleapp.databinding.ActivityInsertDataBinding;

public class InsertDataActivity extends AppCompatActivity {

    ActivityInsertDataBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if (type.equals("update")) {

            setTitle("Update Note");

            binding.titleEdtId.setText(getIntent().getStringExtra("title"));
            binding.descriptionId1.setText(getIntent().getStringExtra("description"));
            int id= getIntent().getIntExtra("id",0);
          binding.add.setText("Update Note");
          binding.add.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent();
                  intent.putExtra("title", binding.titleEdtId.getText().toString());
                  intent.putExtra("description", binding.descriptionId1.getText().toString());
                  intent.putExtra("id",id);
                  setResult(RESULT_OK, intent);
                  finish();

              }
          });

        } else {

            setTitle("Add Note");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.putExtra("title", binding.titleEdtId.getText().toString());
                    intent.putExtra("description", binding.descriptionId1.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InsertDataActivity.this,MainActivity.class));
    }
}