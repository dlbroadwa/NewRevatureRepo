package auction.dataaccess;


import auction.models.Auction;
import auction.models.Item;
import junit.framework.TestCase;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ItemDAOTest extends TestCase {
    private ItemDAO itemDAO = new ItemDAO(new PostGresConnectionUtil());
    protected Item testItem = null;

   /* @BeforeClass
    public void testItemLoader(){

    }*/

    public void testSave() {
        String name="Danarrius";
        String descript="a guy with a face";
        testItem = new Item(name,descript);
        boolean passed = itemDAO.save(testItem);

        Assert.assertTrue("true", passed);
    }

    public void testRetrieveAll() {
        List<Item> items= itemDAO.retrieveAll();
        for(int i = 0; i < items.size(); i++)
        {
            System.out.println(items.get(i));
        }
        Assert.assertFalse("Retrieval Failed",items.isEmpty());
    }


    public void testRetrieveByID() {
        int testID= 1;
        Item newTestItem = itemDAO.retrieveByID(testID);
        System.out.println(newTestItem.getDescription());
        Assert.assertTrue("Assertion Returned",testID == newTestItem.getItemID());
    }
    /*
    public void testUpdate() {
        testItem= new Item(1,"NotDanarrius", "doesnt have a face");
        boolean updated = itemDAO.update(testItem);
        Assert.assertTrue("UPDATE Failed",updated);
    }*/

    public void testDelete() {
        Item testItem = new Item("Danarrius2", "a guy with a face");
        boolean deleted = itemDAO.delete(testItem);
        Assert.assertTrue("Delete Failed", deleted);

    }


   @After
    public static void reset(){
        ItemDAO itemDAO = new ItemDAO(new PostGresConnectionUtil());
        itemDAO.delete(new Item("Danarrius2","a guy with a face"));
        //itemDAO.save(new Item("Danarrius","Guy with a face"));
       // itemDAO.update(new Item(2,"Danarrius", "Guy with a face"));
    }

}