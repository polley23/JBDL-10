import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OddEvenApplication {
    public static void main(String[] args) {
//        OddEvenPrinter oddEvenPrinter = new OddEvenPrinter(20000);
//       Runnable oddRunnable = new Runnable(){
//           @Override
//           public void run() {
//               oddEvenPrinter.printOdd();
//           }
//       };
//        Runnable evenRunnable = new Runnable(){
//            @Override
//            public void run() {
//                oddEvenPrinter.printEven();
//            }
//        };
//        Thread thread1 = new Thread(oddRunnable);
//        Thread thread2 = new Thread(evenRunnable);
//        thread1.start();
//        thread2.start();
//
//    }

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+ " Happy coding");
            System.out.println(Thread.currentThread().getName()+ " Happy coding2");
            System.out.println(Thread.currentThread().getName()+ " Happy coding3");
        }
        };

        executorService.execute(new Thread(runnable));
        executorService.execute(new Thread(runnable));
        executorService.execute(new Thread(runnable));
        executorService.execute(new Thread(runnable));
        executorService.execute(new Thread(runnable));
        executorService.execute(new Thread(runnable));
        executorService.execute(new Thread(runnable));
        executorService.execute(new Thread(runnable));

        executorService.shutdown();


    }

}
