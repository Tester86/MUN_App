package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;


public class ShoppingActivity extends AppCompatActivity implements PaymentResultListener {

    private CardView hoodie, shoes, book, computer, baseball, phone, t_shirt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        hoodie = (CardView)findViewById(R.id.product_hoodie);
        shoes = (CardView)findViewById(R.id.product_shoes);
        book = (CardView)findViewById(R.id.product_book);
        computer = (CardView)findViewById(R.id.product_computer);
        baseball = (CardView)findViewById(R.id.product_baseball);
        phone = (CardView)findViewById(R.id.product_phone);
        t_shirt = (CardView)findViewById(R.id.product_t_shirt);


    }

    public void startPayment(View v) {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_pNWsybFXTJ1FTP");
        checkout.setImage(R.drawable.rzp_logo);

        final Activity activity = this;

        try{
            JSONObject options = new JSONObject();
            options.put("currency", "USD");

            switch (v.getId()){
                case R.id.product_hoodie:
                    options.put("name", "Hoodie");
                    options.put("description", "This is a good hoodie");
                    options.put("amount", "2000");
                    break;
                case R.id.product_shoes:
                    options.put("name", "Shoes");
                    options.put("description", "These are some good shoes");
                    options.put("amount", "1500");
                    break;
                case R.id.product_book:
                    options.put("name", "Book");
                    options.put("description", "This is a good book");
                    options.put("amount", "3000");
                    break;
                case R.id.product_computer:
                    options.put("name", "Computer");
                    options.put("description", "This is a good computer");
                    options.put("amount", "80000");
                    break;
                case R.id.product_baseball:
                    options.put("name", "Baseball");
                    options.put("description", "This is a good baseball");
                    options.put("amount", "500");
                    break;
                case R.id.product_phone:
                    options.put("name", "Phone");
                    options.put("description", "This is a good phone");
                    options.put("amount", "35000");
                    break;
                case R.id.product_t_shirt:
                    options.put("name", "T-Shirt");
                    options.put("description", "This is a good T-Shirt");
                    options.put("amount", "1500");
                    break;
            }
            checkout.open(activity, options);

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "FAILURE", Toast.LENGTH_LONG).show();
    }
}
