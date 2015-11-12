package com.trending.foursquare;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceHandler extends AsyncTask<String,Integer,String>{
    String temp;

    public String Upper(String url2) {
        try {
            URL urlob = new URL(url2);
            HttpURLConnection urlConnection = (HttpURLConnection) urlob.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setDoInput(true);
            urlConnection.connect();

            String readStream = readStream(urlConnection.getInputStream());

            return readStream;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String readStream(InputStream is) {

        StringBuilder sb;
        sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return sb.toString();
    }

    @Override
    protected String doInBackground(String... url) {

        temp = Upper(url[0]);
        Log.d("checktemp",temp);
        return temp;


    }
}

