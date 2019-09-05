package com.example.zahra.hce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Zahra on 28/09/2017.
 */

public class HTTPCode extends Activity {
    Button b2;
    EditText codenumber;
    EditText phonenumber;
    String code;
    private static ArrayList<Activity> activities=new ArrayList<Activity>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code);
        codenumber=findViewById(R.id.enter);
        phonenumber=findViewById(R.id.phonenumber);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String outlet_no= bundle.getString("phonenumber");
        phonenumber.setText(outlet_no);

        phonenumber=findViewById(R.id.e1);
        final SharedPreferences myPref = this.getSharedPreferences(code,0);
        final String stringValue = myPref.getString("code",code);


        if(TextUtils.isEmpty(stringValue)) {
        b2=findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testrequest getRequest = new testrequest();

                code=codenumber.getText().toString();


                //Perform the doInBackground method, passing in our url
                try {
                    String result;
                    String numPoundsMeat =outlet_no ;
                    String and="&";
                    String Space=" ";
                    String codee=code;
                    String ch = getResources().getString(R.string.check);
                    String check = String.format(ch,codee,Space,numPoundsMeat,Space);
                    String strMeatFormat = getResources().getString(R.string.url2);
                    String strMeatMsg = String.format(strMeatFormat,numPoundsMeat,and,codee);
                    result = getRequest.execute(strMeatMsg).get();
                    Log.d("Result",result);
                    if(result.equals(check)){
                        SharedPreferences.Editor editor = myPref.edit();
                        editor.putString("code", code);
                        editor.commit();
                        Intent myIntent = new Intent(HTTPCode.this, main.class);
                        startActivity(myIntent);
                        finish();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }




            }
        });}
        else {
            Intent myIntent = new Intent(HTTPCode.this, main.class);
            startActivity(myIntent);
            finish();
        }


}
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        activities.remove(this);
    }}