package com.example.alex.ui_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewsDemoActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView ratingTextView;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_demo);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingTextView = (TextView) findViewById(R.id.stars);
        webView = (WebView) findViewById(R.id.webView);

        String source = "<!DOCTYPE html><html><body><img src=\"https://media.giphy.com/media/QbumCX9HFFDQA/giphy.gif\" alt=\"Hackerman\" width=\"100%\" height=\"100%\"></body></html>";
        webView.loadData(source, "text/html", "utf-8");

    }

    public void updateRating(View view){
        float rating = ratingBar.getRating();
        String ratingString = Float.toString(rating);
        ratingTextView.setText(ratingString);
    }
}
