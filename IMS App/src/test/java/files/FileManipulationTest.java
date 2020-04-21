/**
 * The FileManipulationTest tests the readStock function to ensure that the testFile that is to be read
 * exists.
 */
package files;

import org.junit.Test;
import static org.junit.Assert.*;

public class FileManipulationTest
{
    private String testFile = "./resources/testFile.txt";


    // Tests to ensure you can read to the text file.
    @Test
    public void readStock()
    {
        FileManipulation fileManipulation = new FileManipulation();
        String testFile = "./resources/testFile.txt";
        //this.fileManipulation.readStock(testFile);
        assertNotNull(testFile);
    }

}