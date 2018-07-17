package example.com.application.messagesmanager.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import example.com.application.messagesmanager.MessagesManager;

/**
 * Created by Marcin on 17.07.2018.
 */

public class MessagesManagerImpl implements MessagesManager {

    private Queue<String> msgsQueue;

    public MessagesManagerImpl(){
        msgsQueue = new LinkedList();
    }

    @Override
    public synchronized void addMessageAndNotify(String msg) {
        msgsQueue.add(msg);
        notify();
    }

    @Override
    public synchronized List<String> getTheOldestMessages() {
        List<String> msgsList = new LinkedList<>();

        while(!msgsQueue.isEmpty()){
            msgsList.add(msgsQueue.poll());
        }

        return msgsList;
    }


}
