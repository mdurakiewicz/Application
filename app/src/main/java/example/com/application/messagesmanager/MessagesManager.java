package example.com.application.messagesmanager;

import java.util.List;

/**
 * Created by Marcin on 17.07.2018.
 */

public interface MessagesManager {

    void addMessageAndNotify(String msg);

    List<String> getTheOldestMessages();
}
