package filestest;

import files.FileManipulation;
import org.junit.Test;

public class FileManipulationTests
{
    FileManipulation fm = new FileManipulation();

    @Test
    public void shouldRead()
    {
        fm.readStock("resources/testFile.txt");
    }
}
