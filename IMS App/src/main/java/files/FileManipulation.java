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
    }


    //TODO find a way to remove certain element from a file
    public void removeFromStock(String fileName) throws IOException
    {
        try
        {
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
            Scanner scanner = super.getScanner();

            if (this.fw == null)
            {
                System.out.println("Current Stock:");
                readStock(this.testFile);
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
                System.out.println("What would you like to add?\n");
                bw.close();
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
                System.out.println("Current Stock:\n");
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

    public void pushToStock(String fileName) throws IOException
    {
        try
        {
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
            Scanner scanner = super.getScanner();
            if (this.fw == null)
            {
                System.out.println("Current Stock:");
                readStock(this.testFile);
                this.fw = new FileWriter(fileName, true);
                this.bw = new BufferedWriter(this.fw);
                this.fileContent = new ArrayList<String>();
                while((line = bReader.readLine()) != null)
                {
                    this.fileContent.add(line);
                    bw.newLine();
                }
                for(String i : this.fileContent)
                {
                    bw.newLine();
                    bw.write(i);
                    bw.newLine();
                }
                System.out.println("What would you like to add?\n");
                bw.write(scanner.nextLine());
                bw.close();
            }
            else
            {
                while((line = bReader.readLine()) != null)
                {

                    this.fileContent.add(line);
                    bw.newLine();

                }
                for(String i : this.fileContent)
                {
                    bw.newLine();
                    bw.write(i);
                    bw.newLine();
                }
                System.out.println("Current Stock:\n");
                bw.newLine();
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

    public void readStock(String fileName)
    {
        try
        {
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
            //int index = 0;
            while((line = bReader.readLine()) != null)
            {
                System.out.println(line);
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



    public String getTestFile()
    {
        return this.testFile;
    }
}






