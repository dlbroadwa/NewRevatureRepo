package com.company.DAO;

import com.company.DAO.fileIO.AddItems;
import com.company.DAO.fileIO.ReadWholeInv;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddItemsTest {
    private String[] testItem = {"alcohol","will","help","this","situation"};
    @Test //test if the adder works correctly
    public void adderTest() throws IOException {
        File testInv = new File("resources/testInv.csv");
        AddItems.adder(testItem,testInv);
        ArrayList wholeTest = ReadWholeInv.readAll(testInv);
        int testSize = wholeTest.size();
        String[] newRow = ((wholeTest.get(testSize-1)).toString()).split(",");
        Assert.assertEquals("[alcohol",newRow[0]);

    }
    @Test //test to see if new file writer is working
    public void adderNoFile() throws IOException {
        File notReal = new File("resources/iDontExsist.csv");
        if (notReal.exists()){
            notReal.delete();
            AddItems.adder(testItem,notReal);
            ArrayList wholeTest = ReadWholeInv.readAll(notReal);
            String line = wholeTest.toString();
            Assert.assertEquals("[[Item Name, ID, onHand, lowLevel, optLevel]]",line);
        } else{
            AddItems.adder(testItem,notReal);
            ArrayList wholeTest = ReadWholeInv.readAll(notReal);
            String line = wholeTest.toString();
            Assert.assertEquals("[Item Name,ID,onHand,lowLevel,optLevel]",line);
        }

    }
}