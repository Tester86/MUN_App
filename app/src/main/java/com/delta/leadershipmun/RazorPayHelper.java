package com.delta.leadershipmun;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class RazorPayHelper extends Activity implements PaymentResultListener {
    private String m_name, m_description, m_currency, m_amount;
    private String m_keyID;
    private Activity m_shopping_activity;
    private Checkout m_checkout;
    private int m_imageID;
    private JSONObject options;

    public RazorPayHelper(String name, String description, String currency, String amount, String keyID, int imageID){
        m_name = name;
        m_description = description;
        m_currency = currency;
        m_amount = String.valueOf(Integer.valueOf(amount) * 100);
        m_keyID = keyID;
        m_imageID = imageID;


        m_checkout = new Checkout();
        m_checkout.setKeyID(m_keyID);
        m_checkout.setImage(m_imageID);

    }

    public void startPayment(){
        try{
            options.put("name", m_name);
            options.put("description", m_description);
            options.put("currency", m_currency);
            options.put("amount", m_amount);

            m_checkout.open(new ShoppingActivity(), options);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(m_shopping_activity, "SUCCESS", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(m_shopping_activity, "FAILURE", Toast.LENGTH_LONG).show();
    }
}
