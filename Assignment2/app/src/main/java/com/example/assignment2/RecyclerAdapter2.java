package com.example.assignment2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {

    Context context;
    ArrayList<Product> products = new ArrayList<>();
    DatabaseHelper db;

    boolean isChecked = false;

    public RecyclerAdapter2(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        db = new DatabaseHelper(context);
        db.getReadableDatabase();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.flowers_nobuttons, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter2.ViewHolder holder, int position) {
        Product current = products.get(position);

        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDescription());
        holder.price.setText("$" + current.getPrice());

        //for image view
        int imageResource = context.getResources().getIdentifier(current.getImage(), null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.imageView.setImageDrawable(res);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addToCart(db.getIdByTitle(current.getTitle()));
            }
        });

        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.removeFromCart(db.getIdByTitle(current.getTitle()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, price;
        ImageView imageView;
        Button button, button2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.titleTextView);
            description = (TextView) itemView.findViewById(R.id.descriptionTextView);
            price = (TextView) itemView.findViewById(R.id.priceTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            button = (Button) itemView.findViewById(R.id.button);
            button2 = (Button) itemView.findViewById(R.id.button2);
        }
    }
}
