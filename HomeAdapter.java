package com.sp.foodlimits;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    Context context;
    ArrayList<HomeUser> HomeUserArrayList;


    public HomeAdapter(Context context, ArrayList<HomeUser> homeUserArrayList) {
        this.context = context;
        this.HomeUserArrayList = homeUserArrayList;

    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.home_showitems,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, int position) {
        HomeUser homeUser = HomeUserArrayList.get(position);
        holder.Fooditem.setText(homeUser.getFooditem());
        holder.Expirydate.setText(homeUser.getExpirydate());
        holder.Paymentmode.setText(homeUser.getPaymentmode());
        holder.Price.setText(homeUser.getPrice());
        holder.SupermarketLocation.setText(homeUser.getSupermarketLocation());
        holder.Contact.setText(homeUser.getContact());


    }

    @Override
    public int getItemCount() {
        return HomeUserArrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Expirydate, Fooditem, Paymentmode, Price, SupermarketLocation, Contact;
        ImageView picture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Expirydate = itemView.findViewById(R.id.displayrestdesc);
            Fooditem = itemView.findViewById(R.id.displayrestfood);
            Paymentmode = itemView.findViewById(R.id.dsiplayrestpay);
            Price = itemView.findViewById(R.id.displayrestprice);
            SupermarketLocation = itemView.findViewById(R.id.displayrestloc);
            Contact = itemView.findViewById(R.id.displayrestcontact);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context,HomePayment.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
