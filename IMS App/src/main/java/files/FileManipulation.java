package files;

import app.Application;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManipulation extends Application
{
    private FileReader reader;
    private BufferedReader bReader;
    private FileWriter fw;
    private BufferedWriter bw;
    private String line = "";
    private String testFile = "./resources/testFile.txt";
    private ArrayList<String> fileContent;

    public FileManipulation()
    {
        this.reader = null;
        this.bReader = null;
        //PushToStock(testFile);
        //ReadStock();
    }

    //TODO
    public void RemoveFromStock(String fileName)
    {
        try
        {
            fw = new FileWriter(fileName, true);
            bw = new BufferedWriter(fw);
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
            Scanner scanner = super.getScanner();
            System.out.println("What would you like from our stock?");
            //int index = 0;
            while((line = bReader.readLine()) != null)
            {

            }
            bw.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void ReadStock(String fileName)
    {
        try
        {
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
            //int index = 0;
            while((line = bReader.readLine()) != null)
            {
                System.out.println(line);
                //index++;
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
    }

    public void PushToStock(String fileName)
    {
        try
        {
            System.out.println("Current Stock:\n");
            System.out.println("What would you like to add?\n");
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
            Scanner scanner = super.getScanner();
            if (this.fw == null)
            {
                ReadStock(this.testFile);
                this.fw = new FileWriter(fileName, true);
                this.bw = new BufferedWriter(this.fw);
                this.fileContent = new ArrayList<String>();
                while((line = bReader.readLine()) != null)
                {
                    this.fileContent.add(line);
                }
                for(String i : this.fileContent)
                {
                    bw.write(i);
                }
                bw.write(scanner.nextLine());
                bw.close();
                //scanner.nextLine();
            }
            else
            {
                while((line = bReader.readLine()) != null)
                {
                    this.fileContent.add(line);
                }
                for(String i : this.fileContent)
                {
                    bw.write(i);
                }
                bw.write(scanner.nextLine());
                bw.close();
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

    public String getTestFile()
    {
        return this.testFile;
    }
}
