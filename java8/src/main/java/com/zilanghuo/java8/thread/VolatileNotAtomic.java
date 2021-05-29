package com.zilanghuo.java8.thread;

/**
 * @author Acer
 * volatile 变量无法保证线程安全，只能保证内存可见性
 * 如果要变成线程安全，那么使用synchronized 锁
 */
public class VolatileNotAtomic {

    private static volatile long count = 0L;

    private static final int NUMBER = 10000;

    public static void main(String[] args) {
        Thread subtractThread = new SubtractThread();
        subtractThread.start();
        for (int i = 0; i < NUMBER; i++) {
            count++;
        }
        while (subtractThread.isAlive()) {
        }
        System.out.println("count 最后的值：" + count);
    }

    public static class SubtractThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < NUMBER; i++) {
                count--;
            }
        }
    }
}