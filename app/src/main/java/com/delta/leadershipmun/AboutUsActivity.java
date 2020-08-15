package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AboutUsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView scroll_tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_entry_point);



    }

    public void viewStaff(View v){

        setContentView(R.layout.activity_about_us);
        scroll_tutorial = (ImageView)findViewById(R.id.scroll_tutorial);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        scroll_tutorial.startAnimation(fadeOut);
        scroll_tutorial.setVisibility(View.INVISIBLE);


    }

    public void viewCoreValues(View v){
        // Show core values document
    }
}
