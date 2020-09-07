package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    private final static String ourVision = "Synergy Network International aspires to be the <b>consolidation</b> of global youth " +
            "<b>leadership</b> and <b>potential</b>.<br><br>Our vision is to be a center for the empowerment of youth voices " +
            "through a network of young leaders, each supporting one another in one common bond: to " +
            "change the world.<br><br>This change will come through every resource, piece of advice, and initiative " +
            "shared by our members as priceless passion is exchanged and strengthened throughout our " +
            "platform for impact. We are the youth <b>foundation</b> for <b>change</b>, <b>progress</b>, and <b>success.</b>";

    private final static String ourMission = "To <b>unite</b> and <b>amplify</b> youth voices<br><br>" +
            "Synergy Network International seeks to amplify youth voices " +
            "through a network of young leaders, each supporting one another in one common bond: to " +
            "change the world. This change will come through every resource, piece of advice, and initiative " +
            "shared by our members as priceless passion is exchanged and strengthened throughout our " +
            "platform for impact. We are the youth foundation for change, progress, and success";

    private static final String ourOrigins = "After observing how much connections with youth leaders from around the world " +
            "enhance one’s own commitments and having been enriched by such relationships " +
            "themselves, three friends decided to partner together on a journey to consolidate an official " +
            "network of youth leadership." +
            "<br><br>These three partners were connected in their common bond of " +
            "leadership, initiative, and a drive for change despite the thousands of kilometers between " +
            "them, and they benefited greatly from it as one strengthened and enriched the projects of another." +
            "<br><br>They realized that if even such a remote connection as their own warranted " +
            "success, an official network committed to bringing leaders together like they were but would " +
            "be all the better on a much larger scale. It would be a platform for change, a platform " +
            "founded on youth leadership and the knowledge, passion, and adaptability of those leaders, " +
            "a network for <b>amplifying ambition</b> and <b>unifying potential</b> to impact." +
            "<br><br>It would be <i>Synergy Network International</i>.";

    private static final String benefitsToMembers = "<br>As a member of SNI, you are a part of a moving force of youth leadership. You have " +
            "a body of the world’s youngest and most incredible change-makers standing with you, " +
            "supporting you with their own initiatives, and sharing their immeasurable advice, " +
            "experience, and knowledge with you. They will develop your skills, fostering well-founded " +
            "passion, and lighting a fire of triumph within you." +
            "<br><br>You are a part of a network of ambition " +
            "and progress, a movement that will give you a platform to raise and strengthen your voice." +
            "<br><br>Together, you will <b>impact</b>; together, you will <b>share success</b>; and together, you will <b>change the world</b>.";

    private static final String ourMessageToTheWorld = "<br><b>Together we are stronger</b>." +
            "<br><br>Just as doctors come together and support one another to " +
            "help save lives, we– the youth of the world– will unite to protect ourselves." +
            "<br><br>We are one, standing with each other as we develop initiatives working against all the odds, defying " +
            "expectations, and surpassing the barriers set up because of our ages." +
            "<br><br>Together we can transform ambition into reality and thoughts into change." +
            "<br><br>Our individual vitality, passion, and " +
            "potential can and will change the world on their own; however, <b>we shape the world when united</b>.";

    private CardView ourVisionCardview, ourMissionCardview, ourOriginsCardview, benefitsToMembersCardview, ourMessageToTheWorldCardview;
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

        int[] images = {R.drawable.bee_image,
                R.drawable.cello_image,
                R.drawable.lion_image,
                R.drawable.pumpkin_image,
                R.drawable.watch_image};

        viewPagerAdapter = new ViewPagerAdapter(this, images);

        viewPager.setAdapter(viewPagerAdapter);

        Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        scroll_tutorial.startAnimation(fadeOut);
        scroll_tutorial.setVisibility(View.INVISIBLE);


    }

    public void viewFoundingPrinciples(View v){
        // Entering founding principles screen
        setContentView(R.layout.founding_principles);

        ourVisionCardview = (CardView)findViewById(R.id.ourVisionCardview);
        ourMissionCardview = (CardView)findViewById(R.id.ourMissionCardview);
        ourOriginsCardview = (CardView)findViewById(R.id.ourOriginsCardview);
        benefitsToMembersCardview = (CardView)findViewById(R.id.benefitsToMembersCardview);
        ourMessageToTheWorldCardview = (CardView)findViewById(R.id.ourMessageToTheWorldCardview);

        ourVisionCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails("1. Our Vision", ourVision);
            }
        });

        ourMissionCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails("2. Our Mission", ourMission);
            }
        });

        ourOriginsCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails("3. Our Origins", ourOrigins);
            }
        });

        benefitsToMembersCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails("4. Benefits to Members", benefitsToMembers);
            }
        });

        ourMessageToTheWorldCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails("5. Our Message to the World", ourMessageToTheWorld); // 90
            }
        });
    }

    private void showDetails(String title, String content){
        Intent intent = new Intent(getApplicationContext(), FoundingPrinciplesDetailedTemplateActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        startActivity(intent);
    }
}
