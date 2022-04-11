package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();

    public RecyclerAdapter(Context context, ArrayList<String> titles, ArrayList<String> descriptions, ArrayList<String> prices, ArrayList<String> images) {
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
        this.prices = prices;
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.flowers, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.description.setText(descriptions.get(position));
        holder.price.setText(prices.get(position));

        //for image view
        int imageResource = context.getResources().getIdentifier(images.get(position), null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.imageView.setImageDrawable(res);

        //for star
        Drawable res2 = context.getResources().getDrawable(android.R.drawable.btn_star_big_off);
        holder.favouriteImageView.setImageDrawable(res2);

        holder.favouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.favouriteImageView.getDrawable() == context.getResources().getDrawable(android.R.drawable.btn_star_big_off)) {
                    holder.favouriteImageView.setImageDrawable(context.getResources().getDrawable(android.R.drawable.btn_star_big_on));
                }
                else {
                    holder.favouriteImageView.setImageDrawable(res2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, price;
        ImageView imageView, favouriteImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.titleTextView);
            description = (TextView) itemView.findViewById(R.id.descriptionTextView);
            price = (TextView) itemView.findViewById(R.id.priceTextView);
            favouriteImageView = (ImageView) itemView.findViewById(R.id.favouriteImageView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
