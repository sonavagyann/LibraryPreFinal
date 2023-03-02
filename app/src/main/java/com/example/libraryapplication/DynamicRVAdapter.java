package com.example.libraryapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class DynamicRVAdapter extends RecyclerView.Adapter<DynamicRVAdapter.MyViewHolder> {

    private RVInterface rvInterface;

    Context context;

    private View.OnClickListener listener;
    ArrayList<DynamicRVModel> dynamicRVModels;

    ArrayList<DynamicRVModel> readList;



    public  DynamicRVAdapter(Context context, ArrayList<DynamicRVModel> dynamicRVModels, RVInterface rvInterface){

        this.context=context;
        this.dynamicRVModels=dynamicRVModels;
        this.rvInterface=rvInterface;

    }


    @NonNull
    @Override
    public DynamicRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item_layout, parent, false);
        return new DynamicRVAdapter.MyViewHolder(view, rvInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        //holder.genres.setText(dynamicRVModels.get(position).getGenres());
        holder.title.setText(dynamicRVModels.get(position).getTitle());
        holder.author.setText(dynamicRVModels.get(position).getAuthor());
        holder.pages.setText(dynamicRVModels.get(position).getPages());
        holder.myImage.setImageResource(dynamicRVModels.get(position).getImages());
        holder.addFavImg.setImageResource(R.drawable.add_favorite);
        holder.addFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        holder.addReadImg.setImageResource(R.drawable.baseline_add_24);
        holder.addReadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dynamicRVModels.size();
    }

    public void filterList(ArrayList<DynamicRVModel> filteredList){
        dynamicRVModels = filteredList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView genres, title, author, pages;
        ImageView myImage, addFavImg, addReadImg;
        public MyViewHolder(@NonNull View itemView, RVInterface rvInterface) {
            super(itemView);

            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
            pages = itemView.findViewById(R.id.book_pages);
            myImage = itemView.findViewById(R.id.book_cover);
            addFavImg = itemView.findViewById(R.id.addToFav);
            addReadImg = itemView.findViewById(R.id.addToRead);


            itemView.setOnClickListener(view -> {
                if(rvInterface!=null){
                    int position = getAbsoluteAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        rvInterface.onItemClick(position);
                    }
                }
            });
        }

    }
}
