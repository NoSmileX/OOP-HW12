package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable synchronizedIncrement = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.incrementWithSynchronization();
            }
        };
        Runnable incrementRaceCondition = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.incrementWithoutSynchronization();
            }
        };
        Thread t1 = new Thread(incrementRaceCondition);
        Thread t2 = new Thread(incrementRaceCondition);
        Thread t3 = new Thread(incrementRaceCondition);
        System.out.println("Race condition increment example: ");
        t1.start();
        t2.start();
        t3.start();
        Thread.currentThread().join(2000);
        System.out.println("Value must be 30 000 but real value after increment is: " + counter.value());
        counter.setCounter(0);

        System.out.println("\nSynchronized increment example: ");
        Thread t4 = new Thread(synchronizedIncrement);
        Thread t5 = new Thread(synchronizedIncrement);
        Thread t6 = new Thread(synchronizedIncrement);
        t4.start();
        t5.start();
        t6.start();
        Thread.currentThread().join(2000);
        System.out.println("Value must be 30 000 but real value after increment is: " + counter.value());

    }
}