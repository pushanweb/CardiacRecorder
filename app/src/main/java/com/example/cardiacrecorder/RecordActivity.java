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

public class RecordActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton, timeButton;
    int hour, minute;
    String date, time;
    EditText Systolic,Diastolic,HeartRate,Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        timeButton = findViewById(R.id.timePickerButton);
        timeButton.setOnClickListener(view -> {
            initTimePicker();
        });

        Systolic = findViewById(R.id.systolic);

        Diastolic = findViewById(R.id.diastolic);

        HeartRate = findViewById(R.id.heartrate);

        Comment = findViewById(R.id.comment);

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
                recordDb.child(Calendar.getInstance().getTime().toString()).setValue(main).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RecordActivity.this,"Added Record",Toast.LENGTH_SHORT).show();
                    }
                });

                new Handler().postDelayed(() -> {
                    Intent homeIntent = new Intent(RecordActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();

                },500);
            }
            else{
                Toast.makeText(RecordActivity.this,"Please Fill All The Fields",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This sets the time obtained from the user
     */
    private void initTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                RecordActivity.this,
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
     * This returns today's date
     * @return
     *      Return today's date
     */
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return (day + "-" + month + "-" + year);
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