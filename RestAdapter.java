package com.sp.foodlimits;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestAdapter extends RecyclerView.Adapter<RestAdapter.MyViewHolder> {

    Context context;
    ArrayList<RestUser> RestUserArrayList;


    public RestAdapter(Context context, ArrayList<RestUser> restUserArrayList) {
        this.context = context;
        this.RestUserArrayList = restUserArrayList;

    }

    @NonNull
    @Override
    public RestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.rest_show_items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RestUser restUser = RestUserArrayList.get(position);
        holder.Fooditem.setText(restUser.getFooditem());
        holder.Description.setText(restUser.getDescription());
        holder.Paymentmode.setText(restUser.getPaymentmode());
        holder.Price.setText(restUser.getPrice());
        holder.RestaurantLocation.setText(restUser.getRestaurantLocation());
        holder.Contact.setText(restUser.getContact());


    }

    @Override
    public int getItemCount() {
        return RestUserArrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Description, Fooditem, Paymentmode, Price, RestaurantLocation, Contact;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Description = itemView.findViewById(R.id.displayrestdesc);
            Fooditem = itemView.findViewById(R.id.displayrestfood);
            Paymentmode = itemView.findViewById(R.id.dsiplayrestpay);
            Price = itemView.findViewById(R.id.displayrestprice);
            RestaurantLocation = itemView.findViewById(R.id.displayrestloc);
            Contact = itemView.findViewById(R.id.displayrestcontact);


        }
    }
}
