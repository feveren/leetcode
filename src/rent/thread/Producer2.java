package rent.thread;

import java.util.LinkedList;
import java.util.Queue;

public class Producer2 {
    private Queue<Integer> queue;
    private int max;

    public Producer2(int max) {
        this.max = max;
        queue = new LinkedList<>();
    }

    public synchronized void put(int value) {
        try {
            while (queue.size() == max) {
                wait();
            }
            queue.offer(value);
            System.out.println("put " + value);
            notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized Integer take() {
        try {
            while (queue.size() == 0) {
                wait();
            }
            Integer value = queue.poll();
            System.out.println("take " + value);
            notifyAll();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        final Producer2 producer = new Producer2(3);
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                producer.put(i);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                producer.take();
            }
        }).start();
    }
}
