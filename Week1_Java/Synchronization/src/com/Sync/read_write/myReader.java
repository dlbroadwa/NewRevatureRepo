package com.Sync.read_write;

import java.io.*;

public class myReader implements Runnable {
    String[] readingMaterial = null;
    int position = 0;


    @Override
    public void run()
    {
        FileReader reader = null;
        BufferedReader buffer = null;
        readingMaterial = new String[50002];
     try
     {
         reader = new FileReader("C:\\Users\\J Aldo\\Desktop\\Revature\\2004-apr06-java\\Week1_Java\\Synchronization\\src\\resource");
         buffer = new BufferedReader(reader);
         String lines = "";
         while (((lines = buffer.readLine()) != null) & (position <= 50000))
         {
             readingMaterial[position++] = lines;
         }
         reader.close();
     }

     catch (Exception e)
     {
         e.printStackTrace();
     }
        System.out.println("Done Reading "+ (position-1) + " Lines" );
    }
}
