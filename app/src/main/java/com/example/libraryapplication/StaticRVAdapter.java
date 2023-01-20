package com.example.libraryapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRVAdapter extends RecyclerView.Adapter<StaticRVAdapter.StaticRVViewHolder>{

    private ArrayList<StaticRVModel> items;
    int row_index = -1;


    public StaticRVAdapter(ArrayList<StaticRVModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item, parent, false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, @SuppressLint("RecyclerView") int position) {
        StaticRVModel currentItem = items.get(position);
        holder.textView.setText(currentItem.getText());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if(row_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_background);
        }
        else{
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_background);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.text);
            linearLayout=itemView.findViewById(R.id.linearlayout);
        }
    }
}
