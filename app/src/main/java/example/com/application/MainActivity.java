package example.com.application;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import example.com.application.messagesmanager.impl.MessagesManagerImpl;
import example.com.application.runnable.BatteryRunnable;
import example.com.application.runnable.CommunicationRunnable;
import example.com.application.runnable.GPSLocationRunnable;

public class MainActivity extends AppCompatActivity {

    GPSLocationRunnable gpsRunnable;
    BatteryRunnable batteryR;
    CommunicationRunnable communicationRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);

        MessagesManagerImpl messageManager = new MessagesManagerImpl();

        gpsRunnable = new GPSLocationRunnable(messageManager, getApplicationContext());
        batteryR = new BatteryRunnable(messageManager, this.getApplicationContext());
        communicationRunnable = new CommunicationRunnable(messageManager);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });

        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                1);
    }

    private void start() {
        gpsRunnable.start();
        batteryR.start();
        communicationRunnable.start();
    }

    private void stop() {
        gpsRunnable.stop();
        batteryR.stop();
        communicationRunnable.stop();
    }

}
