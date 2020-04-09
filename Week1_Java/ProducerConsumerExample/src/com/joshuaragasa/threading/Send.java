package com.joshuaragasa.threading;

public class Send extends ScannerReader
{
    private String msg;
    public void send(String msg)
    {
        System.out.println("Sending: " + msg);
        this.msg = msg;
        try
        {
            Thread.sleep(2000L);
        } catch (Exception var3) {
            System.out.println("The thread has been interrupted");
        }

    }

    public String writeText()
    {
        return super.getScanner().next();
    }

    public String getMsg()
    {
        if(this.msg == null)
        {
            return " ";
        }
        else
        {
            return this.msg;
        }
    }
}
