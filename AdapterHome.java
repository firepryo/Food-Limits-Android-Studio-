package com.sp.foodlimits;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder> {

    Context context;
    ArrayList<UserHome> UserArrayList;


    public AdapterHome(Context context, ArrayList<UserHome> UserArrayList) {
        this.context = context;
        this.UserArrayList = UserArrayList;

    }

    @NonNull
    @Override
    public AdapterHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.rest_show_items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserHome userHome= UserArrayList.get(position);
        holder.Fooditem.setText(userHome.getFooditem());
        holder.Description.setText(userHome.getDescription());
        holder.Paymentmode.setText(userHome.getPaymentmode());
        holder.Price.setText(userHome.getPrice());
        holder.RestaurantLocation.setText(userHome.getRestaurantLocation());
        holder.Contact.setText(userHome.getContact());


    }

    @Override
    public int getItemCount() {
        return UserArrayList.size();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,HomePayment.class);
                    context.startActivity(intent);
                }
            });

        }
    }
}
