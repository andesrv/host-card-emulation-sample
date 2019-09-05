package com.example.zahra.hce;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.zahra.hce.IsoDepTransceiver.OnMessageReceived;

import java.util.ArrayList;


public class MainActivity extends Activity implements OnMessageReceived {
//        , ReaderCallback{
private static ArrayList<Activity> activities=new ArrayList<Activity>();

    private NfcAdapter nfcAdapter;
    private ListView listView;
    private IsoDepAdapter isoDepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
        setContentView(R.layout.activity_main);
//        listView =findViewById(R.id.listView);
//        listView.setAdapter(isoDepAdapter);
        isoDepAdapter = new IsoDepAdapter(getLayoutInflater());
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        Button push = findViewById(R.id.b1);
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
                if (!previouslyStarted) {
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
                    edit.commit();
                    Intent i = new Intent(MainActivity.this, HTTPReq.class);
                    startActivity(i);


                }
else {
                    Intent z = new Intent(MainActivity.this, HTTPReq.class);
                    startActivity(z);
                }
            }

        });

    }

    public static void finishAll()
    {
        for(Activity activity:activities)
            activity.finish();
    }


    @Override
    public void onResume() {
        super.onResume();
//        nfcAdapter.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
//                null);
    }

    @Override
    public void onPause() {
        super.onPause();
//        nfcAdapter.disableReaderMode(this);
    }
//
//    @Override
//    public void onTagDiscovered(Tag tag) {
////        IsoDep isoDep = IsoDep.get(tag);
////        IsoDepTransceiver transceiver = new IsoDepTransceiver(isoDep, this);
////        Thread thread = new Thread(transceiver);
////        thread.start();
//    }

    @Override
    public void onMessage(final byte[] message) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                isoDepAdapter.addMessage(new String(message));
//            }
//        });
    }

    @Override
    public void onError(Exception exception) {
        onMessage(exception.getMessage().getBytes());

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        activities.remove(this);
    }
}
