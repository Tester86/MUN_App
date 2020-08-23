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

    private static final int becomePartnerArg = 0;
    private static final int becomeAssociateArg = 1;

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView scroll_tutorial;
    private Button btnWorkWithUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collaborations_entry_point);


    }

    public void becomePartner(){
        Intent intent = new Intent(this, BecomeAPartnerActivity.class);
        startActivity(intent);
    }

    public void becomeAssociate(){
        Intent intent = new Intent(this, BecomeAnAssociateActivity.class);
        startActivity(intent);
    }

    public void viewPartnerships(View v){

            setViewPager("Become a partner",
                    becomePartnerArg,
                    R.drawable.bee_image,
                    R.drawable.cello_image,
                    R.drawable.lion_image,
                    R.drawable.pumpkin_image,
                    R.drawable.watch_image);
    }

    public void viewAssociations(View v){

        setViewPager("Become an associate",
                becomeAssociateArg,
                R.drawable.bee_image,
                R.drawable.cello_image,
                R.drawable.lion_image,
                R.drawable.pumpkin_image,
                R.drawable.watch_image);
    }



    private void setViewPager(String btnWOrkWithUsContent, int becomeOption, Integer... ID_list){

        int[] IDs = new int[ID_list.length];

        for(int i = 0; i < ID_list.length; i++){
            IDs[i] = ID_list[i];
        }

        setContentView(R.layout.activity_collaborations);

        btnWorkWithUs = (Button)findViewById(R.id.btnWorkWithUs);
        btnWorkWithUs.setText(btnWOrkWithUsContent);

        if(becomeOption == 0){
            btnWorkWithUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    becomePartner();
                }
            });
        } else{
            btnWorkWithUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    becomeAssociate();
                }
            });
        }

        scroll_tutorial = (ImageView)findViewById(R.id.scroll_tutorial);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        viewPagerAdapter = new ViewPagerAdapter(this, IDs);
        viewPager.setAdapter(viewPagerAdapter);

        Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        scroll_tutorial.startAnimation(fadeOut);
        scroll_tutorial.setVisibility(View.INVISIBLE);
    }
}
