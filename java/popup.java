package com.example.zahra.hce;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;



/**
 * Created by Zahra on 24/10/2017.
 */

public class popup extends Activity {
String i="okay";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);


    CharSequence choose[] = new CharSequence[] {"yes", "no"};
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Are You Sure?");
    builder.setItems(choose, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(builder.getContext().equals("yes")){
                i="okay";
                Log.d("khoda",i);
            }
        }
    });
    builder.show();


        final SharedPreferences my = this.getSharedPreferences(i,0);
        SharedPreferences.Editor editor = my.edit();
        editor.putString("i", i);
        editor.commit();

        finish();
        Intent intent2 = new Intent(this, MyHostApduSrevice.class);
        startService(intent2);
        finish();
    }
}
