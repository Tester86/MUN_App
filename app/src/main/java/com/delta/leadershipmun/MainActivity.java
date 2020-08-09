package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private CardView newsfeed, workshops, partnerships, joinUs, otherInitiatives, aboutUs, suggestions;
    private TextView item_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking visual to logic part
        newsfeed = (CardView) findViewById(R.id.newsfeed);
        workshops = (CardView) findViewById(R.id.workshops);
        partnerships = (CardView) findViewById(R.id.partnerships);
        joinUs = (CardView) findViewById(R.id.joinUs);
        otherInitiatives = (CardView) findViewById(R.id.otherInitiatives);
        aboutUs = (CardView) findViewById(R.id.aboutUs);
        suggestions = (CardView) findViewById(R.id.suggestions);

        item_num = (TextView) findViewById(R.id.item_num);

        // Setting values
        item_num.setText("10 items");


    }

    public void OnClickJoinUs(View v){
        Intent intent = new Intent(this, JoinUsActivity.class);
        startActivity(intent);
    }
}