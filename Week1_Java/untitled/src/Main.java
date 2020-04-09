public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread p = new ProdCons(){
            public void run(){
                produce();
            }
        };
        Thread p2 = new ProdCons(){
            public void run(){
                produce();
            }
        };
        Thread c = new ProdCons(){
            public void run(){
                consume();
            }
        };

        p.run();
        p2.run();
        c.run();
        p.join();
        p2.join();
        c.join();
        return;
    }
}
