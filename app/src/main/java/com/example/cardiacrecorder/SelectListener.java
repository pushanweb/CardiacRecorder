package com.example.cardiacrecorder;
/**
 * This is an interface that gets a record model object when a recycler view Item is clicked
 */
public interface SelectListener {
    void onItemClicked(RecordsModel recordModel);
}
