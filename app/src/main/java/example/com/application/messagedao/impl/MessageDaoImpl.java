package example.com.application.messagedao.impl;

import java.util.ArrayList;
import java.util.List;

import example.com.application.messagedao.MessageDao;

/**
 * Created by Marcin on 16.07.2018.
 */

public class MessageDaoImpl implements MessageDao{

    private List<String> messagesList;
    private static MessageDaoImpl messageDao;

    private MessageDaoImpl(){
        messagesList = new ArrayList<>();
    }

    public static MessageDaoImpl getInstance(){
        if(messageDao == null){
            messageDao = new MessageDaoImpl();
        }

        return messageDao;
    }

    @Override
    public void addMessages(List<String> msgs) {
        messagesList.addAll(msgs);
    }

    @Override
    public String getMessages() {
        return messagesList.toString();
    }

    @Override
    public int getSize() {
        return messagesList.size();
    }

    @Override
    public void clearMessages() {
        messagesList.clear();
    }
}
