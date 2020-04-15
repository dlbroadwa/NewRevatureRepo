package test.java;

import com.ex.dao.SqlDatabase;
import com.ex.main.Runner;
import org.junit.Before;
import org.junit.Test;

public class SQLTest {


    @Test
    public void connectToData(){
        Runner sqlDatabase = new SqlDatabase();
        sqlDatabase.run();
    }
}
