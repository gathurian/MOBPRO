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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class HttpDemosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_demos);
    }

    String urlStringtext = "http://wherever.ch/hslu/loremIpsum.txt";
    String urlStringpicture = "http://wherever.ch/hslu/homer.jpg";
    String urlStringJSON = "http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HTTP";
    String urlStringXML = "http://services.aonaware.com/DictService/DictService.asmx/Define?word=android";

    public void loadBinaryDataPicture(View view) {
        DownloadImageTask downloadImageTask = new DownloadImageTask((ImageView) findViewById(R.id.bitMap));
        downloadImageTask.execute(urlStringpicture);
    }

    public void loadBinaryDataText(View view) {
        DownloadTextTask downloadTextTask = new DownloadTextTask((TextView) findViewById(R.id.document));
        downloadTextTask.execute(urlStringtext);
    }

    public void loadJSON(View view) {
        DownloadJSONTask downloadJSONTask = new DownloadJSONTask((TextView) findViewById(R.id.json));
        downloadJSONTask.execute(urlStringJSON);
    }

    public void loadXML(View view) {
        DownloadXMLTask downloadXMLTask = new DownloadXMLTask((TextView) findViewById(R.id.xml));
        downloadXMLTask.execute(urlStringXML);
    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
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
        bmImage.setImageBitmap(result);
    }
}

class DownloadTextTask extends AsyncTask<String, Void, String> {
    TextView textFromURL;

    DownloadTextTask(TextView textImage) {
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

class DownloadXMLTask extends AsyncTask<String, Void, String> {
    TextView textFromURL;

    DownloadXMLTask(TextView textImage) {
        this.textFromURL = textImage;
    }

    protected String doInBackground(String... urls) {
        String text = "";
        try {
            InputStream in = new java.net.URL(urls[0]).openStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDocument = builder.parse(in);
            xmlDocument.getDocumentElement().normalize();

            Node firstDefinition = xmlDocument.getElementsByTagName("Definition").item(0);
            Element definition = (Element) firstDefinition;
            Node dictName = definition.getElementsByTagName("Name").item(0);
            Node wordDef = definition.getElementsByTagName("WordDefinition").item(0);
            text = "android: " + wordDef.getTextContent() + " [" + dictName.getTextContent() + "]";

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    private String readText(InputStream in){
        StringBuilder text = new StringBuilder();
        try {
            String loadedText;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((loadedText = reader.readLine()) != null) {
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

class DownloadJSONTask extends AsyncTask<String, Void, String> {
    TextView textFromURL;

    DownloadJSONTask(TextView textImage) {
        this.textFromURL = textImage;
    }


    protected String doInBackground(String... urls) {
        String text = "";
        try {
            InputStream in = new java.net.URL(urls[0]).openStream();
            String json = readText(in);
            JSONArray array = new JSONArray(json);
            JSONObject firstObject = array.getJSONObject(0);
            JSONObject firstDef = firstObject.getJSONArray("lfs").getJSONObject(0);
            text = "HTTP: " + firstDef.getString("lf") + " (since " + firstDef.getInt("since") + ")";
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return text;
    }

    private String readText(InputStream in){
        StringBuilder text = new StringBuilder();
        try {
            String loadedText;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((loadedText = reader.readLine()) != null) {
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





