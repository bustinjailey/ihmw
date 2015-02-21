package com.bustinjailey.ihmw.receivers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by bustinjailey on 2/19/15.
 */
public class SendTextReceiver extends BroadcastReceiver {
    public static final String MATT_PHONE_NUMBER = "9149248420";
    public static final String FUNNY_MESSAGE_TO_SEND = "I love you!!!!";

    private PendingIntent pendingSuccessIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        int numberOfTexts = intent.getIntExtra("numberOfTexts", 0);

        Toast.makeText(context, String.format("Sending %d  text(s)", numberOfTexts), Toast.LENGTH_SHORT).show();

        final Intent successIntent = new Intent(context, SendTextSuccessReceiver.class);
        pendingSuccessIntent = PendingIntent.getBroadcast(context, 0, successIntent, 0);

        sendSmsMultipleTimes(context, MATT_PHONE_NUMBER, FUNNY_MESSAGE_TO_SEND, numberOfTexts);
    }

    private void sendSmsMultipleTimes(Context context, String phoneNumber, String message, int numberOfTexts) {

        if ((phoneNumber.length() > 0) && (message.length() > 0))
            for (int j = 0; ; j++) {
                if (j >= numberOfTexts) {
                    return;
                }

                sendSms(phoneNumber, message);
            }
        Toast.makeText(context, "Please enter both phone number and message.", Toast.LENGTH_LONG).show();
    }

    private void sendSms(String phoneNumber, String message) {
        try {
            pendingSuccessIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, pendingSuccessIntent, null);
    }
}
