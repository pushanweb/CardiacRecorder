package com.example.cardiacrecorder;

public class RecordsModel {
    String date;
    String time;
    String diastolic;
    String systolic;
    String heartRate;
    String comments;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public RecordsModel(){

    }

    public RecordsModel(String date, String time, String diastolic, String systolic, String heartRate, String comments, String key) {
        this.date = date;
        this.time = time;
        this.diastolic = diastolic;
        this.systolic = systolic;
        this.heartRate = heartRate;
        this.comments = comments;
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
