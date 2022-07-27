package com.example.cardiacrecorder;

/**
 * This is a class that keeps track of the records
 */
public class RecordsModel {
    String date;
    String time;
    String diastolic;
    String systolic;
    String heartRate;
    String comments;
    String key;

    public RecordsModel(){

    }

    /**
     * This adds a city to the list if that city does not exist
     * @param date
     *             set date
     * @param time
     *             set time
     * @param diastolic
     *             set diastolic
     * @param systolic
     *             set systolic
     * @param heartRate
     *              set heartRate
     * @param comments
     *              set comments
     */
    public RecordsModel(String date, String time, String diastolic, String systolic, String heartRate, String comments, String key) {
        this.date = date;
        this.time = time;
        this.diastolic = diastolic;
        this.systolic = systolic;
        this.heartRate = heartRate;
        this.comments = comments;
        this.key = key;
    }

    /**
     * This returns the key
     * @return
     *      Return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * This returns the date
     * @return
     *      Return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * This returns the time
     * @return
     *      Return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * This returns the Diastolic
     * @return
     *      Return the Diastolic
     */
    public String getDiastolic() {
        return diastolic;
    }

    /**
     * This returns the Systolic
     * @return
     *      Return the Systolic
     */
    public String getSystolic() {
        return systolic;
    }

    /**
     * This returns the heartRate
     * @return
     *      Return the heartRate
     */
    public String getHeartRate() {
        return heartRate;
    }

    /**
     * This returns the comments
     * @return
     *      Return the comments
     */
    public String getComments() {
        return comments;
    }
}
