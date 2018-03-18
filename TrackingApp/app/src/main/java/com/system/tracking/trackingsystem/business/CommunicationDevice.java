package com.system.tracking.trackingsystem.business;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Milton on 18/03/2018.
 */

public class CommunicationDevice {

    private static final String URL_SERVER = "http://192.168.43.186/";
    private static final String SERVICE_SIGNAL = URL_SERVER + "SAFE/";
    private static final String SERVICE_CONFIRMATION = URL_SERVER + "CONFIRM/";
    private static final String SERVICE_ACTIVE_ASSAULT = URL_SERVER + "ACTIVE_ASSAULT/";

//    private Integer asyncTaskReturn;

    public Integer getSignal() {
        URL url = null;
        int result = 0;
        try {
            url = new URL(SERVICE_SIGNAL);
//            new LoadHtml().execute(url);
//            while (asyncTaskReturn == null) {}
//            int response = asyncTaskReturn;
//            asyncTaskReturn = null;
//            return response;
            String str = loadHtml(url);
            result = Integer.parseInt(str.substring(0,str.length()-2));
            result *= -1;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Integer getConfirmation() {
        URL url = null;
        try {
            url = new URL(SERVICE_CONFIRMATION);
//            new LoadHtml().execute(url);
            loadHtml(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer getActiveAssault() {
        URL url = null;
        try {
            url = new URL(SERVICE_ACTIVE_ASSAULT);
//            new LoadHtml().execute(url);
            loadHtml(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }


//    private class LoadHtml extends AsyncTask<URL, Void, String> {

    //        @Override
//        protected String doInBackground(URL... urls) {
    private String loadHtml(URL... urls) {
        String result = "";
        try {
            HttpURLConnection connection = null;
            InputStream stream = null;
            //String result = null;
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            stream = connection.getInputStream();
            result = readStream(stream, 500);
            Log.i("MainActivity", result);
            //return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

        public String readStream(InputStream stream, int maxReadSize)
                throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] rawBuffer = new char[maxReadSize];
            int readSize;
            StringBuffer buffer = new StringBuffer();
            while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
                if (readSize > maxReadSize) {
                    readSize = maxReadSize;
                }
                buffer.append(rawBuffer, 0, readSize);
                maxReadSize -= readSize;
            }
            return buffer.toString();
        }

//        @Override
//        protected void onPostExecute(String value) {
//            super.onPostExecute(value);
//            asyncTaskReturn = /*Integer.parseInt(value);*/Integer.getInteger(value);
//        }
    //}
}
