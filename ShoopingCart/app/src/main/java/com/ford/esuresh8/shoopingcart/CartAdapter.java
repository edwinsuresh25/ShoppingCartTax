package com.ford.esuresh8.shoopingcart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private ArrayList<CartDetails> itemList;

    CartAdapter(ArrayList<CartDetails> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listView = inflater.inflate(R.layout.activity_cart_items_details, viewGroup, false);

        MyViewHolder viewHolder = new MyViewHolder(listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder myViewHolder, int i) {
        DecimalFormat dec = new DecimalFormat("#0.00");
        CartDetails cartDetailsList = itemList.get(i);

        TextView itm_prodname = myViewHolder.productName;
        itm_prodname.setText(cartDetailsList.getProductName());
        TextView itm_category = myViewHolder.category;
        itm_category.setText(cartDetailsList.getCategory());
        TextView itm_quantity = myViewHolder.quantity;
        itm_quantity.setText("Quantity: "+Integer.toString(cartDetailsList.getQuantity()));
        TextView itm_unitprice = myViewHolder.unitprice;
        itm_unitprice.setText("Unit Cost:"+String.valueOf(dec.format(cartDetailsList.getUnitPrice())));
        TextView itm_tax=myViewHolder.tax;
        itm_tax.setText("Tax: "+String.valueOf(dec.format(cartDetailsList.getSubTotalWithTax())));
        TextView itm_subtotal = myViewHolder.subtotal;
        itm_subtotal.setText(String.valueOf(dec.format(cartDetailsList.getSubTotal())));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName, category, quantity, unitprice,tax, subtotal;

        public MyViewHolder(@NonNull View v) {
            super(v);
            productName = (TextView) itemView.findViewById(R.id.itm_product_name);
            category = (TextView) itemView.findViewById(R.id.itm_category);
            quantity = (TextView) itemView.findViewById(R.id.itm_quantity);
            unitprice = (TextView) itemView.findViewById(R.id.itm_unit_price);
            subtotal = (TextView) itemView.findViewById(R.id.itm_sub_total);
            tax=(TextView) itemView.findViewById(R.id.unit_tax);
        }
    }
}
