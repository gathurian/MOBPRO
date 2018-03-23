package com.example.alex.comcon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDemosActivity extends AppCompatActivity {

    Button loadBinary;
    Button loadDocument;
    Bitmap bmp;
    ImageView bitMap;
    TextView document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_demos);

        loadBinary = (Button) findViewById(R.id.loadBinary);
        loadDocument = (Button) findViewById(R.id.loadDocument);
        bitMap = (ImageView) findViewById(R.id.bitMap);
        document = (TextView) findViewById(R.id.document);

        loadBinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    URL imageURL = new URL("http://wherever.ch/hslu/homer.jpg");

                    HttpURLConnection httpConnection = (HttpURLConnection) imageURL.openConnection();
                    httpConnection.setInstanceFollowRedirects(true);
                    httpConnection.connect();
                    if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream content = httpConnection.getInputStream();
                        bmp = BitmapFactory.decodeStream(content);
                        bitMap.setImageBitmap(bmp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        loadDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    URL documentURL = new URL("http://wherever.ch/hslu/loremIpsum.txt");
                    HttpURLConnection httpConnection = (HttpURLConnection) documentURL.openConnection();
                    httpConnection.setInstanceFollowRedirects(true);
                    httpConnection.connect();
                    if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream content = httpConnection.getInputStream();
                        String data = convertStreamToString(content);
                        document.setText(data);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
