import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/*
 * wgen we use java.util.concurrent, we probably want
 * - Lock
 * - Condition (created from Lock, can have multiple conditions per Lock)
 * - Condition#await(), Condition#notifyAll() 
 */
class Buffer {
    Object buf;
    Lock l = new ReentrantLock();
    Condition c = l.newCondition();

    void produce(Object val) {
        l.lock();
        while (buf != null) {
            c.await();
        }
        buf = val;
        c.signalAll();
        l.unlock();
    }

    Object consume() {
        l.lock();
        while (buf == null) {
            c.await();
        }
        Object o = buf;
        buf = null;
        c.signalAll();
        l.unlock();
        return o;
    }
}

/* 
 * when we use synchronized, we probably want
 * - wait()
 * - notifyAll (exactly one condition per lock, and you don't create it)
 */
class Buffer2 {
    Object buf;

    synchronized void producee(Object o) {
        // gets the lock magically
        while (buf != null) {
            wait(); // the same as Condition#await();
        }
        buf = o;
        notifyAll(); // the same as Condition#signalAll();
        //releases the lock magically
    }

    synchronized Object consume() {
        // gets the lock magically
        while (buf == null) {
            wait();
        }
        Object o = buf;
        buf = null;
        notifyAll();
        return o; // releases the lock magically
    }
}

/* 
 * Thread cancellation
 * In Java, a program exits when all of its threads have exited
 *     A thread exits when its run() method returns (or raises an exception)
 * What if we need to stop a thread in the middle?
 *     Option A: Kill the thread (bad because some invarient might be transiently 
 *                                violated and then the thread dies before it can 
 *                                be resolved, not good)
 *     Option B: Politely ask the thread to die please (examples below)
 */

class Processor extends Thread {
    public void run() {
        while (!Thread.interrupted()) { // this is a quiescent point, a safe point 
                                        // to end the thread
            // do the work of the thread
        }
        // do the clean up
    }
}

/* 
 * Some things, like Thread.sleep() and Lock#lockInteruptably() throw the
 * InteruptedExeption when they are interupted. We can catch this and do our 
 * cleanup work before dying.
 */

/* 
 * The memory write cache can cause some problems. An update might go into the 
 * cache and not get proprigated across threads. We can "fix" this with volatile.
 * 
 * This guarentees that the latest value of x is visible from all threads.
 * But, updates to x cannot be atomic.
 * 
 * tradeoffs, tradeoffs,
 */
class V {
    volatile int x = ;

    void changeX(int x) {
        this.x = x;
    }
}

/* 
 * java BlockingQueue
 * 
 * 
 */
