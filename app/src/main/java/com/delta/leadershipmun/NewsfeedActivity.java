package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.helpers.ParserAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsfeedActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private ProgressBar newsProgressBar;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        newsRecyclerView = (RecyclerView)findViewById(R.id.newsRecyclerView);
        newsProgressBar = (ProgressBar)findViewById(R.id.newsProgressBar);
        recyclerViewAdapter = new RecyclerViewAdapter(parseItems, this);

        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(recyclerViewAdapter);

        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {


            String url = "https://www.nytimes.com/es/";
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                Elements data = doc.select("ol.css-11jjg.ekkqrpp2");

                for(int i = 0; i < data.size(); i++){

                    String title = data.select("h2.css-l2vidh.e4e4i5l1")
                            .select("a")
                            .eq(i)
                            .text();

                    if(!title.equals("")) parseItems.add(new ParseItem(title));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsProgressBar.setVisibility(View.VISIBLE);
            newsProgressBar.startAnimation(AnimationUtils.loadAnimation(NewsfeedActivity.this, R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            newsProgressBar.setVisibility(View.GONE);
            newsProgressBar.startAnimation(AnimationUtils.loadAnimation(NewsfeedActivity.this, R.anim.fade_out));
            recyclerViewAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}