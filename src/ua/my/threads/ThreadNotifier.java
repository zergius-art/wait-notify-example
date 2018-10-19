package ua.my.threads;

import ua.my.manager.Manager;

public class ThreadNotifier extends Thread{

    Manager manager;

    @Override
    public void run() {
        try {
            manager.notifier();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
