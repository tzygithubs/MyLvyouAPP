package com.dlxy.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by T on 2017/8/18.
 */

public class Receiver extends BroadcastReceiver {
    private static final String TAG= "Receiver";
    public static String names =null;
    public static String url = null;
    @Override
    public void onReceive(Context context, Intent intent) {
       names = intent.getStringExtra("names");
        url = intent.getStringExtra("url");
        Log.i(TAG,"................Receiver"+names+url);
    }
}
