package test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class MyThread {
    private int i = 0;

    public MyThread() {
        System.out.println(i);
    }

    public void my_print() {
        System.out.println("Test");
    }
    public static void main(String[] arg) {

       // new ThreadLocalCase1().test();
        
        // ThreadLocal
        //new ThreadPool().test();
//        new CallableAndFutureTask().test();
//        new TestForRunnable().test();
//        new TestForThread("main").test();

//        Park park = new Park();
//        ParkThread1 parkThread1 = new ParkThread1(park);
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new Thread(parkThread1).start();
//        new ParkThread1(park).start();
//        new ParkThread1(park).start();
//        new ParkThread1(park).start();
//        new ParkThread1(park).start();
//        NumAndLetter a = new NumAndLetter();
//        new NALThread1(a).start();
//        new NALThread2(a).start();
//         Production production = new Production();
//         new production_Thread1("生产者",production).start();
//         new production_Thread2("消费者1",production).start();
//         new production_Thread2("消费者2",production).start();
//         new production_Thread2("消费者3",production).start();

//        Account acct = new Account("1234567",1000);
//        new DrawThread("甲",acct,800).start();
//        new DrawThread("乙",acct,800).start();

        //main是静态的，为什么可以在这里面new不是静态的类（包括自身的类）？因为构造函数（构造器）是静态的吗？
        //和new a = new Main();有什么去区别？
        //new Main();

//        production a = new production();
//        new Thread(a).start();
        // Object[] a=  {1,2,3,5,4,8,6,7};
//        perm b = new perm(a,1,8);
//        System.out.println( a[0]);
//        test2 a = new test2(String.valueOf("1946"));
//        System.out.println(a.isBornBoomer());
//        System.out.println(String.valueOf("1946"));
       // new VolatileTest().test();
        new CasTest().test();
    }

}

/**
 * 测试CAS
 */
class CasTest {
   public void test() {
       AtomicInteger atomicInteger = new AtomicInteger(5);
       System.out.println(atomicInteger.compareAndSet(5,50));
       System.out.println(atomicInteger.compareAndSet(5,100));
       atomicInteger.getAndIncrement();
       atomicInteger.incrementAndGet();
   }
}

/**
 * 测试volatile
 */
class VolatileTest {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public void test() {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                    System.out.println(test.inc);
                };
            }.start();
        }

        while(Thread.activeCount()>1){
            //保证前面的线程都执行完
            Thread.yield();
            System.out.println(Thread.currentThread().getName());
        }

        System.out.println(test.inc);
    }
}

/**
 * 测试ThreadLocal
 */
class ThreadLocalCase {
    //ThreadLocal存入一个不可变类 String类型
    private static String value = "batman";
    private static ThreadLocal<String> objectThreadLocal = ThreadLocal.withInitial(()->value);
    public void test(){
        try{
            System.out.println(Thread.currentThread().getName());
            String value = objectThreadLocal.get();
            System.out.println(value);
            Thread t1 = new Thread(()->{
                String value1 = objectThreadLocal.get();
                value1 = "super man";
                objectThreadLocal.set(value1);
                System.out.println(Thread.currentThread().getName() + ":" + objectThreadLocal.get());
            });
            t1.start();
            t1.join();
            System.out.println(Thread.currentThread().getName());
            value = objectThreadLocal.get();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class ThreadLocalCase1 {
    //ThreadLocal存入一个不可变类 String类型
    private static String value = "batman";
    public void test(){
        try{
            System.out.println(Thread.currentThread().getName());
            System.out.println(value);
            Thread t1 = new Thread(()->{
                value = "super man";
                System.out.println(Thread.currentThread().getName() + ":" + value);
            });
            t1.start();
            t1.join();
            System.out.println(Thread.currentThread().getName());
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

/**
 * test
 */
class ThreadInMethod {
    public String result;

    public void print() {
        System.out.println(result);
    }

    public void op() {
        result = "2";
        new Thread(new Runnable() {
            @Override
            public void run() {
               // System.out.println(2);
                try {
                    Thread.sleep(5000);
                    result = "1";
                    System.out.println(result);
                } catch (Exception e) {
                }
            }
        }).start();
        System.out.println(result);
    }
}

/**
 * class for Thread pool
 */
class ThreadPool {

    public void test() {
        try {

            //创建一个固定线程数为6的线程池
            ExecutorService pool2 = Executors.newFixedThreadPool(6);
            //使用lambda表达式创建Runnable对象
            Runnable target = () -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "的i值为：" + i);
                }
            };
            //向池提交两个线程
            pool2.submit(target);
            pool2.submit(target);
            //关闭线程池
            pool2.shutdown();

            //通过多线程分解任务，有返回值
            int[] arr = new int[100];
            Random rand = new Random();
            int total = 0;
            for (int i = 0, len = arr.length; i < len; i++) {
                int tmp = rand.nextInt(20);
                total += (arr[i] = tmp);
            }
            System.out.println(total);
            //创建一个通用池
            ForkJoinPool pool = ForkJoinPool.commonPool();
            //提交可分解的CalTask任务
            Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
            System.out.println(future.get());
            pool.shutdown();

            //无返回值
            ForkJoinPool pool1 = new ForkJoinPool();
            pool1.submit(new PrintTask(0, 300));
            //awaitTermination(long timeOut, TimeUnit unit)，当前线程阻塞，直到:
            //等所有已提交的任务（包括正在跑的和队列中等待的）执行完；或者等超时时间到；
            //或者线程被中断，抛出InterruptedException
            pool1.awaitTermination(2, TimeUnit.SECONDS);
            pool1.shutdown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

/**
 *
 */
class ThreadPoolCached {
    public void test() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(index + "当前线程" + Thread.currentThread().getName());
                }
            });
        }
    }

}

/**
 *
 */
class CallableAndFutureTask {
    /**
     *
     */
    public void test() {
        //多线程Callable，FutureTask
        FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>) () -> {
            //call()
            int i = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "的循环变量i的值：" + i);
            }
            return i;
        });
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "的循环变量i的值：" + i);
            if (20 == i) {
                new Thread(task, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值：" + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * Runnable
 */
class TestForRunnable implements Runnable {
    private int k;
    //多线程,implements Runnable

    /**
     *
     */
    public void run() {
        for (; k < 100; k++) {
            System.out.println(Thread.currentThread().getName()
                    + " " + k);
        }
    }

    /**
     *
     */
    public void test() {
        //Runnable
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (20 == i) {
                try {
                    //共享k
                    TestForRunnable a = new TestForRunnable();
//                  new Thread(a, "新线程1").start();
//                  new Thread(a, "新线程2").start();
                    Thread t1 = new Thread(a, "新线程1");
                    Thread t2 = new Thread(a, "新线程2");
                    t2.start();
                    t1.start();
                    t1.join();
                    t2.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 *
 */
//多线程，extends Thread
class TestForThread extends Thread {
    private int k;

    /**
     * @param name
     */
    public TestForThread(String name) {
        super(name);
    }

    /**
     *
     */
    public void run() {
        try {
            for (; k < 100; k++) {
                System.out.println(getName() + " " + k);
                //有sleep()抛出InterruptedException
                sleep(10);
                if (20 == k) {
                    //转入就绪状态，给同级或者更高等级线程机会
                    Thread.yield();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void test() {
        //继承Thread
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (20 == i) {
                try {
                    //不共享k
                    // new TestForThread("低级").start();
                    TestForThread t1 = new TestForThread("高级");
                    TestForThread t2 = new TestForThread("低级");
                    t1.setPriority(Thread.MAX_PRIORITY);
                    t1.start();
                    t2.setPriority(Thread.MIN_PRIORITY);
                    t2.start();
                    // t1.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

/**
 * park
 */
class ParkThread1 implements Runnable {
    private Park park;

    public ParkThread1(Park park) {
        this.park = park;
    }

    public void run() {
        for (int i = 0; i < 100; i++)
            park.parking();
    }
}

class Park {
    //    private boolean[] sites = new boolean[3];
    //true可以停车，false不可以
    public int test;
    private boolean[] sites = {true, true, true};
    private int num = 0;

    public synchronized void parking() {
        //for循环放这里跟放ParkThread1的run里面不一样
        boolean flag = false;
        int k = 0;
        int i = 0;
        try {
            for (; i < 3; i++) {
                if (true == sites[i]) {
                    k = i;
                    System.out.println(Thread.currentThread().getName() + "停进了" + (i + 1) + "号位");
                    num++;
                    sites[k] = false;
                    flag = true;
                    break;
                }
            }
            notify();
            wait();
            if (false == flag) {
                System.out.println(Thread.currentThread().getName() + "无法进入，车位已满，请稍后");
                notify();
                wait();
            } else {
                sleep(new Random().nextInt(10000) + 1000);
                System.out.println(Thread.currentThread().getName() + "离开了" + (i + 1) + "号位");
                num--;
                sites[k] = true;
                notifyAll();
                wait();
                sleep(new Random().nextInt(10000) + 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class NALThread1 extends Thread {
    NumAndLetter numAndLetter;

    public NALThread1(NumAndLetter numAndLetter) {
        this.numAndLetter = numAndLetter;
    }

    public void run() {
        while (true) {
            if (!numAndLetter.num())
                break;
        }
    }
}

class NALThread2 extends Thread {
    private NumAndLetter numAndLetter;

    public NALThread2(NumAndLetter numAndLetter) {
        this.numAndLetter = numAndLetter;

    }

    public void run() {
        while (true) {
            if (!numAndLetter.letter())
                break;
        }
    }
}

class NumAndLetter {
    private int i = 1;
    private int num = i + 2;
    private int k = 65;

    public synchronized boolean num() {
        try {
            if (i <= 52) {
                System.out.print(i);
                System.out.print(i + 1);
                i = num;
                num = i + 2;
                notifyAll();
                wait();
            } else
                return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public synchronized boolean letter() {
        try {
            if (k < 91) {
                System.out.print((char) (k++));
                notifyAll();
                wait();
            } else
                return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

}

//继承RecursiveTask<Integer>来实现“可分解”的任务（有返回值）
class CalTask extends RecursiveTask<Integer> {
    //每个小任务最多打印50个
    private static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;

    public CalTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}

//继承RecursiveAction来实现“可分解”的任务（无返回值）
class PrintTask extends RecursiveAction {
    //每个小任务最多打印50个
    private static final int THRESHOLD = 50;
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值：" + i);
            }
        } else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            left.fork();
            right.fork();
        }
    }
}

//传统线程通信+condition
class production_Thread1 extends Thread {
    private Production production;

    public production_Thread1(String name, Production production) {
        super(name);
        this.production = production;
    }

    public void run() {
        for (int i = 0; i < 100; i++)
            production.product();
    }
}

/**
 *
 */
class production_Thread2 extends Thread {
    private Production production;

    public production_Thread2(String name, Production production) {
        super(name);
        this.production = production;
    }

    public void run() {
        for (int i = 0; i < 100; i++)
            production.consume();
    }
}

class Production {

//    private final Lock lock = new ReentrantLock();
//    private final Condition condition = lock.newCondition();

    int MAX_PRODUCT = 100;
    int MIN_PRODUCT = 0;
    volatile int product = 0;

    public synchronized void product() {
        //lock和condition
        //lock.lock();
        try {
            if (this.product >= MAX_PRODUCT) {
                //condition.await();
                wait();
                System.out.println("产品已满");
                return;
            }
            this.product++;
            System.out.println(Thread.currentThread().getName() + "生产了" + this.product + "个产品");
            notifyAll();
            //condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        finally  {
//            lock.unlock();
//        }
    }

    public synchronized void consume() {
        //lock.lock();
        try {
            if (this.product <= MIN_PRODUCT) {
                // condition.await();
                System.out.println("缺货");
                wait();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费了第" + this.product + "个产品");
            this.product--;
            notifyAll();
            //condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
//        }finally  {
//            lock.unlock();
//        }
        }
    }
}

//synchronized(obj)
class Account {
    private String accountNo;
    private double balance;
    private double drawAmount;

    public Account() {
    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void draw(double drawAmount) {
        if (balance >= drawAmount) {
            System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmount);
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= drawAmount;
            System.out.println("\t余额为：" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
        }
    }
}

class DrawThread extends Thread {
    private Account account;
    //取钱数
    private double drawAmount;

    public DrawThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    public void run() {
        account.draw(drawAmount);
        //同步代码块
//        synchronized (account) {
//            if(account.getBalance() >= drawAmount) {
//                System.out.println(getName()+"取钱成功！吐出钞票：" + drawAmount);
//                try  {
//                    Thread.sleep(1);
//                }catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                account.setBalance(account.getBalance() - drawAmount);
//                System.out.println("\t余额为：" + account.getBalance());
//            }
//            else  {
//                System.out.println(getName() + "取钱失败！余额不足！");
//            }
        // }
    }
}
