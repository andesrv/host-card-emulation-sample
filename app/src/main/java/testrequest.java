package com.example.zahra.hce;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zahra on 18/10/2017.
 */

public class testrequest extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD = "GET";
    @Override
    protected String doInBackground(String... params){
        String stringUrl = params[0];
        String result;
        String inputLine;

        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);

            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();

            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);


            //Connect to our url
            connection.connect();

            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());

            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }

            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();

            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        }
        catch(IOException e){
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }


}
//    public String httpGetRequest(Context context, String url, String datam)
//
//    {
//        String response = "";
//        BufferedReader reader = null;
//        HttpURLConnection conn = null;
//        try {
//            Log.d("RequestManager", url);
//            Log.e("data:", " " + datam);
//            URL urlObj = new URL(url);
//
//            conn = (HttpURLConnection) urlObj.openConnection();
//            conn.setRequestMethod("GET");
//
//            conn.setDoOutput(true);
//            conn.connect();
//
//
//            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//            wr.write(datam);
//            wr.flush();
////            Toast.makeText(this, "sent", Toast.LENGTH_SHORT).show();
//
//            Log.d("post response code", conn.getResponseCode() + " ");
//
//            int responseCode = conn.getResponseCode();
////            Toast.makeText(this, responseCode, Toast.LENGTH_SHORT).show();
//            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//
//            response = sb.toString();
//            if (response != null) {
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.d("_____RRR", e.toString());
//        } finally {
//            try {
//                reader.close();
//                if (conn != null) {
//                    conn.disconnect();
//                }
//            } catch (Exception ex) {
//            }
//        }
//        Log.d("RESPONSE POST", response);
//        return response;
//    }