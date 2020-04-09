import java.util.ArrayList;

public class ProdCons extends Thread {
    private static ArrayList<String> s = new ArrayList<String>(10);
    int capacity = 10;

    public void produce(){
        while (s.size() == capacity){
            yield();
        }
        s.add("help");
        s.add("me");
        s.add("I");
        s.add("am");
        s.add("trapped");
        return;
    }

    public void consume(){
        while (s.size()!=capacity){
            yield();
        }
        for (int i = 0; i<s.size(); i++) {
            System.out.println(s.remove(i));
        }
        return;
    }

}
