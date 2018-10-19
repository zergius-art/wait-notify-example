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

        // Create a thread object that calls man.produce()
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    man.produce();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create another thread object that calls
        // man.consume()
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    man.consume();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // Starting both threads
        t1.start();
        t2.start();
    }

    /**
     * Manager class with produce() and consume() methods.
     */
    public static class Manager {
        // Prints a string and waits for consume()
        public void produce()throws InterruptedException {
            // synchronized block ensures only one thread running at a time.
            synchronized(this) {
                System.out.println("producer thread running");
                // releases the lock on shared resource
                wait();
                // and waits till some other method invokes notify().
                System.out.println("Resumed");
            }
        }
        // Sleeps for some time and waits for a key press. After key
        // is pressed, it notifies produce().
        public void consume()throws InterruptedException {
            // this makes the produce thread to run first.
            Thread.sleep(1000);
            Scanner s = new Scanner(System.in);
            // synchronized block ensures only one thread
            // running at a time.
            synchronized(this) {
                System.out.println("Waiting for return key.");
                s.nextLine();
                System.out.println("Return key pressed");
                // notifies the produce thread that it can wake up.
                notify();
                // Sleep
                Thread.sleep(1000);
            }
        }
    }
}
