package ua.my.threads;

import ua.my.manager.Manager;

public class ThreadWaiter implements Runnable {

    Manager manager;

    public void run() {
        try {
            manager.waiter();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
