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
    DatabaseHelper db;

    boolean isChecked = false;

    public RecyclerAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        db = new DatabaseHelper(context);
        db.getReadableDatabase();
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
        Product current = products.get(position);

        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDescription());
        holder.price.setText("$" + current.getPrice());

        //for image view
        int imageResource = context.getResources().getIdentifier(current.getImage(), null, context.getPackageName());
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
                    db.addFavourite(db.getIdByTitle(current.getTitle()));
                    isChecked = true;
                }
                else {
                    holder.favouriteImageView.setImageDrawable(res2);
                    db.removeFavourite(db.getIdByTitle(current.getTitle()));
                    isChecked = false;
                }
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addToCart(db.getIdByTitle(current.getTitle()));
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
