package com.ford.esuresh8.shoopingcart;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartDetailsActivity extends Activity {
    Double total, subTotal = 0.00, tax = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);
        ArrayList<CartDetails> itemList = getIntent().getParcelableArrayListExtra("mylist");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CartAdapter cartAdapter = new CartAdapter(itemList);
        recyclerView.setAdapter(cartAdapter);

        TextView subTotalValue = (TextView) findViewById(R.id.sub_total);
        TextView totalwithtax = (TextView) findViewById(R.id.total_with_tax);
        TextView taxValue = (TextView) findViewById(R.id.total_tax);
        for (int i = 0; i < itemList.size(); i++) {
            tax += itemList.get(i).getSubTotalWithTax();
            subTotal += itemList.get(i).getSubTotal();
        }
        DecimalFormat dec = new DecimalFormat("#0.00");
        total = subTotal + tax;
        subTotalValue.setText("SubTotal: " + String.valueOf(dec.format(subTotal)));
        taxValue.setText("Tax: " + String.valueOf(dec.format(tax)));
        totalwithtax.setText("Total: " + String.valueOf(dec.format(total)));
    }
}
