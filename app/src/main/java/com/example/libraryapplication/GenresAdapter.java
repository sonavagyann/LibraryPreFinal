package com.example.libraryapplication;

import static com.example.libraryapplication.R.drawable.static_rv_background;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.MyViewHolder>{

    OnTabChangeListener listener;
    String[] genres;
    Context context;
    private static int lastClickedPosition = 0;

    public GenresAdapter(Context context, String[] genres, OnTabChangeListener listener) {
        this.context=context;
        this.listener=listener;
        this.genres=genres;
    }

    @NonNull
    @Override
    public GenresAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenresAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_display_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String genre = genres[position];
        holder.text.setText(genre);
        holder.itemView.setBackgroundResource(R.drawable.static_rv_background);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastClickedPosition != -1) {
                    view.setBackgroundResource(R.drawable.static_rv_selected_background);
                    notifyItemChanged(lastClickedPosition);
                }
                lastClickedPosition = position;
                listener.onTabChange(position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return genres.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            cardView=itemView.findViewById(R.id.cardView1);
        }
    }
}
