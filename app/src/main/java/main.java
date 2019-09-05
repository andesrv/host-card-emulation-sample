package com.example.zahra.hce;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Zahra on 18/10/2017.
 */

public class main extends Activity  {
    TextView f;
    String outlet_no;
    String i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        f = findViewById(R.id.w);




        final SharedPreferences myPref = this.getSharedPreferences(outlet_no, 0);
        final String stringValue = myPref.getString("phone", outlet_no);
        f.setText(stringValue);


//
//
//        BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String s = intent.getStringExtra(MyHostApduSrevice.message);
//                if (s.equals("ok")) {
//                    CharSequence choose[] = new CharSequence[]{"yes", "no"};
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(main.this);
//                    builder.setTitle("Are You Sure?");
//                    builder.setItems(choose, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            if (builder.getContext().equals("yes")) {
//                                i = "okay";
//                                Log.d("khoda", i);
//                            }
//                        }
//                    });
//                    builder.show();
//                }
//            }
//        };



        Intent intent = new Intent(this, MyHostApduSrevice.class);
        intent.putExtra("phonenumber", stringValue);
       startService(intent);
        Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();

    }

}
