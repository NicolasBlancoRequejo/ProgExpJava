package be._Testing_of_Multi_threaded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
	static int NUM_THREADS = 6;

	public void testParallelInsertionAndConsumption() throws InterruptedException, ExecutionException{
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
        ExecutorService threadPool = Executors.newFixedThreadPool(NUM_THREADS);
        final CountDownLatch latch = new CountDownLatch(NUM_THREADS);
        List<Future<Integer>> futuresPut = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 3; i++){
            Future<Integer> submit = threadPool.submit(new Callable<Integer>() {
                public Integer call(){
                    int sum = 0;
                    try {
                        for (int i = 0; i < 10000; i++) {
                            int nextInt = ThreadLocalRandom.current().nextInt(100);
                            queue.put(nextInt);
                            sum += nextInt;
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted exception occured! " + e.getMessage());
                    }
                    latch.countDown();
                    return sum;
                }
             });
            futuresPut.add(submit);
        }
        List<Future<Integer>> futuresGet = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 3; i++) {
            Future<Integer> submit = threadPool.submit(new Callable<Integer>() {
                public Integer call() {
                    int count = 0;
                    try {
                        for (int i = 0; i < 10000; i++) {
                            Integer got = queue.take();
                            count += got;
                        }
                    } catch (Exception e) {
                        System.out.println("Exception occured! " + e.getMessage());
                    }
                    latch.countDown();
                    return count;
                }
            });
            futuresGet.add(submit);
        }
        
        latch.await();
        
        int sumPut = 0;
        for (Future<Integer> future : futuresPut) {
        	sumPut += future.get();
        }
        int sumGet = 0;
        for (Future<Integer> future : futuresGet) {
        	sumGet += future.get();
        }
        Assert.assertEquals(sumPut, sumGet);
	}

}
