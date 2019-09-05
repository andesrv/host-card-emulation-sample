package com.example.zahra.hce;

/**
 * Created by Zahra on 23/08/2017.
 */



import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyHostApduSrevice extends HostApduService{
    String  data;
    String test;

//    static final public String message="ok";


    public MyHostApduSrevice(){
        super();
    }

    @Override
    public byte[] processCommandApdu(byte[] apdu ,Bundle extras) {
        if (selectAidApdu(apdu)) {
            Log.i("HCEDEMO", "Application selected");
            Toast.makeText(this,"registered", Toast.LENGTH_SHORT).show();
            return getWelcomeMessage();
        }
        else {
            Log.i("HCEDEMO", "Received: " + new String(apdu));
            return getNextMessage(apdu);
        }
    }

    private byte[] getWelcomeMessage() {
        return data.getBytes();
    }

    private byte[] getNextMessage(byte[] dataIn) {
        test=new String(dataIn);
if(test.equals("are you sure?"))
    return "ok".getBytes();

else
return "khaaaak".getBytes();

    }



    private boolean selectAidApdu(byte[] apdu) {
        return apdu.length >= 2 && apdu[0] == (byte)0 && apdu[1] == (byte)0xa4;
    }



    @Override
    public void onDeactivated(int reason) {
        Log.i("HCEDEMO", "Deactivated: " + reason);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


    @Override
    public void onDestroy(){
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
          data=(String) intent.getExtras().get("phonenumber");
        return START_STICKY;
    }

}
