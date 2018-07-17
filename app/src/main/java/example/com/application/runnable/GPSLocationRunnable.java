package example.com.application.runnable;

import android.content.Context;
import android.util.Log;

import example.com.application.gps.GPSManager;
import example.com.application.messagesmanager.MessagesManager;

/**
 * Created by Marcin on 16.07.2018.
 */

public class GPSLocationRunnable implements Runnable {

    private static final int A = 1000;
    private Thread backgroundThread;
    private MessagesManager messageManager;
    private Context context;

    public GPSLocationRunnable(MessagesManager messageManager, Context context){
        this.messageManager = messageManager;
        this.context = context;
    }

    public void start() {
        if (backgroundThread == null) {
            backgroundThread = new Thread(this);
            backgroundThread.start();
        }
    }

    public void stop() {
        if (backgroundThread != null) {
            backgroundThread.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (!backgroundThread.interrupted()) {
                Thread.sleep(A);
                String gpsLoc = GPSManager.getLocation(context);
                messageManager.addMessageAndNotify(gpsLoc);
                Log.d(GPSLocationRunnable.class.getName(), gpsLoc);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            backgroundThread = null;
        }
    }

}

