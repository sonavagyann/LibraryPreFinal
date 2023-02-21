package com.example.libraryapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class DynamicRVAdapter extends RecyclerView.Adapter<DynamicRVAdapter.MyViewHolder>{

    private RVInterface rvInterface;
    Context context;
    ArrayList<DynamicRVModel> dynamicRVModels;

    private IClickAddFavListener iClickAddFavListener;

    public interface IClickAddFavListener {
        void onClickAddFav(ImageView addFavImg, DynamicRVModel dynamicRVModel);
    }


    public void setData(ArrayList<DynamicRVModel> list, IClickAddFavListener listener){
        this.dynamicRVModels = list;
        this.iClickAddFavListener = listener;
        notifyDataSetChanged();
    }



    public  DynamicRVAdapter(Context context, ArrayList<DynamicRVModel> dynamicRVModels, RVInterface rvInterface){
        this.context=context;
        this.dynamicRVModels = dynamicRVModels;
        this.dynamicRVModels=dynamicRVModels;
        //this.dynamicRVModels = new ArrayList<>(dynamicRVModelsFilter);
        this.rvInterface=rvInterface;

    }


    @NonNull
    @Override
    public DynamicRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(context);
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item_layout, parent, false);
        return new DynamicRVAdapter.MyViewHolder(view, rvInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(dynamicRVModels.get(position).getTitle());
        holder.author.setText(dynamicRVModels.get(position).getAuthor());
        holder.pages.setText(dynamicRVModels.get(position).getPages());
        holder.myImage.setImageResource(dynamicRVModels.get(position).getImages());

    }

    @Override
    public int getItemCount() {
        return dynamicRVModels.size();
    }

    public void filterList(ArrayList<DynamicRVModel> filteredList){
        dynamicRVModels = filteredList;
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, author, pages;
        ImageView myImage, addFavImg;
        public MyViewHolder(@NonNull View itemView, RVInterface rvInterface) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
            pages = itemView.findViewById(R.id.book_pages);
            myImage = itemView.findViewById(R.id.book_cover);
            addFavImg = itemView.findViewById(R.id.addToFav);


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
