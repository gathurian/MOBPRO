package com.example.alex.ui_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ViewsDemoActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView ratingTextView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_demo);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingTextView = (TextView) findViewById(R.id.stars);
        imageView = (ImageView) findViewById(R.id.giffer);
        ratingTextView.setText("3.5");
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                updateRating();
            }
        });

        Glide.with(this).load(R.drawable.android).into(imageView);
    }

    public void updateRating(){
        float rating = ratingBar.getRating();
        String ratingString = Float.toString(rating);
        ratingTextView.setText(ratingString);
    }
}
