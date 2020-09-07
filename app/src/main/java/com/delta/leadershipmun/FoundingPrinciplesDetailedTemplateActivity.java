package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class FoundingPrinciplesDetailedTemplateActivity extends AppCompatActivity {

    private TextView principle_title, principle_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_founding_principles_detailed_template);

        principle_title = (TextView)findViewById(R.id.principle_title);
        principle_content = (TextView)findViewById(R.id.principle_content);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        principle_title.setText(title);
        principle_content.setText(Html.fromHtml(content));
    }
}