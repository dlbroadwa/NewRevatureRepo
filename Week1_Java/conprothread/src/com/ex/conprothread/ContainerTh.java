package com.ex.conprothread;

public class ContainerTh  implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("performing task " + name);

            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

}
