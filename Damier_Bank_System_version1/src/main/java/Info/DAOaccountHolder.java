package Info;

import Application.DispatchingTask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this class has only one mission
 *
 * Reading and writing to a file
 * System is saving information to the database also to the file system
 */
public class DAOaccountHolder implements Information {



String filepath =null;
DispatchingTask dispatchingTask;




/**
Getting the file path that was passed to this constructor at the Dispatching class level
 */
    public DAOaccountHolder(String filepath){

        this.filepath = filepath;
    }

public void readingFile() throws IOException {

List array = new ArrayList<>();
    FileReader reader = new FileReader(filepath); // File Path
    BufferedReader bufferedReader =new BufferedReader(reader); // Reading the file


String str  ;
    while ((str =  bufferedReader.readLine()) !=null) // Checking if the file is Empty and Print it out
    {
        array.add(str);
      //  System.out.println(str);
    }
    System.out.println(array);

}


// Write to the File
public  void writingFile(String name , String Add , String sb , int ssn) throws IOException {

//calling the file path
    FileWriter file = new FileWriter(filepath, true);
// write line by line to the file
    BufferedWriter bufferedWriter = new BufferedWriter(file);

    List list = new ArrayList<>();// Create a list to store data to a Single Object



//Write to the file and get the data from AccountHolderInfo through the Dispatching Class

bufferedWriter.write(name+"\t");
    bufferedWriter.write(Add+"\t");
    bufferedWriter.write(sb+"\t");
    bufferedWriter.write( ssn+"\t");



   bufferedWriter.newLine(); // New line after every Storage

    bufferedWriter.close(); // Close the file
    System.out.println("\nData also Stored in a File\n"); // Confirm Storage


}















    @Override
    public void allInfoAboutThePerson() {

      //  accountHolderInfo.toString();
    }

    @Override
    public void Credential(int num) {

    }
}
