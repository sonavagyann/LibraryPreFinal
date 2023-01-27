package com.example.libraryapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StaticRVAdapter extends RecyclerView.Adapter<StaticRVAdapter.StaticRVViewHolder>{

    public StaticRVAdapter(List items, Context context) {
        this.items = items;
        this.context = context;
    }

    private List<StaticRVModel> items;
    Context context;
    int row_index = -1;



    public StaticRVAdapter(List<StaticRVModel> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StaticRVViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(items.get(position).getText());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if(row_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_background);
        }
        else{
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_background);

        }
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout linearLayout;

        //had NonNull
        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.text);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearlayout);
        }
    }
}
