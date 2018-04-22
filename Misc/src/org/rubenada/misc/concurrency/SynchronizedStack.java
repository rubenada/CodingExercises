package org.rubenada.misc.concurrency;


import java.util.Random;
import java.util.concurrent.locks.*;

public class SynchronizedStack {
    public static void main(String args[]) {
        SyncStack<Integer> stack = new SyncStack<>();

        Thread p1 = new Thread(new Producer(stack));
        Thread c1 = new Thread(new Consumer(stack));

        try {
            p1.start();
            c1.start();

            p1.join();
            c1.join();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static class Producer implements Runnable {
        SyncStack<Integer> stack;
        Random rand = new Random();
        public Producer (SyncStack<Integer> stack) {
            this.stack = stack;
        }

        @Override
        public void run(){
            int iterations = 0;
            while (iterations < 10) {
                try {
                    stack.push(iterations);
                    Thread.sleep(1000 * rand.nextInt(3) + 1);
                }
                catch (InterruptedException e) {
                    //no-op
                }
                iterations++;
            }
        }
    }

    public static class Consumer implements Runnable {
        SyncStack<Integer> stack;
        Random rand = new Random();
        public Consumer (SyncStack<Integer> stack) {
            this.stack = stack;
        }

        @Override
        public void run(){
            int iterations = 0;
            while (iterations < 10) {
                try {
                    stack.pop();
                    Thread.sleep(1000 * rand.nextInt(5) + 1);
                }
                catch (InterruptedException e) {
                    //no-op
                }
                iterations++;
            }
        }
    }

    public static class SyncStack<T> {
        private Lock lock = new ReentrantLock();
        private Condition notEmpty = lock.newCondition();
        private Condition notFull = lock.newCondition();
        private int numberOfElements = 0;
        private static final int CAPACITY = 3;
        private Object[] array = new Object[CAPACITY];


        public void push (T element) throws InterruptedException {
            lock.lock();
            String name = Thread.currentThread().getName();
            try {
                while (numberOfElements == CAPACITY) {
                    System.out.println("Producer " + name + " waiting (due to full stack) with " + element);
                    notFull.await();
                }
                array[numberOfElements++] = element;
                notEmpty.signal();
                System.out.println("Producer " + name + " pushed " + element);
            }
            finally {
                lock.unlock();
            }
        }

        public T pop () throws InterruptedException {
            Object element;
            String name = Thread.currentThread().getName();
            lock.lock();
            try {
                while (numberOfElements == 0) {
                    System.out.println("Consumer " + name + " waiting with due to empty stack");
                    notEmpty.await();
                }
                element = array[--numberOfElements];
                notFull.signal();
                System.out.println("Consumer " + name + " retrieved " + element);
            }
            finally {
                lock.unlock();
            }
            return (T) element;
        }
    }
}
