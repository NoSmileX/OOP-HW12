package org.example;


public class Counter {
    private int counter = 0;

    public Counter() {
    }

    public int value() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public synchronized void incrementWithSynchronization (){
        counter++;
    }
    public void incrementWithoutSynchronization (){
        counter++;
    }
}
