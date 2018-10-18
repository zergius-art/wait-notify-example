package ua.my;

import ua.my.manager.Manager;
import ua.my.threads.*;
/**
 * Simple program to demonstrate inter-thread communication
 * (wait(), join() and notify()) in Java
 */
public class Main
{
    public static void main(String[] args) {



        // Create a thread object that calls man.waiter()
        Thread threadWaiter1 = new Thread(new ThreadWaiter(), "Waiter 1");

        // Create another thread object that calls man.waiter() again
        Thread threadWaiter2 = new Thread(new ThreadWaiter(), "Waiter 2");

        // Create another thread object that calls man.notifier()
        Thread threadNotifier = new ThreadNotifier();
        threadNotifier.setName("Notifier");

        // Starting all threads
        threadWaiter1.start();
        threadWaiter2.start();
        threadNotifier.start();
    }
}