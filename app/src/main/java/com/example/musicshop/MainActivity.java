package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerarraylist;
    ArrayAdapter spinneradapter;
    HashMap goodsMap;
    String goodsname;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.editTextTextPersonName);

        spinner = findViewById(R.id.spinner4);
        spinner.setOnItemSelectedListener(this);
        spinnerarraylist = new ArrayList();

        spinnerarraylist.add("АК-74");
        spinnerarraylist.add("Молотов");
        spinnerarraylist.add("Перчатки");
        spinnerarraylist.add("LADA");


        spinneradapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerarraylist);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinneradapter);

        goodsMap = new HashMap();
        goodsMap.put("АК-74", 1300.0);
        goodsMap.put("Молотов", 1213.0);
        goodsMap.put("Перчатки", 1123.0);
        goodsMap.put("LADA", 1322.0);


    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView ValTextView = findViewById(R.id.ValTextView);
        ValTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    public void subtractionQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView ValTextView = findViewById(R.id.ValTextView);
        ValTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsname = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsname);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsimageView);

        switch (goodsname){
            case "АК-74":
                goodsImageView.setImageResource(R.drawable.f);
                break;
            case "Молотов":
                goodsImageView.setImageResource(R.drawable.km);
                break;
            case "Перчатки":
                goodsImageView.setImageResource(R.drawable.pr);
                break;
            case "LADA":
                goodsImageView.setImageResource(R.drawable.lada);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.f);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addToCarts(View view) {
        Order order = new Order();

        order.userName = userNameEditText.getText().toString();

        order.goodsName = goodsname;

        order.quantity = quantity;

        order.price = price;

        order.orderPrice = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity2.class);
        orderIntent.putExtra("userNameForIntent",order.userName);
        orderIntent.putExtra("goodsName",order.goodsName);
        orderIntent.putExtra("quantity",order.quantity);
        orderIntent.putExtra("price",order.price);
        orderIntent.putExtra("orderPrice",order.orderPrice);
        startActivity(orderIntent);
    }
}