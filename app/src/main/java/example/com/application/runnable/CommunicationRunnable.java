package example.com.application.runnable;

import android.util.Log;

import java.util.List;

import example.com.application.messagedao.MessageDao;
import example.com.application.messagedao.impl.MessageDaoImpl;
import example.com.application.messagesmanager.MessagesManager;

/**
 * Created by Marcin on 16.07.2018.
 */

public class CommunicationRunnable implements Runnable {

    private Thread backgroundThread;
    private MessagesManager messageManager;
    private MessageDao messageDao;
    private static final int C = 5;
    private static String URL = "url";

    public CommunicationRunnable(MessagesManager messageManager) {
        this.messageManager = messageManager;
        this.messageDao = MessageDaoImpl.getInstance();
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
                synchronized (messageManager){
                    messageManager.wait();
                    List<String> msgs = messageManager.getTheOldestMessages();
                    Log.d(CommunicationRunnable.class.getName(), "msgs " + msgs);
                    messageDao.addMessages(msgs);

                    if(messageDao.getSize() > C){
                        String msgsInfo = messageDao.getMessages();
                        messageDao.clearMessages();

                        Log.d(CommunicationRunnable.class.getName(), "POST " + msgsInfo + " " + URL);
                    }
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            backgroundThread = null;
        }
    }

}
