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

//implements Filterable

public class DynamicRVAdapter extends RecyclerView.Adapter<DynamicRVAdapter.MyViewHolder>implements Filterable{

    private RVInterface rvInterface;
    Context context;
    ArrayList<DynamicRVModel> dynamicRVModels;
    ArrayList<DynamicRVModel> dynamicRVModelsFull;

    private IClickAddFavListener iClickAddFavListener;

    public interface IClickAddFavListener {
        void onClickAddFav(ImageView addFavImg, DynamicRVModel dynamicRVModel);
    }


    /*
    public void setFilteredList(ArrayList<DynamicRVModel> filteredList){
        this.dynamicRVModels = filteredList;
        notifyDataSetChanged();
    }*/

    public void setData(ArrayList<DynamicRVModel> list, IClickAddFavListener listener){
        this.dynamicRVModels = list;
        this.iClickAddFavListener = listener;
        notifyDataSetChanged();
    }



    public  DynamicRVAdapter(Context context, ArrayList<DynamicRVModel> dynamicRVModels, RVInterface rvInterface){
        this.context=context;
        this.dynamicRVModels = dynamicRVModels;
        this.dynamicRVModelsFull=dynamicRVModels;
        this.dynamicRVModels = new ArrayList<>(dynamicRVModelsFull);
        this.rvInterface=rvInterface;

    }

    public void SearchList(ArrayList<DynamicRVModel> searchList){
        this.dynamicRVModels = searchList;
        notifyDataSetChanged();
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

        final DynamicRVModel dynamicRVModel = dynamicRVModels.get(position);
        if(dynamicRVModel == null){
            return;
        }

        holder.title.setText(dynamicRVModel.getTitle());
        holder.author.setText(dynamicRVModel.getAuthor());
        holder.pages.setText(dynamicRVModel.getPages());
        holder.myImage.setImageResource(dynamicRVModel.getImages());

        holder.addFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickAddFavListener.onClickAddFav(holder.addFavImg, dynamicRVModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dynamicRVModels.size();
    }



    @Override
    public Filter getFilter() {
        return booksFilter;
    }

    private final Filter booksFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<DynamicRVModel> filteredBooks = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){

                filteredBooks.addAll(dynamicRVModelsFull);

            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(DynamicRVModel books : dynamicRVModelsFull){

                    if(books.title.toLowerCase().contains(filterPattern))
                        filteredBooks.add(books);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredBooks;
            results.count = filteredBooks.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            dynamicRVModels.clear();
            dynamicRVModels.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();
        }
    };

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
