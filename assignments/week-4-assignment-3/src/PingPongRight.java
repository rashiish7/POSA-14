// Import the necessary Java synchronization and scheduling classes.

import java.util.concurrent.CountDownLatch;

/**
 * @class PingPongRight
 *
 * @brief This class implements a Java program that creates two
 *        instances of the PlayPingPongThread and start these thread
 *        instances to correctly alternate printing "Ping" and "Pong",
 *        respectively, on the console display.
 */
public class PingPongRight {
    /**
     * Number of iterations to run the test program.
     */
    public static int mMaxIterations = 10;
    
    /**
     * Latch that will be decremented each time a thread exits.
     */
    public static CountDownLatch mLatch = new CountDownLatch(2); // TODO - You fill in here

    /**
     * @class PlayPingPongThread
     *
     * @brief This class implements the ping/pong processing algorithm
     *         using the SimpleSemaphore to alternate printing "ping"
     *         and "pong" to the console display.
     */
    public static class PlayPingPongThread extends Thread
    {
        /**
         * Constructor initializes the data member.
         */
        public PlayPingPongThread (final String print, final SimpleSemaphore sema1,
                                   final SimpleSemaphore sema2 /* TODO - You fill in here */)
        {
            // TODO - You fill in here.
            mPrint = print;
            mSema1 = sema1;
            mSema2 = sema2;
        }

        /**
         * Main event loop that runs in a separate thread of control
         * and performs the ping/pong algorithm using the
         * SimpleSemaphores.
         */
        public void run () 
        {
            // TODO - You fill in here.
            while(mMaxIterations > 0) {

                mSema1.acquireUninterruptibly();

                if(mMaxIterations > 0)
                    System.out.println(mPrint + "  (" + mMaxIterations + ")");

                if(mPrint == "Pong!")
                    --mMaxIterations;

                mSema2.release();

            }

            // Thread exists
            mLatch.countDown();
        }

        /**
         * String to print (either "ping!" or "pong"!) for each
         * iteration.
         */
        // TODO - You fill in here.
        private String mPrint;

        /**
         * The two SimpleSemaphores use to alternate pings and pongs.
         */
        // TODO - You fill in here.
        private SimpleSemaphore mSema1;
        private SimpleSemaphore mSema2;
    }

    /**
     * The main() entry point method into PingPongRight program. 
     */
    public static void main(String[] args) {
        try {         
            // Create the ping and pong SimpleSemaphores that control
            // alternation between threads.

            // TODO - You fill in here.
            SimpleSemaphore pingSema = new SimpleSemaphore(1, true);
            SimpleSemaphore pongSema = new SimpleSemaphore(0, true);

            System.out.println("Ready...Set...Go!");

            // Create the ping and pong threads, passing in the string
            // to print and the appropriate SimpleSemaphores.
            PlayPingPongThread ping =
                new PlayPingPongThread("Ping!", pingSema, pongSema /* TODO - You fill in here */);
            PlayPingPongThread pong =
                new PlayPingPongThread("Pong!", pongSema, pingSema /* TODO - You fill in here */);
            
            // Initiate the ping and pong threads, which will call the
            // run() hook method.
            ping.start();
            pong.start();

            // Use barrier synchronization to wait for both threads to
            // finish.

            // TODO - replace replace the following line with a
            // CountDownLatch barrier synchronizer call that waits for
            // both threads to finish.
            mLatch.await();
            throw new java.lang.InterruptedException();
        } 
        catch (java.lang.InterruptedException e)
            {}

        System.out.println("Done!");
    }
}
