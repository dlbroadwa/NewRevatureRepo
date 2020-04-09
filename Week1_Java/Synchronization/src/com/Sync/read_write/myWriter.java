package com.Sync.read_write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class myWriter implements Runnable
{

    @Override
    public void run()
    {
        int i = 1;
        BufferedWriter writer = null;
        try
        {
            File myobj = new File("C:\\Users\\J Aldo\\Desktop\\Revature\\2004-apr06-java\\Week1_Java\\Synchronization\\src\\resource");
            myobj.delete();
            writer = new BufferedWriter(new FileWriter("C:\\Users\\J Aldo\\Desktop\\Revature\\2004-apr06-java\\Week1_Java\\Synchronization\\src\\resource", true));
            String lines = "";
            for (i = 1 ; i < 50000 + 1; i++)
            {
                    lines = String.valueOf(i);
                    writer.write("Line " + lines);
                    if (i != 50000) writer.newLine();
            }
            writer.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Done Writing " + (i-1) +" lines");
    }
}
