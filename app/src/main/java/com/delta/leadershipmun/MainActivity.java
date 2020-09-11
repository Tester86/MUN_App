package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.razorpay.Checkout;


public class MainActivity extends AppCompatActivity {

    private CardView newsfeed, workshops, partnerships, joinUs, ourProducts, aboutUs, suggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Checkout c = new Checkout();
        c.setKeyID("rzp_test_pNWsybFXTJ1FTP");
        Checkout.preload(getApplicationContext());

        // Linking visual to logic part
        newsfeed = (CardView) findViewById(R.id.newsfeed);
        workshops = (CardView) findViewById(R.id.workshops);
        partnerships = (CardView) findViewById(R.id.partnerships);
        joinUs = (CardView) findViewById(R.id.joinUs);
        ourProducts = (CardView) findViewById(R.id.ourProducts);
        aboutUs = (CardView) findViewById(R.id.aboutUs);
        suggestions = (CardView) findViewById(R.id.suggestions);



    }

    public void OnClickJoinUs(View v) {
        Intent intent = new Intent(this, JoinUsActivity.class);
        startActivity(intent);
    }

    public void OnClickSuggestionBox(View v) {
        Intent intent = new Intent(this, SuggestionBoxActivity.class);
        startActivity(intent);
    }

    public void OnClickAboutUs(View v){
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void OnClickCollaborations(View v){
        Intent intent = new Intent(this, CollaborationsActivity.class);
        startActivity(intent);
    }

    public void OnClickShopping(View v){
        Intent intent = new Intent(this, ShoppingActivity.class);
        startActivity(intent);
    }

    public void OnClickNewsfeed(View v){
        Intent intent = new Intent(this, NewsfeedActivity.class);
        startActivity(intent);
    }
}