package com.sp.foodlimits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuperAdapter extends RecyclerView.Adapter<SuperAdapter.MyViewHolder> {

    Context context;
    ArrayList<SuperUser> SuperUserArrayList;


    public SuperAdapter(Context context, ArrayList<SuperUser> superUserArrayList) {
        this.context = context;
        this.SuperUserArrayList = superUserArrayList;

    }

    @NonNull
    @Override
    public SuperAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.super_show_items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SuperUser superUser = SuperUserArrayList.get(position);
        holder.Fooditem.setText(superUser.getFooditem());
        holder.Expirydate.setText(superUser.getExpirydate());
        holder.Paymentmode.setText(superUser.getPaymentmode());
        holder.Price.setText(superUser.getPrice());
        holder.SupermarketLocation.setText(superUser.getSupermarketLocation());
        holder.Contact.setText(superUser.getContact());


    }

    @Override
    public int getItemCount() {
        return SuperUserArrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Expirydate, Fooditem, Paymentmode, Price, SupermarketLocation, Contact;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Expirydate = itemView.findViewById(R.id.displayrestdesc);
            Fooditem = itemView.findViewById(R.id.displayrestfood);
            Paymentmode = itemView.findViewById(R.id.dsiplayrestpay);
            Price = itemView.findViewById(R.id.displayrestprice);
            SupermarketLocation = itemView.findViewById(R.id.displayrestloc);
            Contact = itemView.findViewById(R.id.displayrestcontact);

        }
    }
}
