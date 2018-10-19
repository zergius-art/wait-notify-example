package ua.my.threads;

import ua.my.manager.Manager;

public class ThreadNotifier extends Thread{
    @Override
    public void run() {
        Manager man = new Manager();
        try {
            man.notifier();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
