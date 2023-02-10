package com.example.libraryapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StaticRVAdapter extends RecyclerView.Adapter<StaticRVAdapter.MyViewHolder>{

    Context context;
    ArrayList<StaticRVModel> staticRVModels;

    public StaticRVAdapter(Context context, ArrayList<StaticRVModel> staticRVModels) {
        this.context=context;
        this.staticRVModels=staticRVModels;
    }

    @NonNull
    @Override
    public StaticRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.static_rv_item, parent, false);
        return new StaticRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        holder.text.setText(staticRVModels.get(position).getGenres());

    }

    @Override
    public int getItemCount(){
        return staticRVModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
}
