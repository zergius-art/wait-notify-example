package ua.my.threads;

import ua.my.manager.Manager;

public class ThreadWaiter implements Runnable {
    public void run() {
        Manager man = new Manager();
        try {
            man.waiter();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
