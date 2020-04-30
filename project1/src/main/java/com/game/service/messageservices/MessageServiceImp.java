package com.game.service.messageservices;

import com.game.data.Repository;
import com.game.models.Message;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;

public class MessageServiceImp {
    private String curr;
    private final Repository<Message, Timestamp> mrepo;
    private List<Message> messageList;
    private final Logger logger = Logger.getLogger(MessageServiceImp.class);
    public MessageServiceImp(Repository<Message, Timestamp> mrepo) {
        this.mrepo = mrepo;
    }

    public void boot(String curr){
        this.curr = curr;
        messageList = mrepo.findAll();
    }

    /**
     * prints out content of message to the user in a numbered format
     * Shows the username of who sent the message: message
     */
    public void readMessages() {
        int i=1;
        for (Message m:messageList) {
            logger.debug(i++ +": "+m.getFrom() + ": " + m.getMessage());
        }
    }

    /**
     * Creates and add message to the message list and call the repo's save method to
     * add the record to the database.
     * @return true if message has been sent
     */
    public boolean send(String to, String content) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Message temp = new Message(content,to,timestamp, curr);
        mrepo.save(temp);
        logger.debug("Message sent");
        return true;
    }

    /**
     * removes the message from message list and call the repo's delete method to
     * remove the record from the database. Unlike the account's, the primary key
     * is used as the id to delete the record as it may be the only column with
     * unique values.
     * @param index message id
     */
    public boolean delete(int index) {
        --index;
        if (index < messageList.size()&&index>=0) {
            Message temp = messageList.remove(index);
            mrepo.delete(temp.getTime());
            return true;
        }
        return false;
    }

    /**
     * Lets current user see how many messages they have
     */
    public int getMessageNumber(){
        return messageList.size();
    }

    /**
     * deletes by traversing through the user's messageList and getting their unique ids from the object
     */
    public void deleteAll() {
        for (Message m:messageList) {
            mrepo.delete(m.getTime());
        }
        messageList.clear();
    }
}
