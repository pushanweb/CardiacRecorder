package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button add = findViewById(R.id.add);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this , RecordActivity.class);
            startActivity(intent);
            finish();
        });
    }
}