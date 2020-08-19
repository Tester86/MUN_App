package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;


public class ShoppingActivity extends AppCompatActivity implements PaymentResultListener {

    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        btnPay = (Button) findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

    }

    public void startPayment(){

        RazorPayHelper helper = new RazorPayHelper("Test Sale",
                "Some random sale",
                "USD",
                "20", "", 1);

        helper.startPayment();
        /*Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_pNWsybFXTJ1FTP");
        checkout.setImage(R.drawable.rzp_logo);

        final Activity activity = this;

        try{
            JSONObject options = new JSONObject();
            options.put("name", "Tea Factory");
            options.put("description", "Testing Sell");
            options.put("currency", "USD");
            options.put("amount", "50000");
            checkout.open(activity, options);

        } catch(Exception e){
            e.printStackTrace();
        }*/

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
