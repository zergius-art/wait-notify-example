package ua.my;
/**
 * Simple program to demonstrate inter-thread communication
 * (wait(), join() and notify()) in Java
 */
import java.util.Scanner;
public class Main
{
    public static void main(String[] args) throws InterruptedException {

        Manager man = new Manager();

        // Create a thread object that calls man.waiter()
        Thread threadWaiter1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    man.waiter();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Waiter 1");

        // Create another thread object that calls man.waiter() again
        Thread threadWaiter2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    man.waiter();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Waiter 2");

        // Create another thread object that calls man.notifier()
        Thread threadNotifier = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    man.notifier();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Starting all threads
        threadWaiter1.start();
        threadWaiter2.start();
        threadNotifier.start();
    }

    /**
     * Manager class with waiter() and notifier() methods.
     */
    public static class Manager {
        // Prints a string and waits for notifier()
        public void waiter()throws InterruptedException {
            // synchronized block ensures only one thread running at a time.
            synchronized(this) {
                System.out.println("producer thread "
                        + Thread.currentThread().getName() + " running");
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
}
