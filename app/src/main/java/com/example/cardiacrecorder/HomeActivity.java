package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements SelectListener{

    ArrayList<RecordsModel> recordArray=new ArrayList<RecordsModel>();

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

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("Records");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    recordArray.add(new RecordsModel(
                            ds.child("Date").getValue().toString(),
                            ds.child("Time").getValue().toString(),
                            ds.child("Diastolic").getValue().toString(),
                            ds.child("Systolic").getValue().toString(),
                            ds.child("Heart Rate").getValue().toString(),
                            ds.child("Comments").getValue().toString(),
                            ds.getKey()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });

        CR_RecyclerViewAdapter adapter = new CR_RecyclerViewAdapter(this,recordArray,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * gets the RecordModel object on Item click
     * @param recordModel
     *
     */
    @Override
    public void onItemClicked(RecordsModel recordModel) {
        Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
        intent.putExtra("key", recordModel.getKey());
        intent.putExtra("date", recordModel.getDate());
        intent.putExtra("time", recordModel.getTime());
        intent.putExtra("systolic", recordModel.getSystolic());
        intent.putExtra("diastolic", recordModel.getDiastolic());
        intent.putExtra("heartRate", recordModel.getHeartRate());
        intent.putExtra("comment", recordModel.getComments());
        startActivity(intent);
        finish();
    }
}