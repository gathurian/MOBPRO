package com.example.alex.comcon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpDemosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_demos);
    }

    String urlStringtext = "http://wherever.ch/hslu/loremIpsum.txt";
    String urlStringpicture = "http://wherever.ch/hslu/homer.jpg";

    public void loadBinaryDataPicture(View view) {
        DownloadImageTask downloadImageTask = new DownloadImageTask((ImageView) findViewById(R.id.bitMap));
        downloadImageTask.execute(urlStringpicture);
    }

    public void loadBinaryDataText(View view) {
        DownloadTextTask downloadTextTask = new DownloadTextTask((TextView) findViewById(R.id.document));
        downloadTextTask.execute(urlStringtext);
    }

}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bitMap;

    DownloadImageTask(ImageView bmImage) {
        this.bitMap = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bitMap.setImageBitmap(result);
    }
}

class DownloadTextTask extends AsyncTask<String, Void, String> {
    TextView textFromURL;

    public DownloadTextTask(TextView textImage) {
        this.textFromURL = textImage;
    }

    protected String doInBackground(String... urls) {
        StringBuilder text = new StringBuilder();
        try {
            InputStream in = new java.net.URL(urls[0]).openStream();
            String loadedText;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while((loadedText = reader.readLine()) != null) {
                text.append(loadedText);
                text.append("\n");
            }
            reader.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return text.toString();
    }

    protected void onPostExecute(String result) {
        textFromURL.setText(result);
    }
}




