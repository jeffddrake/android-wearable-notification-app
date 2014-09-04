package com.example.jeffdrake.firstbuildwearablehack;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.content.Intent;
import android.app.PendingIntent;
import android.R.drawable;
import android.R.string;
import android.widget.Button;
import android.R.id;
import android.support.v4.app.RemoteInput;
import android.app.Notification;
import android.util.Log;



public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    public void buttonPressed(View view) {

        String replyLabel = getResources().getString(R.string.app_name);

        RemoteInput remoteInput = new RemoteInput.Builder(getResources().getString(R.string.EXTRA_VOICE_REPLY))
                .setLabel(replyLabel)
                .build();

        //JDD - create a new intent
        Intent replyIntent = new Intent(this, HandleButtonPressActivity.class);


        int notificationId = 001;

        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, replyIntent, 0);


        // Create the reply action and add the remote input
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher_1b, getString(R.string.app_name), viewPendingIntent).addRemoteInput(remoteInput).build();


        Notification notification =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_launcher_1b)
                        .setContentTitle("Your Dryer Is Summoning You")
                        .setContentText("Your Laundry Is Done, Stop Drinking Beer and Get It.")
                        .extend(new WearableExtender().addAction(action))
                        .build();

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(getApplicationContext());

        // Build the notification and issues it with notification manager.
        //notificationManager.notify(notificationId, notificationBuilder.build());
        notificationManager.notify(notificationId, notification);

        //startActivity(intent);

    }
}
