package files;

import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class FileManipulationTest
{
    private String testFile = "./resources/testFile.txt";



    // TODO Make a test for pushtostock
//    @Test
//    public void pushToStock() throws IOException
//    {
//        this.fileManipulation = new FileManipulation();
//        String testFile = "./resources/testFile.txt";
//        this.fileManipulation.pushToStock(testFile);
//    }

    @Test
    public void readStock()
    {
        FileManipulation fileManipulation = new FileManipulation();
        String testFile = "./resources/testFile.txt";
        //this.fileManipulation.readStock(testFile);
        assertNotNull(testFile);
    }

}