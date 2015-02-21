package com.bustinjailey.ihmw.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by bustinjailey on 2/19/15.
 */
public class SendTextSuccessReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Successfully hated on Matt", Toast.LENGTH_SHORT).show();
    }
}
