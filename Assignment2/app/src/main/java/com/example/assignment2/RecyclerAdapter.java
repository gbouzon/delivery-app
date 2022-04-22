package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> products = new ArrayList<>();

    boolean isChecked = false;

    public RecyclerAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
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

        holder.title.setText(products.get(position).getTitle());
        holder.description.setText(products.get(position).getDescription());
        holder.price.setText("$" + products.get(position).getPrice());

        //for image view
        int imageResource = context.getResources().getIdentifier(products.get(position).getImage(), null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.imageView.setImageDrawable(res);

        //for star
        Drawable res2 = context.getResources().getDrawable(android.R.drawable.btn_star_big_off);
        holder.favouriteImageView.setImageDrawable(res2);

        holder.favouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isChecked) {
                    //Toast.makeText(context, "image is: " + holder.favouriteImageView.getDrawable(), Toast.LENGTH_SHORT).show();
                    holder.favouriteImageView.setImageDrawable(context.getResources().getDrawable(android.R.drawable.btn_star_big_on));
                    isChecked = true;
                }
                else {
                    holder.favouriteImageView.setImageDrawable(res2);
                    isChecked = false;
                }
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, price;
        ImageView imageView, favouriteImageView;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.titleTextView);
            description = (TextView) itemView.findViewById(R.id.descriptionTextView);
            price = (TextView) itemView.findViewById(R.id.priceTextView);
            favouriteImageView = (ImageView) itemView.findViewById(R.id.favouriteImageView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }
}
