package com.joshuaragasa.threading;

public class SynchronizedThread extends Thread {
    private String msg;
    Send sender;

    SynchronizedThread(String msg, Send obj)
    {
        this.msg = msg;
        this.sender = obj;
    }

    public void run()
    {
        synchronized(this.sender)
        {
            this.sender.send(this.msg);
        }
    }

    public String getMsg()
    {
        return this.msg;
    }
}
