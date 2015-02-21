package com.bustinjailey.ihmw;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bustinjailey.ihmw.receivers.SendTextReceiver;


public class HaterMain extends Activity {
    private PendingIntent pendingSendTextIntent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hater_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hater_main);

        // Set up PendingIntent for sending texts
        final Intent sendTextIntent = new Intent(this, SendTextReceiver.class);


        ImageButton doTheHates = ((ImageButton) findViewById(R.id.doHating));
        doTheHates.setBackground(getDrawable(R.drawable.saturn_v));
        doTheHates.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                String editTextValue = ((EditText) findViewById(R.id.numberOfHates)).getText().toString();

                Integer numberOfTexts;
                if (isNumeric(editTextValue)) {
                    numberOfTexts = Integer.parseInt(editTextValue);
                } else {
                    numberOfTexts = 1;
                }

                try {
                    sendTextIntent.putExtra("numberOfTexts", numberOfTexts);
                    pendingSendTextIntent = PendingIntent.getBroadcast(HaterMain.this, 0, sendTextIntent, 0);
                    pendingSendTextIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isNumeric(String str) {

        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hater_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
