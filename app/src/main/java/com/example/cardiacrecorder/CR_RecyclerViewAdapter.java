package com.example.cardiacrecorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is a class for recycler viewAdapter that provide a binding from an app-specific data set to views that are displayed within a RecyclerView.
 */
public class CR_RecyclerViewAdapter extends RecyclerView.Adapter<CR_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<RecordsModel> recordArray;
    SelectListener listener;

    /**
     * This provide a binding with the RecordModel that are displayed within a RecyclerView
     * @param context
     *             obtains the context
     * @param recordArray
     *             hold the arraylist of RecordModel
     * @param listener
     *             holds SelectListener

     */
    public CR_RecyclerViewAdapter(Context context, ArrayList<RecordsModel> recordArray, SelectListener listener) {
        this.context = context;
        this.recordArray = recordArray;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CR_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new CR_RecyclerViewAdapter.MyViewHolder(view);
    }
    /**
     * to update the RecyclerView.ViewHolder contents with the item at the given position and also sets up some private fields to be used by RecyclerView.
     */
    @SuppressLint("SetTextI18n")
    @Override

    public void onBindViewHolder(@NonNull CR_RecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.date.setText("Date: " + recordArray.get(position).getDate());
        holder.time.setText("Time: " + recordArray.get(position).getTime());
        holder.systolic.setText("Systolic: " + recordArray.get(position).getSystolic());
        holder.diastolic.setText("Diastolic: " + recordArray.get(position).getDiastolic());
        holder.heartRate.setText("Heart Rate: " + recordArray.get(position).getHeartRate());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(recordArray.get(position));
            }
        });
    }
    /**
     * This returns count of the record on the database
     * @return
     *      Returns the total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return recordArray.size();
    }

    /**
     * This is a class that sets Content view as Item view for the recycler view
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date, time, systolic, diastolic, heartRate;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.recycler_date);
            time = itemView.findViewById(R.id.recycler_time);
            systolic = itemView.findViewById(R.id.recycler_systolic);
            diastolic = itemView.findViewById(R.id.recycler_diastolic);
            heartRate = itemView.findViewById(R.id.recycler_heart_rate);

            cardView = itemView.findViewById(R.id.card_view);

        }
    }
}
