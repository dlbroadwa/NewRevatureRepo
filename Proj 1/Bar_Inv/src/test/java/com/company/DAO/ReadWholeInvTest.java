package com.company.DAO;

import com.company.DAO.fileIO.ReadWholeInv;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWholeInvTest {
    @Test //see if we can read the a whole csv
    public void readAllTest() throws IOException {
        File testInv = new File("resources/testInv.csv");
        ArrayList results = new ArrayList();
        results = ReadWholeInv.readAll(testInv);
        String[] resultsRow = ((results.get(0)).toString()).split(",");
        Assert.assertEquals("[This", resultsRow[0]);
        Assert.assertEquals(" is", resultsRow[1]);
        Assert.assertEquals(" a", resultsRow[2]);
        Assert.assertEquals(" test", resultsRow[3]);
        Assert.assertEquals(" dude]", resultsRow[4]);

    }
//pullRow tests
    @Test //test to see if we can pull one row
    public void pullRowTest() throws Exception {
        String id = "106";
        String[] result = ReadWholeInv.pullRow(id);
        Assert.assertEquals(" " + id, result[1]);
    }

    @Test //test to see what happens when we pass in an unrecognized id
    public void pullRowTestCantFind() throws Exception {
        String id = "hi";
        String[] result = ReadWholeInv.pullRow(id);
        Assert.assertEquals("[I can't find that one]", result[0]);
    }
}