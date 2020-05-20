/**
 * Initialized by Jean Aldoph II, This testing set will be
 * used to MOCK generate employees for the Management Service of the
 * theme park. These tests will only determine the functionality
 * of the tests, they DO NOT need to be called during service.
 */

//

import data.GenerationDAO;
import org.junit.Test;
import utils.PostgresConnectionUtil;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GenerationTests {
//
//    ScriptEngineManager manager = new ScriptEngineManager();
//    ScriptEngine engine = manager.getEngineByName("JavaScript");
//    // read script file
//    engine.eval(Files.newBufferedReader(Paths.get("/EmployeeGeneration.js"), StandardCharsets.UTF_8)));
//
//    Invocable inv = (Invocable) engine;
// //call function from script file
// inv.invokeFunction("yourFunction", "param");

@Test
public void testGen()
{

    GenerationDAO genDao = new GenerationDAO();
    try
    {
        genDao.makeAday();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
}

}
