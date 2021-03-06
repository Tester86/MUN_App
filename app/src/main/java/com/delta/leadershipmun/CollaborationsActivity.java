package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class CollaborationsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView scroll_tutorial;
    private Button btnWorkWithUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collaborations);

        setViewPager("Partner with us",
                R.drawable.bee_image,
                R.drawable.cello_image,
                R.drawable.lion_image,
                R.drawable.pumpkin_image,
                R.drawable.watch_image);

    }


    private void setViewPager(String btnWOrkWithUsContent, Integer... ID_list){

        int[] IDs = new int[ID_list.length];

        for(int i = 0; i < ID_list.length; i++){
            IDs[i] = ID_list[i];
        }

        btnWorkWithUs = (Button)findViewById(R.id.btnWorkWithUs);
        btnWorkWithUs.setText(btnWOrkWithUsContent);

        btnWorkWithUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BecomeAnAssociateActivity.class);
                startActivity(intent);
            }
        });

        scroll_tutorial = (ImageView)findViewById(R.id.scroll_tutorial);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        viewPagerAdapter = new ViewPagerAdapter(this, IDs);
        viewPager.setAdapter(viewPagerAdapter);

        Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        scroll_tutorial.startAnimation(fadeOut);
        scroll_tutorial.setVisibility(View.INVISIBLE);
    }
}
