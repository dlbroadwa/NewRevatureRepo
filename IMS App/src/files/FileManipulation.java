package files;

import java.io.File;


import app.Application;
import app.IMSEntry;

import java.io.*;
import java.util.Scanner;

//TODO Add More Functionality
public class FileManipulation extends Application
{
    private FileReader reader;
    private BufferedReader bReader;
    private FileWriter fw;
    private String line = "";
    private String testFile = "./resources/testFile.txt";
    public FileManipulation()
    {
        System.out.println("Connected.....");
        this.reader = null;
        this.bReader = null;
        PushToStock(testFile);
        //ReadStock();
    }

    private void RemoveFromStock()
    {

    }

    private String ReadStock(String fileName)
    {

        try
        {
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
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
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        finally
        {
            return fileName;
        }
    }

    private void PushToStock(String fileName)
    {
        //TODO provide a way for the file content to NOT be overriden
        try
        {
            System.out.println("Current Stock:\n");
            System.out.println("What would you like to add?\n");
            Scanner scanner = super.getScanner();
            if(this.fw == null)
            {
                this.fw = new FileWriter(ReadStock(fileName));
                fw.write(scanner.next());
                fw.close();
            }
            else
            {
                fw.write("./resources/testFile.txt");
                fw.close();
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
