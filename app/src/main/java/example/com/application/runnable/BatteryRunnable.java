package example.com.application.runnable;

import android.content.Context;
import android.util.Log;

import example.com.application.battery.BatteryStateManager;
import example.com.application.messagesmanager.MessagesManager;

/**
 * Created by Marcin on 16.07.2018.
 */

public class BatteryRunnable implements Runnable {

    private static final int B = 1000;
    private Thread backgroundThread;
    private MessagesManager messageManager;
    private Context context;

    public BatteryRunnable(MessagesManager messageManager, Context context){
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

    public void run() {
        try {
            while (!backgroundThread.interrupted()) {
                Thread.sleep(B);
                String batteryUsage = BatteryStateManager.getBatteryPercentage(context);
                messageManager.addMessageAndNotify(batteryUsage);
                Log.d(BatteryRunnable.class.getName(), batteryUsage);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            backgroundThread = null;
        }
    }
}
