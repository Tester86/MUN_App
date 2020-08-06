package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int tagCounter;
    private CardView unitedKingdom;
    private TextView item_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking visual to logic
        item_num = (TextView) findViewById(R.id.item_counter);
        unitedKingdom = (CardView) findViewById(R.id.unitedKingdom);

        // Setting values
        item_num.setText("10 items");

    }

}
