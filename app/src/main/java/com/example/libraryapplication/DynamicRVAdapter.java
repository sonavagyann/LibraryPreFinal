package com.example.libraryapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DynamicRVAdapter extends RecyclerView.Adapter<DynamicRVAdapter.MyViewHolder> {

    //int  images[];
    //private final RVInterface rvInterface;
    Context context;
    ArrayList<DynamicRVModel> dynamicRVModels;

    //+RVInterface rvInterface nerqevy
    public  DynamicRVAdapter(Context context, ArrayList<DynamicRVModel> dynamicRVModels){
        this.context=context;
        this.dynamicRVModels=dynamicRVModels;
        //this.rvInterface=rvInterface;

    }

    @NonNull
    @Override
    public DynamicRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dynamic_rv_item_layout, parent, false);
        return new DynamicRVAdapter.MyViewHolder(view);//+rvInterface
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(dynamicRVModels.get(position).getTitle());
        holder.author.setText(dynamicRVModels.get(position).getAuthor());
        holder.pages.setText(dynamicRVModels.get(position).getPages());
        holder.myImage.setImageResource(dynamicRVModels.get(position).getImages());
    }

    @Override
    public int getItemCount() {
        return dynamicRVModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, author, pages;
        ImageView myImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
            pages = itemView.findViewById(R.id.book_pages);
            myImage = itemView.findViewById(R.id.book_cover);

            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rvInterface!=null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            rvInterface.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }
}
