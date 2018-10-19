package ua.my.manager;

import java.util.Scanner;

/**
 * Manager class with waiter() and notifier() methods.
 */
public class Manager {
    // Prints a string and waits for notifier()
    public void waiter()throws InterruptedException {
        // synchronized block ensures only one thread running at a time.
        synchronized(this) {
            System.out.println("waiter thread \""
                    + Thread.currentThread().getName() + "\" running");
            // releases the lock on shared resource
            wait();
            // and waits till some other method invokes notify().
            System.out.println("Resumed "
                    + Thread.currentThread().getName());
            // sleep
            Thread.sleep(1000);
            // and notifies second waiter thread that it can wake up.
            notify();
        }
    }

    // Sleeps for some time and waits for a key press.
    // After key is pressed, it notifies waiter().
    public void notifier()throws InterruptedException {
        // this makes the produce thread to run first.
        Thread.sleep(1000);
        Scanner s = new Scanner(System.in);
        // synchronized block ensures only one thread
        // running at a time.
        synchronized(this) {
            System.out.println("notifier thread \""
                    + Thread.currentThread().getName() + "\" running");
            System.out.println("Waiting for return key.");
            s.nextLine();
            System.out.println("Return key pressed");
            // notifies the waiter thread that it can wake up.
            notify();
            // Sleep
            Thread.sleep(1000);
        }
    }
}