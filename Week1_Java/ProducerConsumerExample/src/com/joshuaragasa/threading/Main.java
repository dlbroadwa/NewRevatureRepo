package com.joshuaragasa.threading;

public class Main {



    public static void main(String[] args)
    {
        Send send = new Send();
        while(true)
        {
            System.out.println("User 1 message: ");
            SynchronizedThread s1 = new SynchronizedThread(send.writeText(), send);
            System.out.println("User 2 message: ");
            SynchronizedThread s2 = new SynchronizedThread(send.writeText(), send);
            s1.start();
            s2.start();
            try
            {
                s1.join();
                s2.join();
                System.out.println("User 1 said: " + s1.getMsg());
                System.out.println("User 2 said: " + s2.getMsg());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.exit(0);
                break;
            }
            if(s1.getMsg().toLowerCase().equals("quit") && s2.getMsg().toLowerCase().equals("quit"))
            {
                System.exit(0);
                break;
            }
        }
    }
}
