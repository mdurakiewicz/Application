package example.com.application.messagedao;

import java.util.List;

/**
 * Created by Marcin on 16.07.2018.
 */

public interface MessageDao {

    void addMessages(List<String> msgs);

    String getMessages();

    int getSize();

    void clearMessages();
}
