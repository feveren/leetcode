package rent.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer {
    private Queue<Integer> queue;
    private int max;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public Producer(int max) {
        this.max = max;
        queue = new LinkedList<>();
    }

    public void put(int value) {
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
            }
            queue.offer(value);
            System.out.println("put " + value);
            notEmpty.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Integer take() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            Integer value = queue.poll();
            System.out.println("take " + value);
            notFull.signalAll();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
