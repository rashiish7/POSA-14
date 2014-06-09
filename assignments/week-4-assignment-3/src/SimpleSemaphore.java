import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores.
 */
public class SimpleSemaphore {
    /**
     * Constructor initialize the data members.
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    {
        // TODO - you fill in here
        count = permits;
        lock = new ReentrantLock(fair);
        condition = lock.newCondition();
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
        lock.lock();
        try {
            while (count == 0)
                condition.await();

            --count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here

        lock.lock();
        try {
            while (count == 0)
                condition.awaitUninterruptibly();

            --count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
        lock.lock();
        try {
            ++count;
            // release the waiting thread one at a time
            condition.signal();
        } finally {
            lock.unlock();
        }

    }

    /**
     * Return the number of permits available.
     */
    public int availablePermits(){
        // TODO - you fill in here
        return count; // You will change this value.
    }

    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
    private Lock lock;

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    private Condition condition;

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here.  Make sure that this data member will
    // ensure its values aren't cached by multiple Threads..
    private volatile int count;
}

