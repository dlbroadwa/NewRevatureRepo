package test.java;

import com.ex.dao.FileIoDAO;
import com.ex.main.InventoryScreen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest {
    FileIoDAO screen;

     @Test
    public void testInit() {
        // setup a fake file with animal
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("testAnswers"));
            writer.write("Sammy Lion M 12 Enclosure17");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        screen = new FileIoDAO("testAnswers");
         Assert.assertNotNull(screen);
    }


}
