package files;

//import java.io.File;


import app.Application;
import app.IMSEntry;

import java.io.*;
import java.util.Scanner;

//TODO Add More Functionality
public class FileManipulation extends Application
{
    private FileReader reader = null;
    private BufferedReader bReader = null;

    public FileManipulation()
    {
        System.out.println("Connected.....");
        ReadStock();
    }

    private void RemoveFromStock()
    {

    }

    private void ReadStock()
    {

        try
        {
            reader = new FileReader("./resources/testFile.txt");
            bReader = new BufferedReader(reader);
            String line = "";
            int index = 0;
            while((line = bReader.readLine()) != null)
            {
                System.out.println(line);
                index++;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException f)
        {
            f.printStackTrace();
            System.exit(0);
        }

    }
}
