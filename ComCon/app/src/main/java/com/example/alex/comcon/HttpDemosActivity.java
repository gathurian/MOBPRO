package com.example.alex.comcon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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
                InputStream stream = openHttpConnection("http://wherever.ch/hslu/homer.jpg");
                Bitmap bmp = BitmapFactory.decodeStream(stream);
                bitMap.setImageBitmap(bmp);
            }
        });

        loadDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream stream = openHttpConnection("http://wherever.ch/hslu/loremIpsum.txt");
                String data = convertStreamToString(stream);
                Log.d("DATA", data);
                //document.setText(data);
            }
        });
    }

    private InputStream openHttpConnection(String urlString){
        InputStream stream = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        try {
            URL url = new URL(urlString);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.connect();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }

    public static String convertStreamToString(InputStream is) {
        StringBuilder text= new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while((line = reader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
            return text.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
