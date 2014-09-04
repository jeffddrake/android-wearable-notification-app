package com.example.jeffdrake.firstbuildwearablehack;

import android.app.Activity;
import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class HandleButtonPressActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_button_press);
        //JDD - just put some default text before we capture the voice text
        TextView TempText = (TextView)findViewById(R.id.notification_return_header_id_102);
        TempText.setText("... wait for it ...");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.handle_button_press, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void receiveVoiceInputButton(View view) {
        setContentView(R.layout.activity_handle_button_press);

        CharSequence DisplayText = getMessageText(this.getIntent());
        TextView DynamicDisplay = (TextView)findViewById(R.id.notification_return_header_id_102);
        if( DisplayText != null)
        {
            DynamicDisplay.setText(DisplayText);
        }
        else
        {
            DynamicDisplay.setText("I'm sorry, what did you say?");
        }


    }

    //JDD - this is the function where we receive our voice input data via strings
    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = null;
        remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput != null) {
            return remoteInput.getCharSequence(getResources().getString(R.string.EXTRA_VOICE_REPLY));
        }
        return null;
    }
}
