package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton, timeButton;
    int hour, minute;
    String date, time, systolic, diastolic, heartRate, comment, key;
    EditText Systolic,Diastolic,HeartRate,Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        key = getIntent().getStringExtra("key");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        systolic = getIntent().getStringExtra("systolic");
        diastolic = getIntent().getStringExtra("diastolic");
        heartRate = getIntent().getStringExtra("heartRate");
        comment = getIntent().getStringExtra("comment");

        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(date);
        initDatePicker();
        dateButton.setText(date);

        timeButton = findViewById(R.id.timePickerButton);
        timeButton.setText(time);
        timeButton.setOnClickListener(view -> {
            initTimePicker();
        });

        Systolic = findViewById(R.id.systolic);
        Systolic.setText(systolic);
        Diastolic = findViewById(R.id.diastolic);
        Diastolic.setText(diastolic);
        HeartRate = findViewById(R.id.heartrate);
        HeartRate.setText(heartRate);
        Comment = findViewById(R.id.comment);
        Comment.setText(comment);

        Button submit = findViewById(R.id.update);
        submit.setOnClickListener(view -> {
            if((date!=null && !date.isEmpty()) && (time!=null && !time.isEmpty()) && (Systolic.getText()!=null && !Systolic.getText().toString().isEmpty())
                    && (Diastolic.getText()!=null && !Diastolic.getText().toString().isEmpty()) &&
                    (HeartRate.getText()!=null && !HeartRate.getText().toString().isEmpty() )) {
                DatabaseReference recordDb = FirebaseDatabase.getInstance().getReference("Records");
                HashMap<String,String> main = new HashMap<>();
                main.put("Date",date);
                main.put("Time",time);
                main.put("Systolic",Systolic.getText().toString());
                main.put("Diastolic",Diastolic.getText().toString());
                main.put("Heart Rate",HeartRate.getText().toString());
                main.put("Comments",Comment.getText().toString());
                recordDb.child(key).setValue(main).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(DetailsActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                    }
                });

                new Handler().postDelayed(() -> {
                    Intent homeIntent = new Intent(DetailsActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();

                },500);
            }
            else{
                Toast.makeText(DetailsActivity.this,"Please Fill All The Fields",Toast.LENGTH_SHORT).show();
            }

        });

        Button delete = findViewById(R.id.Delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference recordDb = FirebaseDatabase.getInstance().getReference("Records");
                recordDb.child(key).removeValue();

                new Handler().postDelayed(() -> {
                    Intent homeIntent = new Intent(DetailsActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();
                },500);
                Toast.makeText(DetailsActivity.this,"Deleted Record",Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * This sets the time obtained from the user
     */
    private void initTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                DetailsActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                (timePicker, h, m) -> {
                    hour = h;
                    minute = m;

                    time = (hour < 10 ? "0"+hour : hour) + ":" + (minute < 10 ? "0"+minute : minute);
                    timeButton.setText(time);
                }, 24,0,true
        );

        timePickerDialog.updateTime(hour,minute);
        timePickerDialog.show();
    }

    /**
     * This sets the date obtained from the user
     */
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            date = day + "-" + month + "-" + year;
            dateButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    /**
     * This shows the date picker dialog
     * @param view
     *      This is the view where it is shown
     */
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}