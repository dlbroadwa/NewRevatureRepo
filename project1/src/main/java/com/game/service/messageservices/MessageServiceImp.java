package com.game.service.messageservices;

import com.game.data.MessageSQLRepo;
import com.game.data.Repository;
import com.game.models.Message;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;

public class MessageServiceImp {
    private final MessageSQLRepo mrepo;
    private final Logger logger = Logger.getLogger(MessageServiceImp.class);
    public MessageServiceImp(MessageSQLRepo mrepo) {
        this.mrepo = mrepo;
    }

    /**
     * prints out content of message to the user in a numbered format
     * Shows the username of who sent the message: message
     */
    public void readMessages(String name) {
        int i=1;
        //perhaps store message list in account for quicker access
        List<Message> messageList = mrepo.findAll(name);
        for (Message m:messageList) {
            logger.debug(i++ +": "+m.getFrom() + ": " + m.getMessage());
        }
    }

    /**
     * Creates and add message to the message list and call the repo's save method to
     * add the record to the database.
     * @return true if message has been sent
     */
    public boolean send(String to, String content, String from) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Message temp = new Message(content,to,timestamp,from);
        mrepo.save(temp);
        logger.debug("Message sent");
        return true;
    }

    /**
     * deletes all messages to and from that user
     */
    public void clear(String name) {
        mrepo.clear(name);
    }
}
