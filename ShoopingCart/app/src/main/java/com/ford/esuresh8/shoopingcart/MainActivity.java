package com.ford.esuresh8.shoopingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public ArrayList<CartDetails> cartdetails = new ArrayList();
    TextView price;
    EditText productName;
    EditText quantity;
    Spinner spinner;
    RadioGroup importFlag;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price = (TextView) findViewById(R.id.item_price);
        productName = (EditText) findViewById(R.id.prod_name);
        quantity = (EditText) findViewById(R.id.item_quantity);
        spinner = (Spinner) findViewById(R.id.category_spinner);
        importFlag = (RadioGroup) findViewById(R.id.importedFlag);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.category_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        List<String> cat_price = Arrays.asList(getResources().getStringArray(R.array.category_price));
        price.setText(cat_price.get(parent.getSelectedItemPosition()).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void ResetFields() {
        productName.setText("");
        quantity.setText("");
        spinner.setSelection(0);
    }

    public void onAddCartClicked(View view) {
        int selectedId = importFlag.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(selectedId);

        if (productName.getText().toString().equals("") || spinner.getSelectedItem().toString().equals("Select") || quantity.getText().toString().equals("") || rb.getText().toString().equals("")) {
            Toast.makeText(this, "Input Shouldn't be Empty", Toast.LENGTH_LONG).show();
        } else {
            CartDetails cd = new CartDetails();
            cd.setProductName(productName.getText().toString());
            cd.setCategory(spinner.getSelectedItem().toString());
            cd.setQuantity(Integer.parseInt(quantity.getText().toString()));
            cd.setImportFlag(rb.getText().toString());
            cd.setUnitPrice(Double.parseDouble(price.getText().toString()));
            cartdetails.add(cd);
            ResetFields();

            Button button = (Button) findViewById(R.id.add_cart);
            button.setText(cartdetails.size() + " on Cart");
            Toast.makeText(this, cartdetails.size() + " Items Added", Toast.LENGTH_LONG).show();
        }
    }

    public void onBuyClicked(View view) {
        if (cartdetails.size() == 0) {
            Toast.makeText(this, "No Items in Cart to Buy, Please Add Items", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, CartDetailsActivity.class);
            intent.putParcelableArrayListExtra("mylist", cartdetails);
            this.startActivity(intent);
        }
    }
}
