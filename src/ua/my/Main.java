package ua.my;

import ua.my.manager.Manager;
import ua.my.threads.*;
/**
 * Simple program to demonstrate inter-thread communication
 * (wait(), join() and notify()) in Java
 */
public class Main {

    private static Manager manager = new Manager();

    public static void main(String[] args) {

        // Create a thread object that calls man.waiter()
        Runnable thread1 = new ThreadWaiter();
        ((ThreadWaiter) thread1).setManager(manager);
        Thread threadWaiter1 = new Thread(thread1, "Waiter 1");

        // Create another thread object that calls man.waiter() again
        Runnable thread2 = new ThreadWaiter();
        ((ThreadWaiter) thread2).setManager(manager);
        Thread threadWaiter2 = new Thread(thread1, "Waiter 2");

        // Create another thread object that calls man.notifier()
        Thread threadNotifier = new ThreadNotifier();
        threadNotifier.setName("Notifier");
        ((ThreadNotifier) threadNotifier).setManager(manager);

        // Starting all threads
        threadWaiter1.start();
        threadWaiter2.start();
        threadNotifier.start();
    }
}