package com.example.zahra.hce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;




/**
 * Created by Zahra on 27/09/2017.
 */

public class HTTPReq extends Activity {


    private static ArrayList<Activity> activities=new ArrayList<Activity>();
    Button b;
    EditText phonenumber;
    String phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
        setContentView(R.layout.sendreq);

        phonenumber=findViewById(R.id.e1);
        final SharedPreferences myPref = this.getSharedPreferences(phone,0);
        final String stringValue = myPref.getString("phone",phone);


       if(TextUtils.isEmpty(stringValue)) {

           b = findViewById(R.id.button2);
           b.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   //Instantiate new instance of our class
                   phone = phonenumber.getText().toString();
                   testrequest getRequest = new testrequest();

                   //Perform the doInBackground method, passing in our url
                   try {
                       String result;
                       String numPoundsMeat = phone;
                       String strMeatFormat = getResources().getString(R.string.url1);
                       String strMeatMsg = String.format(strMeatFormat, numPoundsMeat);
                       result = getRequest.execute(strMeatMsg).get();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } catch (ExecutionException e) {
                       e.printStackTrace();
                   }

                   SharedPreferences.Editor editor = myPref.edit();
                   editor.putString("phone", phone);
                   editor.commit();

                   Intent myIntent = new Intent(HTTPReq.this, HTTPCode.class);
                   myIntent.putExtra("phonenumber", phone);
                   startActivity(myIntent);
                   finish();
               }
           });
       }

       else{

           Intent myIntent = new Intent(HTTPReq.this, HTTPCode.class);
           myIntent.putExtra("phonenumber", stringValue);
           startActivity(myIntent);
           finish();
       }


}


    public static void finishAll()
    {
        for(Activity activity:activities)
            activity.finish();
    }

    public String httpGetRequest(Context context, String url,String datam)

    {
        String response = "";
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        try {
            Log.d("RequestManager", url );
            Log.e("data:", " " + datam);
            URL urlObj = new URL(url);

            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");

            conn.setDoOutput(true);
            conn.connect();


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(datam);
            wr.flush();
            Toast.makeText(this,"sent",Toast.LENGTH_SHORT).show();

            Log.d("post response code", conn.getResponseCode() + " ");

            int responseCode = conn.getResponseCode();
            Toast.makeText(this,responseCode,Toast.LENGTH_SHORT).show();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line=null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            response = sb.toString();
            if(response != null){

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("_____RRR", e.toString());
        } finally {
            try {
                reader.close();
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception ex) {
            }
        }
        Log.d("RESPONSE POST", response);
        return response;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        activities.remove(this);
    }

}

