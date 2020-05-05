package com.game.service.messageservices;

import com.game.data.MessageSQLRepo;
import com.game.models.Message;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import java.util.List;

public class MessageServiceImp implements MessageService{
    private final MessageSQLRepo mrepo;
    private final Logger logger = Logger.getLogger(MessageServiceImp.class);
    public MessageServiceImp(MessageSQLRepo mrepo) {
        this.mrepo = mrepo;
    }

    /**
     * Creates and add message to the message list and call the repo's save method to
     * add the record to the database.
     */
    public void send(String to, String content, String from) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Message temp = new Message(content,to,timestamp,from);
        mrepo.save(temp);
        logger.debug("Message sent");
    }

    /**
     * deletes all messages to and from that user
     */
    @Override
    public void clear(String name) {
        mrepo.clear(name);
        logger.debug("All messages deleted");
    }

    @Override
    public List<Message> getMessageList(String username) {
        return mrepo.findAllbyName(username);
    }
}
